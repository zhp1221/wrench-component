package xyz.zhp.wrench.test.design.tree.hub.factory;

import io.github.zhp1221.design.framework.tree.hub.engine.ExpressionRuleTreeEngine;
import io.github.zhp1221.design.framework.tree.hub.factory.DefaultFactory;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeEntryPointVO;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeLineVO;
import io.github.zhp1221.design.framework.tree.hub.model.RuleTreeNodeVO;
import io.github.zhp1221.design.framework.tree.hub.template.RuleTreeHubTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhp.wrench.test.design.tree.hub.business.AbstractSeckillRuleSupport;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillBeanConstant;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static xyz.zhp.wrench.test.design.tree.hub.model.HubTemplateEnum.SECKILL_TREE;
import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.PURCHASE_COUNT_LIMIT;
import static xyz.zhp.wrench.test.design.tree.hub.model.SeckillConstant.USERS_COUNT_LIMIT;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 17:56
 */
@Service
public class SeckillUpgradeFactory {

    @Autowired
    private Map<String, AbstractSeckillRuleSupport> ruleTreeGroupMap;

    public static RuleTreeEntryPointVO init() {
        String limit_a = "#seckillreq.limit >= #user_count_limit";
        String limit_b = "#seckillreq.limit < #user_count_limit";
        String login = "@org.apache.commons.lang3.ObjectUtils@isEmpty(#seckillreq.userInfo.userId)";
        String robot_fraud = "#seckillreq.robotFraud == true";
        String purchase_limit = "#purchase_count >= #purchase_count_limit";
        String storage_count = "#seckillreq.count == 0";
        String dedecution = "#deduction_flag == false";
        String account_check = "#account_check == true";
        String defaultExpression = "1==1";

        // 新增
        String userIdentityFlag = "#user_identity_check == true";
        String orderPuchased="#order_purchased == true";
        String frequentFlag = "#frequent_flag == true";
        String city_limit = "#city_limit == true";
        // 根节点
        RuleTreeNodeVO rootNode = RuleTreeNodeVO.builder()
                .info("秒杀根节点-是否限流")
                .serviceBean(SeckillBeanConstant.rootSeckill)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    // if
                    add(new RuleTreeLineVO(limit_a, "userIdentityCheckNode", ognl -> ognl.put("user_count_limit", USERS_COUNT_LIMIT)));
                    // else
                    add(new RuleTreeLineVO(limit_a, "excessiveVisitInfoNode", ognl -> ognl.put("excessiveVisitInfoNode", USERS_COUNT_LIMIT)));
                    // 兜底
                    add(new RuleTreeLineVO(limit_b, "loginNode"));
                }})
                .build();

        // 登录节点
        RuleTreeNodeVO loginNode = RuleTreeNodeVO.builder()
                .info("用户登录节点")
                .serviceBean(SeckillBeanConstant.login)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(login, "loginInfoNode", "用户没登录->结束"));
                    add(new RuleTreeLineVO(defaultExpression, "accountCheckNode"));
                }})
                .build();

        // 机器人刷单节点
        RuleTreeNodeVO robotFraudNode = RuleTreeNodeVO.builder()
                .info("机器人刷单节点")
                .serviceBean(SeckillBeanConstant.robotFraud)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(robot_fraud, "frequentCheckNode"));
                    add(new RuleTreeLineVO(defaultExpression, "cityUserNode"));
                }})
                .build();

        // 限购数量节点
        RuleTreeNodeVO purchaseLimitNode = RuleTreeNodeVO.builder()
                .info("限购数量节点")
                .serviceBean(SeckillBeanConstant.userPurchased)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(purchase_limit, "purchaseLimitInfoNode", ognl -> ognl.put("purchase_count_limit", PURCHASE_COUNT_LIMIT)));
                    add(new RuleTreeLineVO(defaultExpression, "inventoryQryNode"));
                }})
                .build();

        RuleTreeNodeVO inventoryQryNode = RuleTreeNodeVO.builder()
                .info("库存查询节点")
                .serviceBean(SeckillBeanConstant.inventoryQry)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(storage_count, "shortageInfoNode"));
                    add(new RuleTreeLineVO(defaultExpression, "inventoryDeductionNode"));
                }})
                .build();

        RuleTreeNodeVO inventoryDeductionNode = RuleTreeNodeVO.builder()
                .info("库存扣减节点")
                .serviceBean(SeckillBeanConstant.inventoryDeduction)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    // if
                    add(new RuleTreeLineVO(dedecution, "userIdentityCheckNode"));
                    // 兜底
                    add(new RuleTreeLineVO(defaultExpression, "orderMqNode"));
                }})
                .build();

        RuleTreeNodeVO orderMqNode = RuleTreeNodeVO.builder()
                .info("订单创建节点")
                .serviceBean(SeckillBeanConstant.orderMq)
                .build();

        // ========================info===========================
        RuleTreeNodeVO excessiveVisitInfoNode = RuleTreeNodeVO.builder()
                .info("限流信息节点")
                .serviceBean(SeckillBeanConstant.excessiveVisitInfo)
                .build();

        RuleTreeNodeVO loginInfoNode = RuleTreeNodeVO.builder()
                .info("用户未登录信息提示节点")
                .serviceBean(SeckillBeanConstant.loginInfo)
                .build();

        RuleTreeNodeVO accountErrorInfoNode = RuleTreeNodeVO.builder()
                .info("用户账号异常提示节点")
                .serviceBean(SeckillBeanConstant.accountErrorInfo)
                .build();

        RuleTreeNodeVO purchaseLimitInfoNode = RuleTreeNodeVO.builder()
                .info("限购信息提示节点")
                .serviceBean(SeckillBeanConstant.purchaseLimitInfo)
                .build();

        RuleTreeNodeVO shortageInfoNode = RuleTreeNodeVO.builder()
                .info("已售罄信息提示节点")
                .serviceBean(SeckillBeanConstant.shortageInfo)
                .build();

        // =====================新增需求==============================
        RuleTreeNodeVO userIdentityCheckNode = RuleTreeNodeVO.builder()
                .info("检测用户，无刷单&&vip")
                .serviceBean(SeckillBeanConstant.userIdentityCheck)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 发送优惠券
                    add(new RuleTreeLineVO(userIdentityFlag, "ticketSendNode"));
                    // 访问频繁
                    add(new RuleTreeLineVO(defaultExpression, "excessiveVisitInfoNode"));
                }})
                .build();

        RuleTreeNodeVO ticketSendNode = RuleTreeNodeVO.builder()
                .info("发送优惠券")
                .serviceBean(SeckillBeanConstant.ticket)
                .build();

        RuleTreeNodeVO accountCheckNode = RuleTreeNodeVO.builder()
                .info("是否是封禁账号")
                .serviceBean(SeckillBeanConstant.accountCheck)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 账号异常返回
                    add(new RuleTreeLineVO(account_check, "accountErrorInfoNode"));
                    // 是否刷单
                    add(new RuleTreeLineVO(defaultExpression, "robotFraudNode"));
                }})
                .build();



        RuleTreeNodeVO frequentCheckNode = RuleTreeNodeVO.builder()
                .info("是否经常刷单")
                .serviceBean(SeckillBeanConstant.robotFraudFrequent)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 用户身份检测
                    add(new RuleTreeLineVO(userIdentityFlag, "ticketSendNode"));
                    // 访问频繁
                    add(new RuleTreeLineVO(defaultExpression, "excessiveVisitInfoNode"));
                }})
                .build();

        RuleTreeNodeVO orderPurchasedNode = RuleTreeNodeVO.builder()
                .info("是否有已下单")
                .serviceBean(SeckillBeanConstant.orderPurchasedCheck)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 订单取消
                    add(new RuleTreeLineVO(orderPuchased, "orderCancelNode"));
                    // 冻结账号
                    add(new RuleTreeLineVO(defaultExpression, "frozenAccountNode"));
                }})
                .build();

        RuleTreeNodeVO orderCancelNode = RuleTreeNodeVO.builder()
                .info("订单取消")
                .serviceBean(SeckillBeanConstant.orderCancel)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 冻结账号
                    add(new RuleTreeLineVO(defaultExpression, "frozenAccountNode"));
                }})
                .build();

        RuleTreeNodeVO frozenAccountNode = RuleTreeNodeVO.builder()
                .info("冻结账号中")
                .serviceBean(SeckillBeanConstant.frozenAccount)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 账号异常返回
                    add(new RuleTreeLineVO(defaultExpression, "accountErrorInfoNode"));
                }})
                .build();

        RuleTreeNodeVO cityUserNode = RuleTreeNodeVO.builder()
                .info("城市限购检测中")
                .serviceBean(SeckillBeanConstant.cityUserPurchased)
                .nextStrategy(new ArrayList<RuleTreeLineVO>(){{
                    // 城市限购判断
                    add(new RuleTreeLineVO(city_limit, "purchaseLimitInfoNode"));
                    // 限购判断
                    add(new RuleTreeLineVO(defaultExpression, "purchaseLimitNode"));
                }})
                .build();


        // =================组装========================
        // =================调用========================
        Map<String, RuleTreeNodeVO> ruleTreeMap = new HashMap<String, RuleTreeNodeVO>() {{
            put("rootNode", rootNode);
            put("loginNode", loginNode);
            put("robotFraudNode", robotFraudNode);
            put("purchaseLimitNode", purchaseLimitNode);
            put("inventoryQryNode", inventoryQryNode);
            put("inventoryDeductionNode", inventoryDeductionNode);
//            put("userPurchasedNode", userPurchasedNode);
            put("orderMqNode", orderMqNode);
            put("excessiveVisitInfoNode", excessiveVisitInfoNode);
            put("loginInfoNode", loginInfoNode);
            put("accountErrorInfoNode", accountErrorInfoNode);
            put("purchaseLimitInfoNode", purchaseLimitInfoNode);
            put("shortageInfoNode", shortageInfoNode);
            put("userIdentityCheckNode", userIdentityCheckNode);
            put("ticketSendNode", ticketSendNode);
            put("accountCheckNode", accountCheckNode);
            put("frequentCheckNode", frequentCheckNode);
            put("orderPurchasedNode", orderPurchasedNode);
            put("orderCancelNode", orderCancelNode);
            put("frozenAccountNode", frozenAccountNode);
        }};

        return RuleTreeEntryPointVO.builder()
                .id(SECKILL_TREE.getId())
                .treeName("秒杀场景树")
                .treeDesc("用于电商场景，库存秒杀的规则树")
                .treeRootRuleNode("rootNode")
                .treeNodeMap(ruleTreeMap)
                .build();
    }

    public SeckillResp start(SeckillReq req) {
        ExpressionRuleTreeEngine open = DefaultFactory.open(RuleTreeHubTemplate.get(SECKILL_TREE.getId()));

        open.ognlContext.put("seckillreq", req);

        SeckillResp secKillResp = new SeckillResp();

        open.process(req, secKillResp, null, ruleTreeGroupMap);

        return secKillResp;
    }

    @PostConstruct
    public void initSecKill() {

        // 初始化
        RuleTreeEntryPointVO init = init();

        // 添加节点
        RuleTreeHubTemplate.put(SECKILL_TREE.getId(), init);
    }
}
