package xyz.zhp.wrench.test.design.tree.hub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:59
 */
@Getter
@AllArgsConstructor
public enum HubTemplateEnum {
    DEFAULT("-1"),
    SECKILL_TREE("1"),
            ;
    public final String id;
}
