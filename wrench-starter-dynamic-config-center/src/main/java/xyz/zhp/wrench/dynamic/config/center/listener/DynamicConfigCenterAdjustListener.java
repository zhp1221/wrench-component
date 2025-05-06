package xyz.zhp.wrench.dynamic.config.center.listener;

import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zhp.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import xyz.zhp.wrench.dynamic.config.center.domain.service.IDynamicConfigCenterService;

/**
 * @author zhp
 * @description 事件订阅机制
 * @since 2025-05-06 14:16
 */
public class DynamicConfigCenterAdjustListener implements MessageListener<AttributeVO> {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterAdjustListener.class);

    private final IDynamicConfigCenterService dynamicConfigCenterService;

    public DynamicConfigCenterAdjustListener(IDynamicConfigCenterService dynamicConfigCenterService) {
        this.dynamicConfigCenterService = dynamicConfigCenterService;
    }

    @Override
    public void onMessage(CharSequence charSequence, AttributeVO attributeVO) {

        try {
            log.info("wrench dcc config attribute:{} value:{}", attributeVO.getAttribute(), attributeVO.getVal());
            dynamicConfigCenterService.adjustAttributeValue(attributeVO);
        } catch (Exception e) {
            log.error("wrench dcc config err, attribute:{} value:{}", attributeVO.getAttribute(), attributeVO.getVal());
        }

    }
}
