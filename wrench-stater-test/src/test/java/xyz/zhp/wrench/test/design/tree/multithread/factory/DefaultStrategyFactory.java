package xyz.zhp.wrench.test.design.tree.multithread.factory;

import org.springframework.beans.factory.annotation.Qualifier;
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
public class DefaultStrategyFactory {

    private final MultiRootNode multiRootNode;

    public DefaultStrategyFactory(MultiRootNode multiRootNode) {
        this.multiRootNode = multiRootNode;
    }

    public StrategyHandler<String, DynamicContext, String> strategyHandler() {
        return multiRootNode;
    }
}
