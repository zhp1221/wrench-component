package xyz.zhp.wrench.test.design.tree.hub.business.rule;

import cn.hutool.json.JSONUtil;
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
@Service(SeckillBeanConstant.login)
public class LoginServiceImpl  extends AbstractSeckillRuleSupport {

    @Override
    public void handle(SeckillReq secKillReq, SeckillResp s, Void dynamicContext) {
        log.info("登录信息:{}",  secKillReq.getUserInfo() == null ? "" : JSONUtil.toJsonPrettyStr( secKillReq.getUserInfo()));
    }
}