package xyz.zhp.wrench.test.design.tree.hub.model;

import lombok.experimental.UtilityClass;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:09
 */
public class SeckillConstant {

    public static final String ACCOUNT_ERROR = "账号存在刷单型为";
    public static final String EXCESSIVE_VISIT_INFO = "当前访问过多";
    public static final String LOGIN_INFO = "请您先登录";
    public static final String PURCHASE_LIMIT_INFO = "您已达最大限购数量，请期待下一次活动";
    public static final String SHORTAGE_INFO = "已售罄";
    public static final String WAIT_PAY = "请及时付款";
    public static final String TICKET_SEND = "由于您未抢购到，赠送您一张优惠券";

    // 限购数量
    public static final Integer PURCHASE_COUNT_LIMIT = 2;
    // 限流数量
    public static final Integer USERS_COUNT_LIMIT = 1_000;
}
