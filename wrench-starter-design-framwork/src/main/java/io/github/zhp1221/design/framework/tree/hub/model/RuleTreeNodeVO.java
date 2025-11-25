package io.github.zhp1221.design.framework.tree.hub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 业务处理
 *
 * @author zhp
 * @since 2025-11-25 16:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeVO {

    // 当前节点
    private String info;

    // bean 名
    private String serviceBean;

    // 分支预测
    private List<RuleTreeLineVO> nextStrategy;

}
