package xyz.zhp.wrench.test.design.tree.hub.business.inventory;

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
 * @since 2025-11-25 17:21
 */
@Slf4j
@Service(SeckillBeanConstant.inventoryDeduction)
public class InventoryDeductionServiceImpl  extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("扣减中~~~");
    }
    @Override
    public Boolean match(OgnlContext ognlContext, String ognl, Void dynamicContext) {
        // 扣减结果
        ognlContext.put("deduction_flag", new Random().nextBoolean());
        return super.match(ognlContext, ognl, dynamicContext);
    }
}

