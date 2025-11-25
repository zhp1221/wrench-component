package xyz.zhp.wrench.test.design.tree.hub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SeckillResp {
    /**
     * 提示信息
     */
    private String msg;
}
