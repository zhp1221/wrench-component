package xyz.zhp.wrench.dynamic.config.center.types.common;

/**
 * @author zhp
 * @description
 * @since 2025-05-06 11:07
 */
public class Constants {

    public static final String DYNAMIC_CONFIG_CENTER_REDIS_TOPIC = "DYNAMIC_CONFIG_CENTER_REDIS_TOPIC:";

    public static final String SYMBOL_COLON = ":";

    public static final String SYMBOL_LINE = "_";

    /**
     * dcc redis message topic
     *
     * @param applicationName 系统名称
     * @return redis key
     */
    public static String getTopic(String applicationName){
        return DYNAMIC_CONFIG_CENTER_REDIS_TOPIC + applicationName;
    }
}
