package xyz.zhp.wrench.test.design.tree.multithread.factory;

import org.springframework.stereotype.Service;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.node.MultiRootNode;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 17:21
 */
@Service
public class MultiDefaultStrategyFactory {

    private final MultiRootNode multiRootNode;

    public MultiDefaultStrategyFactory(MultiRootNode multiRootNode) {
        this.multiRootNode = multiRootNode;
    }

    public StrategyHandler<String, DynamicContext, String> strategyHandler() {
        return multiRootNode;
    }
}
