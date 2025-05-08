package xyz.zhp.wrench.test.design.tree.normal.factory;

import org.springframework.stereotype.Service;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.normal.node.RootNode;

/**
 * @author zhp
 * @description
 * @since 2025-05-08 10:00
 */
@Service
public class DefaultStrategyFactory {

    private final RootNode rootNode;

    public DefaultStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<String, DynamicContext, String> get(){
        return rootNode;
    }

}
