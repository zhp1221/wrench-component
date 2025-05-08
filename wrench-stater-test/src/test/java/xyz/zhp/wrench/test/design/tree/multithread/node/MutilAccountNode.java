package xyz.zhp.wrench.test.design.tree.multithread.node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.zhp.wrench.design.framwork.tree.StrategyHandler;
import xyz.zhp.wrench.test.design.tree.model.DynamicContext;
import xyz.zhp.wrench.test.design.tree.multithread.MutilAbstractXxxSupport;
import xyz.zhp.wrench.test.design.tree.multithread.node.last.MultiMemberLevel1Node;
import xyz.zhp.wrench.test.design.tree.multithread.node.last.MultiMemberLevel2Node;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 17:46
 */
@Component
public class MutilAccountNode extends MutilAbstractXxxSupport {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MultiMemberLevel1Node multiMemberLevel1Node;

    @Autowired
    private MultiMemberLevel2Node multiMemberLevel2Node;

    @Override
    protected void multiThread(String requestParameter, DynamicContext dynamicContext) throws Exception {
        CompletableFuture<String> accountType01 = CompletableFuture.supplyAsync(() -> {
            log.info("异步查询账户标签，账户标签；开户|冻结|止付|可用");
            return new Random().nextBoolean() ? "账户冻结" : "账户可用";
        }, threadPoolExecutor);

        CompletableFuture<String> accountType02 = CompletableFuture.supplyAsync(() -> {
            log.info("异步查询授信数据，拦截|已授信|已降档");
            return new Random().nextBoolean() ? "拦截" : "已授信";
        }, threadPoolExecutor);

        CompletableFuture.allOf(accountType01, accountType02)
                .thenRun(() -> {
                    dynamicContext.set("accountType01", accountType01.join());
                    dynamicContext.set("accountType02", accountType02.join());
                })
                .join();
    }

    @Override
    protected String doApply(String requestParameter, DynamicContext dynamicContext) throws Exception {
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
            return multiMemberLevel1Node;
        }

        if ("拦截".equals(accountType02)) {
            return multiMemberLevel1Node;
        }

        if (level == 1) {
            return multiMemberLevel1Node;
        }

        return multiMemberLevel2Node;
    }
}
