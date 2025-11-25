package xyz.zhp.wrench.test.design.tree.general.multithread.node;

import io.github.zhp1221.design.framework.tree.general.StrategyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.general.multithread.MutilAbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 17:43
 */
@Component
public class MutilSwitchNode extends MutilAbstractXxxSupport {

    @Autowired
    private MutilAccountNode mutilAccountNode;

    @Override
    protected String doApply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("【开关节点】规则决策树 userId:{}", requestParameter);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return mutilAccountNode;
    }
}
