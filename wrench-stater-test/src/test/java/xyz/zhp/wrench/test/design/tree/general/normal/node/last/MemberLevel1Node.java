package xyz.zhp.wrench.test.design.tree.general.normal.node.last;

import com.alibaba.fastjson.JSON;
import io.github.zhp1221.design.framework.tree.general.StrategyHandler;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.general.normal.AbstractXxxSupport;

import static io.github.zhp1221.design.framework.tree.general.StrategyHandler.DEFAULT;

/**
 * @author zhp
 * @description
 * @since 2025-05-08 10:07
 */
@Component
public class MemberLevel1Node extends AbstractXxxSupport {
    @Override
    public String apply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("【级别节点-1】规则决策树 userId:{}",requestParameter);
        return "level1" + JSON.toJSONString(dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        return DEFAULT;
    }
}
