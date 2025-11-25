package xyz.zhp.wrench.test.design.tree.general.multithread.node.last;

import com.alibaba.fastjson.JSON;
import io.github.zhp1221.design.framework.tree.general.StrategyHandler;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.general.multithread.MutilAbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 18:00
 */
@Component
public class MultiMemberLevel1Node extends MutilAbstractXxxSupport {

    @Override
    protected String doApply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("【级别节点-1】规则决策树 userId:{}", requestParameter);
        return "level1" + JSON.toJSONString(dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
