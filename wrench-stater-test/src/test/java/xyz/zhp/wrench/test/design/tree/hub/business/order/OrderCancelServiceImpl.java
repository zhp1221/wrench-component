package xyz.zhp.wrench.test.design.tree.hub.business.order;

import lombok.extern.slf4j.Slf4j;
import ognl.OgnlContext;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import java.util.Random;

/**
 *
 *
 * @author zhp
 * @since 2025-11-26 10:17
 */
@Slf4j
@Service(SeckillBeanConstant.orderCancel)
public class OrderCancelServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {

        log.info("已购订单取消中");
    }
}
