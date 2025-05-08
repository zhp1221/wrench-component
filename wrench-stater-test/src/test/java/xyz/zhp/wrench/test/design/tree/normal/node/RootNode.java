package xyz.zhp.wrench.test.design.tree.normal.node;

import org.springframework.stereotype.Component;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.normal.AbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-08 10:03
 */
@Component
public class RootNode extends AbstractXxxSupport {

    private final SwitchNode switchNode;

    public RootNode(SwitchNode switchNode) {
        this.switchNode = switchNode;
    }

    @Override
    public String apply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("【根节点】规则决策树 userId:{}", requestParameter);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return switchNode;
    }
}
