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
public class SeckillFactory {

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
        String defaultExpression = "1==1";
        // 根节点
        RuleTreeNodeVO rootNode = RuleTreeNodeVO.builder()
                .info("秒杀根节点-是否限流")
                .serviceBean(SeckillBeanConstant.rootSeckill)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(limit_a, "excessiveVisitInfoNode", ognl -> ognl.put("user_count_limit", USERS_COUNT_LIMIT)));
                    add(new RuleTreeLineVO(limit_b, "loginNode"));
                }})
                .build();

        // 登录节点
        RuleTreeNodeVO loginNode = RuleTreeNodeVO.builder()
                .info("用户登录节点")
                .serviceBean(SeckillBeanConstant.login)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(login, "loginInfoNode", "用户没登录->结束"));
                    add(new RuleTreeLineVO(defaultExpression, "robotFraudNode"));
                }})
                .build();

        // 机器人刷单节点
        RuleTreeNodeVO robotFraudNode = RuleTreeNodeVO.builder()
                .info("机器人刷单节点")
                .serviceBean(SeckillBeanConstant.robotFraud)
                .nextStrategy(new ArrayList<RuleTreeLineVO>() {{
                    add(new RuleTreeLineVO(robot_fraud, "accountErrorInfoNode", "机器人刷单->结束"));
                    add(new RuleTreeLineVO(defaultExpression, "purchaseLimitNode"));
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
                    add(new RuleTreeLineVO(dedecution, "excessiveVisitInfoNode"));
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
