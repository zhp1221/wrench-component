package xyz.zhp.wrench.test.design.tree.general.multithread;

import io.github.zhp1221.design.framework.tree.general.AbstractMultiThreadStrategyRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;

/**
 * @author zhp
 * @description 抽象支持类
 * @since 2025-05-07 17:09
 */
public abstract class MutilAbstractXxxSupport extends AbstractMultiThreadStrategyRouter<String, DynamicContext, String> {

    protected final Logger log = LoggerFactory.getLogger(MutilAbstractXxxSupport.class);

    @Override
    protected void multiThread(String requestParameter, DynamicContext dynamicContext) throws Exception {
        // 缺省方法

    }
}
