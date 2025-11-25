package io.github.zhp1221.design.framework.tree.hub;

import io.github.zhp1221.design.framework.utils.OgnlUtils;
import ognl.OgnlContext;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:54
 */
public interface StrategyHubHandler<R, T, D> {

    /**
     * 功能处理接口类
     *
     * @param requestParameter 请求参数
     * @param dynamicContext   功能处理后的动态参数上下文
     * @return 反参类型
     * @throws Exception ex
     */
    void handle(R req, T resp, D dynamicContext);

    default Boolean match(OgnlContext ognlContext, String ognl, D d){
        return OgnlUtils.match(ognlContext, ognl);
    }

}
