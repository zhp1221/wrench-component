package xyz.zhp.wrench.test.design.tree.hub.business.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:23
 */
@Slf4j
@Service(SeckillBeanConstant.orderMq)
public class OrderMqServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("mq发送中~~~~");
        log.info("redis 添加 该用户购买数量 + 1");
        s.setMsg("请及时付款~");
    }

}