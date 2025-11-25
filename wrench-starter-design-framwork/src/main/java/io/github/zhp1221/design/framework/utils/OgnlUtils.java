package io.github.zhp1221.design.framework.utils;

import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:30
 */
@Slf4j
@UtilityClass
public class OgnlUtils {

    public <T> OgnlContext getContext(T t){
        return Ognl.createDefaultContext(t);
    }

    public Object getValue(String ognl, OgnlContext ognlContext) throws OgnlException {
        return Ognl.getValue(ognl, ognlContext, ognlContext.getRoot());
    }
    public void clear(OgnlContext ognlContext){
        ognlContext.clear();
    }

    public void traverse(OgnlContext ognlContext){
        ognlContext.forEach((k, v) -> {
            log.info("key:{}, value:{}", k, v == null ? "" : JSONUtil.toJsonPrettyStr(v));
        });
    }

    public Boolean match(OgnlContext ognlContext, String ognlExpression){
        try {
            Object value;
            if ((value = Ognl.getValue(ognlExpression, ognlContext, ognlContext.getRoot())) == null) {
                throw new IllegalArgumentException("[ognl value parse null] please checkout your expression syntax");
            }
            return (Boolean)value;
        } catch (OgnlException e) {
            throw new RuntimeException("[ognl parse error]" + e.getMessage(), e);
        }

    }

}
