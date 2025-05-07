package xyz.zhp.wrench.design.framwork.tree;


import lombok.Getter;
import lombok.Setter;

/**
 * @author zhp
 * @description 策略路由抽象类
 * @since 2025-05-07 16:37
 */
@SuppressWarnings("all")
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyHandler<T, D, R>, StrategyMapper<T, D, R>{

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    /**
     * 路由策略抉择模板
     * <li>获取策略类</li>
     * <li>功能受理</li>
     *
     * @param requestParameter 请求参数
     * @param dynamicContext 动态上下文参数
     * @return 反参类型
     * @throws Exception ex
     */
    public R router(T requestParameter, D dynamicContext) throws Exception{
        StrategyHandler<T, D, R> strategyHandler = get(requestParameter, dynamicContext);
        if (strategyHandler != null) return strategyHandler.apply(requestParameter, dynamicContext);
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }

}
