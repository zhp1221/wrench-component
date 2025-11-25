package xyz.zhp.wrench.test.design.tree.hub.business.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.EXCESSIVE_VISIT_INFO;

/**
 * 访问人数过多提示节点
 *
 * @author zhp
 * @since 2025-11-25 17:19
 */
@Slf4j
@Service(SeckillBeanConstant.excessiveVisitInfo)
public class ExcessiveVisitInfoServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp resp, Void dynamicContext) {
        resp.setMsg(EXCESSIVE_VISIT_INFO);
    }
}