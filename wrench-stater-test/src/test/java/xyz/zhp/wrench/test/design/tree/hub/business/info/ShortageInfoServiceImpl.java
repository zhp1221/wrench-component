package xyz.zhp.wrench.test.design.tree.hub.business.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.SHORTAGE_INFO;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:21
 */
@Slf4j
@Service(SeckillBeanConstant.shortageInfo)
public class ShortageInfoServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp resp, Void dynamicContext) {
        resp.setMsg(SHORTAGE_INFO);
    }
}
