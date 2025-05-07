package xyz.zhp.wrench.design.framwork.tree;

/**
 * @author zhp
 * @description 异步资源加载路由抽象类
 * @since 2025-05-07 16:48
 */
public abstract class AbstractMultiThreadStrategyRouter<T, D, R> extends AbstractStrategyRouter<T, D, R>{

    /**
     * 异步资源数据整合 & 功能数据整合
     * <p>
     *     在正常功能数据处理，新增三方数据异步整合步骤
     * </p>
     * <p>
     *     而原有的功能数据处理的实现，抽象为{@link AbstractMultiThreadStrategyRouter#doApply(T, D)}方法，进行子类的实现
     * </p>
     *
     * @param requestParameter 请求参数
     * @param dynamicContext   功能处理后的动态参数上下文
     * @return 反参类型
     * @throws Exception ex
     */
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        multiThread(requestParameter, dynamicContext);
        return doApply(requestParameter, dynamicContext);
    }

    /**
     * 异步资源数据处理隔离区
     *
     * @param requestParameter 请求参数
     * @param dynamicContext 上下文参数
     * @throws Exception ex
     */
    protected abstract void multiThread(T requestParameter, D dynamicContext) throws Exception;

    /**
     * 功能数据处理隔离区，同功能处理接口类{@link StrategyHandler#apply(T, D)}
     *
     * @param requestParameter 请求参数
     * @param dynamicContext 动态上下文参数
     * @return 反参类型
     * @throws Exception ex
     */
    protected abstract R doApply(T requestParameter, D dynamicContext) throws Exception;

}
