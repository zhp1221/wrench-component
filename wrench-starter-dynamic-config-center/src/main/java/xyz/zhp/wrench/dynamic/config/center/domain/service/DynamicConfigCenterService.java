package xyz.zhp.wrench.dynamic.config.center.domain.service;

import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import xyz.zhp.wrench.dynamic.config.center.config.DynamicConfigCenterAutoConfig;
import xyz.zhp.wrench.dynamic.config.center.config.DynamicConfigCenterAutoProperties;
import xyz.zhp.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import xyz.zhp.wrench.dynamic.config.center.types.annotations.DccValue;
import xyz.zhp.wrench.dynamic.config.center.types.common.Constants;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhp
 * @description 动态属性值处理操作服务类
 * @since 2025-05-06 13:36
 */
public class DynamicConfigCenterService implements IDynamicConfigCenterService{

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterAutoConfig.class);

    private final DynamicConfigCenterAutoProperties dynamicConfigCenterAutoProperties;

    private final RedissonClient redissonClient;

    /**
     * 本地属性缓存map
     */
    private final Map<String, Object> dccBeanGroup = new ConcurrentHashMap<>();

    public DynamicConfigCenterService(DynamicConfigCenterAutoProperties dynamicConfigCenterAutoProperties, RedissonClient redissonClient) {
        this.dynamicConfigCenterAutoProperties = dynamicConfigCenterAutoProperties;
        this.redissonClient = redissonClient;
    }

    @Override
    public Object proxyObject(Object bean) {

        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;

        // 判断bean，是否使用了代理
        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);
        }

        Field[] fields = targetBeanClass.getDeclaredFields();

        for (Field field : fields) {
            // 判断是否添加DccValue注解
            if (!field.isAnnotationPresent(DccValue.class)) continue;

            DccValue dccValue = field.getAnnotation(DccValue.class);

            String value = dccValue.value();

            if (StringUtils.isBlank(value)) {
                throw new RuntimeException(field.getName() + " @DccValue is not config value, config case [isSwitch:1]");
            }

            String[] splits = value.split(Constants.SYMBOL_COLON);

            String attributeKey = dynamicConfigCenterAutoProperties.getKey(splits[0].trim());

            // val
            String defaultValue = splits.length == 2 ? splits[1] : null;

            if (StringUtils.isBlank(defaultValue)) {
                throw new RuntimeException("dcc config err " + attributeKey + " is null - 请配置默认值! ");
            }

            String setValue = defaultValue;

            // 设置值
            try {

                // redis 操作，判断配置的key是否存在，不存在创建，存在获取最新值
                RBucket<String> bucket = redissonClient.getBucket(attributeKey);

                if (bucket.isExists()) {
                    setValue = bucket.get();
                }else{
                    bucket.set(defaultValue);
                }

                // 通过反射更改属性值
                field.setAccessible(true);
                field.set(targetBeanObject, fieldType(field, setValue));
                field.setAccessible(false);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            dccBeanGroup.put(attributeKey, targetBeanObject);
        }

        // 返回传进来的bean
        return bean;
    }

    @Override
    public void adjustAttributeValue(AttributeVO attributeVO) {


        String atrributeKey = dynamicConfigCenterAutoProperties.getKey(attributeVO.getAttribute());
        String value = attributeVO.getVal();

        RBucket<Object> bucket = redissonClient.getBucket(atrributeKey);

        // redis 缓存未命中，返回
        if (!bucket.isExists()) return;

        Object objBean = dccBeanGroup.get(atrributeKey);

        // 本地缓存未命中，返回
        if (objBean == null) return;

        Class<?> objBeanClass = objBean.getClass();

        if (AopUtils.isAopProxy(objBeanClass)) {
            objBeanClass = AopUtils.getTargetClass(objBeanClass);
        }

        try {
            // 1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
            // 2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
            Field field = objBeanClass.getDeclaredField(attributeVO.getAttribute());

            field.setAccessible(true);
            field.set(objBean, fieldType(field, value));
            field.setAccessible(false);

            // redis 更新
            bucket.set(value);

            log.info("DCC 节点监听，动态设置值 {} {}", atrributeKey, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 处理属性类型，兼容多类型值
     *
     * @param field 属性
     * @param value 值
     * @return 值
     */
    private Object fieldType(Field field, String value){ // Add type conversion logic
        Class<?> fieldType = field.getType();
        Object convertedValue;

        if (fieldType == Integer.class) {
            convertedValue = Integer.parseInt(value);
        } else if (fieldType == Long.class) {
            convertedValue = Long.parseLong(value);
        } else if (fieldType == Double.class) {
            convertedValue = Double.parseDouble(value);
        } else if (fieldType == Boolean.class) {
            convertedValue = Boolean.parseBoolean(value);
        } else {
            convertedValue = value;
        }
        return convertedValue;
    }

}
