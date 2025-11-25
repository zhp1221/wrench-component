package io.github.zhp1221.design.framework.tree.general;

/**
 *
 * @param <T> 请求参数
 * @param <D> 动态上下文参数
 * @param <R> 反参类型
 * @author zhp
 * @description 分支预测策略映射类
 * @since 2025-05-07 16:05
 */
public interface StrategyMapper<T, D, R> {

    /**
     * 分支预测-获取策略处理类
     *
     * @param requestParameter 请求参数
     * @param dynamicContext 动态上下文参数
     * @return 功能处理策略类
     * @throws Exception ex
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
