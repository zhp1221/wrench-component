package xyz.zhp.wrench.test.design.tree;

import org.junit.Test;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.AppTest;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.factory.MultiDefaultStrategyFactory;
import xyz.zhp.wrench.test.design.tree.normal.factory.DefaultStrategyFactory;

import javax.annotation.Resource;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 18:02
 */
public class DesignFrameworkTest extends AppTest {

    @Resource
    private MultiDefaultStrategyFactory multiDefaultStrategyFactory;

    @Resource
    private DefaultStrategyFactory defaultStrategyFactory;

    @Test
    public void multiTree() throws Exception{
        StrategyHandler<String, DynamicContext, String> strategyHandler = multiDefaultStrategyFactory.strategyHandler();

        String result = strategyHandler.apply("zhp", new DynamicContext());

        log.info("测试结果:{}", result);

    }

    @Test
    public void tree() throws Exception{
        StrategyHandler<String, DynamicContext, String> strategyHandler = defaultStrategyFactory.get();

        String result = strategyHandler.apply("zhp", new DynamicContext());

        log.info("测试结果:{}", result);
    }
}
