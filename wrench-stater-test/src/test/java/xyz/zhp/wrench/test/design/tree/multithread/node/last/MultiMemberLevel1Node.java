package xyz.zhp.wrench.test.design.tree.multithread.node.last;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.AbstractXxxSupport;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 18:00
 */
@Component
public class MultiMemberLevel1Node extends AbstractXxxSupport {

    @Override
    protected String doApply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("【级别节点-1】规则决策树 userId:{}",requestParameter);
        return "level1" + JSON.toJSONString(dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
