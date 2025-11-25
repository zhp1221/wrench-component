package xyz.zhp.wrench.test.design.tree.general.normal.factory;

import io.github.zhp1221.design.framework.tree.general.StrategyHandler;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.general.normal.node.RootNode;

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

    public StrategyHandler<String, DynamicContext, String> get() {
        return rootNode;
    }

}
