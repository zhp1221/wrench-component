package xyz.zhp.wrench.test.design.tree.general.normal;

import io.github.zhp1221.design.framework.tree.general.AbstractStrategyRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;

/**
 * @author zhp
 * @description
 * @since 2025-05-08 10:01
 */
public abstract class AbstractXxxSupport extends AbstractStrategyRouter<String, DynamicContext, String> {

    protected final Logger log = LoggerFactory.getLogger(AbstractXxxSupport.class);

}
