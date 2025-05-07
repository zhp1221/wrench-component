package xyz.zhp.wrench.test.design.tree.multithread.node;

import org.springframework.stereotype.Component;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.AbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 17:43
 */
@Component
public class MutilSwitchNode extends AbstractXxxSupport {

    @Override
    protected String doApply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("【开关节点】规则决策树 userId:{}", requestParameter);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return null;
    }
}
