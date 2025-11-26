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
@Service(SeckillBeanConstant.cityUserPurchased)
public class CityUserPurchasedServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("检测是否是所在城市中");
    }

    @Override
    public Boolean match(OgnlContext ognlContext, String ognl, Void unused) {
        int purchase_count = new Random().nextInt(PURCHASE_COUNT_LIMIT + 1);
        boolean cityFlag = new Random().nextBoolean();
        log.info("城市是否限购：{} 读取用户已购数量~~~, 限购数量：{}, 用户已购数量:{}", cityFlag, PURCHASE_COUNT_LIMIT, purchase_count);
        ognlContext.put("purchase_count", purchase_count);
        ognlContext.put("city_limit", cityFlag);
        return super.match(ognlContext, ognl, unused);
    }
}
