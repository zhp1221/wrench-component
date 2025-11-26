package xyz.zhp.wrench.test.design.tree.hub.business.rule;

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
 * @since 2025-11-26 10:10
 */
@Slf4j
@Service(SeckillBeanConstant.robotFraudFrequent)
public class RobotFraudFrequentServiceImpl  extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("是否频繁刷单检测中");
    }

    @Override
    public Boolean match(OgnlContext ognlContext, String ognl, Void unused) {
        ognlContext.put("frequent_flag", new Random().nextBoolean());
        return super.match(ognlContext, ognl, unused);
    }
}
