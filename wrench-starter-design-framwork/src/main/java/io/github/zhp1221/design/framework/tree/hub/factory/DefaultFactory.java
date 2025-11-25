package io.github.zhp1221.design.framework.tree.hub.factory;

import io.github.zhp1221.design.framework.tree.hub.engine.ExpressionRuleTreeEngine;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeEntryPointVO;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:49
 */
public class DefaultFactory {
    /**
     * 根据业务模板配置id，获取启动引擎
     *
     * @param ruleTreeEntryPointVO
     * @return
     */
    public static ExpressionRuleTreeEngine open(RuleTreeEntryPointVO ruleTreeEntryPointVO) {
        return new ExpressionRuleTreeEngine(ruleTreeEntryPointVO);
    }
}
