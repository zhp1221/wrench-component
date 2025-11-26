package xyz.zhp.wrench.test.design.tree.hub.business.ticket;

import lombok.extern.slf4j.Slf4j;
import ognl.OgnlContext;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import java.util.Random;

import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.TICKET_SEND;

/**
 *
 *
 * @author zhp
 * @since 2025-11-26 10:23
 */
@Slf4j
@Service(SeckillBeanConstant.ticket)
public class TicketServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("赠与优惠券中~~~");
        s.setMsg(TICKET_SEND);
    }

}
