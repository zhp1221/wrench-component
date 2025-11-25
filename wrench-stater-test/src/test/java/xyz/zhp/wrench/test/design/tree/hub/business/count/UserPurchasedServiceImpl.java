package xyz.zhp.wrench.test.design.tree.hub.business.count;

import lombok.extern.slf4j.Slf4j;
import ognl.OgnlContext;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import java.util.Random;

import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.PURCHASE_COUNT_LIMIT;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 18:23
 */
@Slf4j
@Service(SeckillBeanConstant.userPurchased)
public class UserPurchasedServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        int purchaseCount = new Random().nextInt(PURCHASE_COUNT_LIMIT + 1);
        log.info("redis 读取用户已购数量~~~, 限购数量：{}, 用户已购数量:{}", PURCHASE_COUNT_LIMIT, purchaseCount);
    }

    @Override
    public Boolean match(OgnlContext ognlContext, String ognl, Void unused) {
        int purchase_count = new Random().nextInt(PURCHASE_COUNT_LIMIT + 1);
        log.info("用户已购数量:{}", purchase_count);
        ognlContext.put("purchase_count", purchase_count);
        return super.match(ognlContext, ognl, unused);
    }
}
