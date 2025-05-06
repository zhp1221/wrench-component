package xyz.zhp.wrench.dynamic.config.center.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.zhp.wrench.dynamic.config.center.domain.service.IDynamicConfigCenterService;

/**
 * @author zhp
 * @description 系统参数配置类
 * @since 2025-05-06 10:43
 */
@Configuration
public class DynamicConfigCenterAutoConfig implements BeanPostProcessor {

    private final IDynamicConfigCenterService iDynamicConfigCenterService;

    public DynamicConfigCenterAutoConfig(IDynamicConfigCenterService iDynamicConfigCenterService) {
        this.iDynamicConfigCenterService = iDynamicConfigCenterService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return iDynamicConfigCenterService.proxyObject(bean);
    }
}
