package io.github.zhp1221.design.framework.tree.general;

/**
 * @param <T> 请求参数
 * @param <D> 动态上下文参数
 * @param <R> 反参类型
 * @author zhp
 * @description 功能受理策略类
 * @since 2025-05-07 16:05
 */
@SuppressWarnings("all")
public interface StrategyHandler<T, D, R> {

    /**
     * 缺省策略处理实例
     */
    StrategyHandler DEFAULT = (T, D) -> null;

    /**
     * 功能处理接口类
     *
     * @param requestParameter 请求参数
     * @param dynamicContext   功能处理后的动态参数上下文
     * @return 反参类型
     * @throws Exception ex
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;

}
