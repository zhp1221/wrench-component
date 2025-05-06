package xyz.zhp.wrench.dynamic.config.center.domain.service;

import xyz.zhp.wrench.dynamic.config.center.config.DynamicConfigCenterAutoConfig;
import xyz.zhp.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import xyz.zhp.wrench.dynamic.config.center.types.annotations.DccValue;

/**
 * @author zhp
 * @description
 * @since 2025-05-06 13:36
 */
public interface IDynamicConfigCenterService {

    /**
     * 通过{@link DynamicConfigCenterAutoConfig}实现bean增强操作的拦截操作。
     * 进行dcc属性值的扫描，处理
     * <ul>
     *     <li>
     *         扫描bean，判断是否代理，获取真实的bean，class
     *     </li>
     *     <li>
     *         遍历bean里的所有字段属性，是否携带{@link DccValue}
     *     </li>
     *     <li>
     *         redis缓存是否命中，命中取出，设值，否则使用配置的defaultVal
     *     </li>
     * </ul>
     *
     * @param bean bean
     * @return bean
     */
    Object proxyObject(Object bean);

    /**
     * 更换属性值
     * <ul>
     *     <li>
     *         判断本地缓存
     *     </li>
     *     <li>
     *         通过反射更新
     *     </li>
     *     <li>
     *         redids缓存更新
     *     </li>
     * </ul>
     *
     * @param attributeVO {@link AttributeVO}
     */
    void adjustAttributeValue(AttributeVO attributeVO);

}
