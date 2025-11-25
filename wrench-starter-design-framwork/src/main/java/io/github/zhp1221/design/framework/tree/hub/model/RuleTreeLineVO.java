package io.github.zhp1221.design.framework.tree.hub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ognl.OgnlContext;

import java.util.function.Consumer;

/**
 * 规则场景
 *
 * @author zhp
 * @since 2025-11-25 16:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeLineVO {

    // 信息
    private String msg;

    // 规则Key节点 From
    private String ruleNodeFrom;

    // 下一跳规则点
    private String ruleNodeTo;

    // ognl 表达式
    private String ognlExpression;

    // 表达式
    private Consumer<OgnlContext> consumer;

    public RuleTreeLineVO(String ognlExpression, String ruleNodeTo, String msg) {
        this.ognlExpression = ognlExpression;
        this.ruleNodeTo = ruleNodeTo;
        this.msg = msg;
    }

    public RuleTreeLineVO(String ognlExpression, String ruleNodeTo) {
        this.ognlExpression = ognlExpression;
        this.ruleNodeTo = ruleNodeTo;
    }

    public RuleTreeLineVO(String ognlExpression, String ruleNodeTo, Consumer<OgnlContext> consumer) {
        this.ognlExpression = ognlExpression;
        this.ruleNodeTo = ruleNodeTo;
        this.consumer = consumer;
    }

    public RuleTreeLineVO(String ognlExpression, String ruleNodeTo, Consumer<OgnlContext> consumer, String msg) {
        this.ognlExpression = ognlExpression;
        this.ruleNodeTo = ruleNodeTo;
        this.consumer = consumer;
        this.msg = msg;
    }

}
