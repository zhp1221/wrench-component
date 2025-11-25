package xyz.zhp.wrench.test.design.tree.hub.business.inventory;

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
@Service(SeckillBeanConstant.inventoryQry)
public class InventoryQryServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("当前库存数量：{}", secKillReq.getCount());
    }
}
