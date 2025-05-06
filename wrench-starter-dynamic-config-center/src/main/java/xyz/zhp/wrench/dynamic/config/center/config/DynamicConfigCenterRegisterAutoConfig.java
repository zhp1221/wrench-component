package xyz.zhp.wrench.dynamic.config.center.config;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.zhp.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import xyz.zhp.wrench.dynamic.config.center.domain.service.DynamicConfigCenterService;
import xyz.zhp.wrench.dynamic.config.center.domain.service.IDynamicConfigCenterService;
import xyz.zhp.wrench.dynamic.config.center.listener.DynamicConfigCenterAdjustListener;
import xyz.zhp.wrench.dynamic.config.center.types.common.Constants;

/**
 * @author zhp
 * @description 注册器配置类（redis）
 * @since 2025-05-06 10:43
 */
@Configuration
@EnableConfigurationProperties(value = {
        DynamicConfigCenterRegisterAutoProperties.class,
        DynamicConfigCenterAutoProperties.class
})
public class DynamicConfigCenterRegisterAutoConfig {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterRegisterAutoConfig.class);

    @Bean("dccWrenchRedisson")
    public RedissonClient redissonClient(DynamicConfigCenterRegisterAutoProperties properties){
        Config config = new Config();

        // 根据需要可以设定编解码器；https://github.com/redisson/redisson/wiki/4.-%E6%95%B0%E6%8D%AE%E5%BA%8F%E5%88%97%E5%8C%96
        config.setCodec(JsonJacksonCodec.INSTANCE);


        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                .setPassword(properties.getPassword())
                .setConnectionPoolSize(properties.getPoolSize())
                .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setIdleConnectionTimeout(properties.getIdleTimeout())
                .setConnectTimeout(properties.getConnectTimeout())
                .setRetryAttempts(properties.getRetryAttempts())
                .setRetryInterval(properties.getRetryInterval())
                .setPingConnectionInterval(properties.getPingInterval())
                .setKeepAlive(properties.isKeepAlive())
        ;

        RedissonClient redissonClient = Redisson.create(config);

        log.info("dcc-wrench，注册器（redis）链接初始化完成。{} {} {}", properties.getHost(), properties.getPoolSize(), !redissonClient.isShutdown());

        return redissonClient;
    }

    @Bean
    public IDynamicConfigCenterService dynamicConfigCenterService(DynamicConfigCenterAutoProperties dynamicConfigCenterAutoProperties, RedissonClient dccWrenchRedisson){
        return new DynamicConfigCenterService(dynamicConfigCenterAutoProperties, dccWrenchRedisson);
    }

    @Bean
    public DynamicConfigCenterAdjustListener dynamicConfigCenterAdjustListener(IDynamicConfigCenterService iDynamicConfigCenterService){
        return new DynamicConfigCenterAdjustListener(iDynamicConfigCenterService);
    }

    @Bean("dccRedisTopic")
    public RTopic a(
            DynamicConfigCenterAutoProperties dynamicConfigCenterAutoProperties,
            DynamicConfigCenterAdjustListener dynamicConfigCenterAdjustListener,
            RedissonClient dccWrenchRedisson
    ){
        RTopic topic = dccWrenchRedisson.getTopic(Constants.getTopic(dynamicConfigCenterAutoProperties.getSystem()));
        topic.addListener(AttributeVO.class, dynamicConfigCenterAdjustListener);
        return topic;
    }


}
