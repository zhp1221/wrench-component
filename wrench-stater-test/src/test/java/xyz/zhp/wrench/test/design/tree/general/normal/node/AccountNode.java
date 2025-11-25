package xyz.zhp.wrench.test.design.tree.general.normal.node;

import io.github.zhp1221.design.framework.tree.general.StrategyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.test.design.tree.general.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.general.normal.AbstractXxxSupport;
import xyz.zhp.wrench.test.design.tree.general.normal.node.last.MemberLevel1Node;
import xyz.zhp.wrench.test.design.tree.general.normal.node.last.MemberLevel2Node;

import java.util.Random;

/**
 * @author zhp
 * @description
 * @since 2025-05-08 10:06
 */
@Component
public class AccountNode extends AbstractXxxSupport {

    @Autowired
    private MemberLevel1Node memberLevel1Node;

    @Autowired
    private MemberLevel2Node memberLevel2Node;

    @Override
    public String apply(String requestParameter, DynamicContext dynamicContext) throws Exception {
        log.info("同步查询账户标签，账户标签；开户|冻结|止付|可用");
        String accountType01 = new Random().nextBoolean() ? "账户冻结" : "账户可用";
        dynamicContext.set("accountType01", accountType01);

        log.info("同步查询授信数据，拦截|已授信|已降档");
        String accountType02 = new Random().nextBoolean() ? "拦截" : "已授信";
        dynamicContext.set("accountType02", accountType02);


        log.info("【账户节点】规则决策树 userId:{}", requestParameter);

        // 模拟查询用户级别
        int level = new Random().nextInt(2);
        log.info("模拟查询用户级别 level:{}", level);

        dynamicContext.setLevel(level);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<String, DynamicContext, String> get(String requestParameter, DynamicContext dynamicContext) throws Exception {
        String accountType01 = dynamicContext.getValue("accountType01");
        String accountType02 = dynamicContext.getValue("accountType02");

        int level = dynamicContext.getLevel();

        if ("账户冻结".equals(accountType01)) {
            return memberLevel1Node;
        }

        if ("拦截".equals(accountType02)) {
            return memberLevel1Node;
        }

        if (level == 1) {
            return memberLevel1Node;
        }

        return memberLevel2Node;
    }
}
