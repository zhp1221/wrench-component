package xyz.zhp.wrench.test.design.tree.hub.model;

import lombok.Data;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:06
 */
@Data
public class SeckillReq {

    /**
     * 限流数量
     */
    private Integer limit;

    /**
     * 登录信息
     */
    private UserInfo userInfo;

    /**
     * 是否是机器人刷单
     */
    private Boolean robotFraud;

    /**
     * 库存数量
     */
    private Integer count;
}
