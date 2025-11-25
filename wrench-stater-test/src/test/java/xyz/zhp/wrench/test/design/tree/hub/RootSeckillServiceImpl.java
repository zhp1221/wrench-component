package xyz.zhp.wrench.test.design.tree.hub;

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
 * @since 2025-11-25 18:21
 */
@Slf4j
@Service(SeckillBeanConstant.rootSeckill)
public class RootSeckillServiceImpl  extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {

        log.info("秒杀接口~, 当前访问数量:{}", secKillReq.getLimit());

    }
}
