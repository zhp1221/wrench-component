package io.github.zhp1221.design.framework.tree.hub.template;

import cn.hutool.core.util.StrUtil;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeEntryPointVO;
import lombok.experimental.UtilityClass;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:59
 */
@UtilityClass
public class RuleTreeHubTemplate {
    public static final ConcurrentHashMap<String, RuleTreeEntryPointVO> TEMPLATE_MAP = new ConcurrentHashMap<>(16);

    public void put(String id, RuleTreeEntryPointVO entryPointVO) {
        if (StrUtil.isBlank(id) || entryPointVO == null) {
            throw new IllegalArgumentException("模板id/规则启动点，不能为空");
        }
        TEMPLATE_MAP.put(id, entryPointVO);
    }

    public RuleTreeEntryPointVO get(String id) {
        RuleTreeEntryPointVO ruleTreeEntryPointVO;
        if ((ruleTreeEntryPointVO = TEMPLATE_MAP.get(id)) == null) {
            throw new IllegalArgumentException("非法规则id, 请适配");
        }
        return ruleTreeEntryPointVO;
    }
}
