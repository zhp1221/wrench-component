package xyz.zhp.wrench.test.design.tree.hub.business.info;

import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.LOGIN_INFO;

/**
 * 未登录信息节点
 *
 * @author zhp
 * @since 2025-11-25 17:20
 */
@Service(SeckillBeanConstant.loginInfo)
public class LoginInfoServiceImpl extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp resp, Void dynamicContext) {
        resp.setMsg(LOGIN_INFO);
    }
}