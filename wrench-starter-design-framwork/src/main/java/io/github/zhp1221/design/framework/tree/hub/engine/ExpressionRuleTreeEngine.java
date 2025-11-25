package io.github.zhp1221.design.framework.tree.hub.engine;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import io.github.zhp1221.design.framework.tree.hub.StrategyHubHandler;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeEntryPointVO;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeLineVO;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeNodeVO;
import io.github.zhp1221.design.framework.tree.hub.singleton.SingletonTag;
import lombok.extern.slf4j.Slf4j;
import ognl.Ognl;
import ognl.OgnlContext;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:49
 */
@SuppressWarnings("all")
public class ExpressionRuleTreeEngine {
    public final OgnlContext ognlContext;
    private final RuleTreeEntryPointVO ruleTreeEntryPointVO;

    public ExpressionRuleTreeEngine(RuleTreeEntryPointVO ruleTreeEntryPointVO) {
        this.ruleTreeEntryPointVO = ruleTreeEntryPointVO;
        this.ognlContext = Ognl.createDefaultContext(SingletonTag.getInstance());
    }

    public <REQ, RESP, D, T extends StrategyHubHandler> RESP process(REQ req, RESP resp, D dynamicContext, Map<String, T> logicTreeGroup) {

        try {
            // 前置参数
            String nextNode = ruleTreeEntryPointVO.getTreeRootRuleNode();
            Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeEntryPointVO.getTreeNodeMap();
            RuleTreeNodeVO ruleTree = treeNodeMap.get(nextNode);

            // 开始处理
            while (nextNode != null) {

                StaticLog.info("=================={}===================", nextNode);

                // 业务类获取
                StrategyHubHandler iTestRuleTree = logicTreeGroup.get(ruleTree.getServiceBean());

                if (iTestRuleTree == null) {
                    StaticLog.info("策略为空，结束规则树");
                    break;
                }
                // 业务处理
                iTestRuleTree.handle(req, resp, dynamicContext);

                // 处理 & 获取下一个规则节点
                ruleTree = treeNodeMap.get(nextNode = logic(ruleTree, iTestRuleTree, dynamicContext));
            }
        } catch (Exception e) {

            StaticLog.error("[执行失败] e:{}", e.getMessage(), e);

            throw new IllegalArgumentException(e.getMessage());

        } finally {

            ognlContext.clear();
        }

        return resp;
    }

    private <REQ, RESP, D> String logic(RuleTreeNodeVO ruleTree, StrategyHubHandler iTestRuleTree, D dynamicContext) {
        // 获取下一个节点
        List<RuleTreeLineVO> nextStrategy = ruleTree.getNextStrategy();
        if (IterUtil.isEmpty(nextStrategy)) {
            return null;
        }
        // 走业务场景的判断
        for (RuleTreeLineVO x : nextStrategy) {
            if (StrUtil.isBlank(x.getOgnlExpression())) {
                return null;
            }
            // 自定义元数据
            if (x.getConsumer() != null) {
                x.getConsumer().accept(this.ognlContext);
            }
            // 匹配
            if (iTestRuleTree.match(this.ognlContext, x.getOgnlExpression(), dynamicContext)) {
                return x.getRuleNodeTo();
            }
        }

        StaticLog.error("业务匹配规则失败，请检查, nextStrategy:{}", log(nextStrategy));
        throw new RuntimeException("规则树引擎，nextNode 计算失败，未找到可执行节点！");
    }


    public <T> String log(T t) {
        if (ObjectUtil.isEmpty(t)) {
            return "";
        }
        return JSONUtil.toJsonStr(t);
    }


}
