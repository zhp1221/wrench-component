package io.github.zhp1221.design.framework.tree.hub.structure;

import cn.hutool.core.collection.IterUtil;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeEntryPointVO;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeLineVO;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeNodeVO;
import io.github.zhp1221.design.framework.tree.hub.template.RuleTreeHubTemplate;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:43
 */
@Slf4j
@UtilityClass
public class RuleTreeStructure {

    /**
     * 根据模板id，打印规则结构
     *
     * @param templateId 模板id
     */
    public void structure(String templateId) {
        structure(RuleTreeHubTemplate.get(templateId));
    }

    /**
     * 根据启动点，打印规则结构
     *
     * @param ruleTreeEntryPointVO 启动点
     */
    public void structure(RuleTreeEntryPointVO ruleTreeEntryPointVO) {
        String treeRootRuleNode = ruleTreeEntryPointVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeEntryPointVO.getTreeNodeMap();
        recursion(treeRootRuleNode, 0, treeNodeMap);
    }

    /**
     * 递归
     *
     * @param node           节点名称
     * @param recursionDepth 递归深度
     * @param treeNodeMap    节点map
     */
    private void recursion(String node, int recursionDepth, Map<String, RuleTreeNodeVO> treeNodeMap) {
        RuleTreeNodeVO ruleTreeNodeVO = treeNodeMap.get(node);
        log.info(printContext(recursionDepth, String.format("[%s]", node)));

        List<RuleTreeLineVO> nextStrategy;

        if (ruleTreeNodeVO == null || IterUtil.isEmpty(nextStrategy = ruleTreeNodeVO.getNextStrategy())) {
            return;
        }

        for (RuleTreeLineVO x : nextStrategy) {
            recursion(x.getRuleNodeTo(), recursionDepth + 1, treeNodeMap);
        }
    }

    /**
     * 空格制空，打印信息
     *
     * @param count   空格分割
     * @param context 信息
     * @return 结果
     */
    private static String printContext(int count, String context) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("      ");
        }
        return sb + "|---->" + context;
    }
}
