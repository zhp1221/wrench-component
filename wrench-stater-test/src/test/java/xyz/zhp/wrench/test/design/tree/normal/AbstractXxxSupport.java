package xyz.zhp.wrench.test.design.tree.normal;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.routine.AbstractRoutines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zhp.wrench.design.framwork.tree.AbstractStrategyRouter;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.MutilAbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-08 10:01
 */
public abstract class AbstractXxxSupport extends AbstractStrategyRouter<String, DynamicContext, String> {

    protected final Logger log = LoggerFactory.getLogger(AbstractXxxSupport.class);

}
