package io.github.zhp1221.design.framework.tree.hub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 引擎启动点
 *
 * @author zhp
 * @since 2025-11-25 16:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeEntryPointVO {
    // 模板id
    private String id;
    // 名称
    private String treeName;
    // 描述
    private String treeDesc;
    // 引擎节点
    private String treeRootRuleNode;
    // 节点Map
    private Map<String, RuleTreeNodeVO> treeNodeMap;
}
