package xyz.zhp.wrench.test.design.tree.hub;

import io.github.zhp1221.design.framework.tree.hub.structure.RuleTreeStructure;
import xyz.zhp.wrench.test.design.tree.hub.factory.SeckillFactory;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 18:01
 */
public class StructureTest {

    public static void main(String[] args) {
        RuleTreeStructure.structure(SeckillFactory.init());
    }
}
