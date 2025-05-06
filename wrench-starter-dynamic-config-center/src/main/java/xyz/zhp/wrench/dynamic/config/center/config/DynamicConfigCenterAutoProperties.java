package xyz.zhp.wrench.dynamic.config.center.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import xyz.zhp.wrench.dynamic.config.center.types.common.Constants;

/**
 * @author zhp
 * @description 系统参数配置类
 * @since 2025-05-06 10:43
 */
@ConfigurationProperties(value = "dcc.wrench.config", ignoreInvalidFields = true)
public class DynamicConfigCenterAutoProperties {

    /**
     * 系统名称
     */
    private String system;

    /**
     * 属性redis key
     *
     * @param attributeName 属性名称
     * @return key
     */
    public String getKey(String attributeName){
        return system + Constants.SYMBOL_LINE + attributeName;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
