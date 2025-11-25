package xyz.zhp.wrench.test.design.tree.general.multithread.node;

import io.github.zhp1221.design.framework.tree.general.StrategyHandler;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.general.multithread.MutilAbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 17:33
 */
@Component
public class MultiRootNode extends MutilAbstractXxxSupport {

    private final MutilSwitchNode mutilSwitchNode;

    public MultiRootNode(MutilSwitchNode mutilSwitchNode) {
        this.mutilSwitchNode = mutilSwitchNode;
    }

    @Override
    protected String doApply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        // 根节点信息打印
        log.info("【根节点】规则决策树 userId:{}", requestParameter);
        // 分支预测路由
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return mutilSwitchNode;
    }
}
