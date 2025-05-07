package xyz.zhp.wrench.test.design.tree.multithread;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zhp.wrench.design.framwork.tree.AbstractMultiThreadStrategyRouter;
import xyz.zhp.wrench.dynamic.config.center.config.DynamicConfigCenterAutoConfig;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.factory.DefaultStrategyFactory;

/**
 * @author zhp
 * @description 抽象支持类
 * @since 2025-05-07 17:09
 */
public abstract class AbstractXxxSupport extends AbstractMultiThreadStrategyRouter<String, DynamicContext, String> {

    protected final Logger log = LoggerFactory.getLogger(AbstractXxxSupport.class);

    @Override
    protected void multiThread(String requestParameter, DynamicContext dynamicContext) throws Exception {
        // 缺省方法

    }
}
