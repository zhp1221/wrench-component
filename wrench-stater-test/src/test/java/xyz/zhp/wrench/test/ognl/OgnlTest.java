package xyz.zhp.wrench.test.ognl;

import io.github.zhp1221.design.framework.tree.hub.singleton.SingletonTag;
import io.github.zhp1221.design.framework.utils.OgnlUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 18:02
 */
public class OgnlTest {

    public static void main(String[] args) throws OgnlException {
        User user = new User("张三", 12);
        OgnlContext context = OgnlUtils.getContext(SingletonTag.getInstance());
        context.put("payCode", 1);
        context.put("payCode", 2);
        context.put("list", null);
        context.put("user", user);
        // 1
        System.out.println(Ognl.getValue("#payCode", context, context.getRoot()));
        // 2
        System.out.println(Ognl.getValue("age > 10", user, Boolean.class));
        // 3
        System.out.println(Ognl.getValue("@Math@min(10, 20)", context, context.getRoot()));
        // 4
        System.out.println(Ognl.getValue("@org.apache.commons.lang3.ObjectUtils@isEmpty(#user)", context, context.getRoot()));
        // 5
        System.out.println(Ognl.getValue(String.format("@org.apache.commons.lang3.ObjectUtils@isEmpty(%s)", "#user"), context, context.getRoot()));

        // 语法校验
        Ognl.parseExpression("#user.a");
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User{
        private String name;

        private Integer age;
    }
}
