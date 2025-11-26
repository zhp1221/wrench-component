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
@Service(SeckillBeanConstant.orderPurchasedCheck)
public class OrderPurchasedCheckServiceImpl  extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("检测该用户，是否有已下单");
    }

    @Override
    public Boolean match(OgnlContext ognlContext, String ognl, Void unused) {
        boolean flag = new Random().nextBoolean();
        log.info("已下单标志:{}", flag);
        ognlContext.put("order_purchased", flag);
        return super.match(ognlContext, ognl, unused);
    }
}
