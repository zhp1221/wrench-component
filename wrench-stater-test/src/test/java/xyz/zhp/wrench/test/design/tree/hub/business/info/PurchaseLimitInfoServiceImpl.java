package xyz.zhp.wrench.test.design.tree.hub.business.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.PURCHASE_LIMIT_INFO;

/**
 * 限购信息提示节点
 *
 * @author zhp
 * @since 2025-11-25 17:20
 */
@Slf4j
@Service(SeckillBeanConstant.purchaseLimitInfo)
public class PurchaseLimitInfoServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp resp, Void dynamicContext) {
        resp.setMsg(PURCHASE_LIMIT_INFO);
    }
}