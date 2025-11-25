package xyz.zhp.wrench.test.ognl;

import io.github.zhp1221.design.framework.tree.hub.singleton.SingletonTag;
import ognl.Ognl;
import ognl.OgnlContext;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 18:24
 */
public class OgnlExternalUtilsTest {

    public static void main(String[] args) {
        OgnlContext context = Ognl.createDefaultContext(SingletonTag.getInstance());

        // 准备测试数据
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
        context.put("numbers", numbers);
        context.put("StringUtils", org.apache.commons.lang3.StringUtils.class);
        context.put("Math", Math.class);
        context.put("Arrays", Arrays.class);
        // 1. 调用Java标准库的静态方法
        String[] expressions = {
                // Math类静态方法
                "@java.lang.Math@abs(-10)",                    // 绝对值
                "@Math@max(10, 20)",                         // 最大值
                "@Math@min(10, 20)",                         // 最小值
                "@Math@sqrt(16)",                            // 平方根

                // StringUtils静态方法
                "@org.apache.commons.lang3.StringUtils@isEmpty('')",           // 判断空字符串
                "@StringUtils@isBlank('  ')",                                // 判断空白字符串
                "@StringUtils@capitalize('hello')",                         // 首字母大写
                "@StringUtils@join(#numbers, ',')",                         // 连接字符串

                // Arrays静态方法
                "@java.util.Arrays@asList(1, 2, 3).size()",                 // 数组转列表
                "@Arrays@toString(#numbers.toArray())"                     // 数组转字符串
        };
        for (String expr : expressions) {
            try {
                Object result = Ognl.getValue(expr, context, context.getRoot());
                System.out.println(expr + " => " + result);
            } catch (Exception e) {
                System.err.println(expr + " => ERROR: " + e.getMessage());
            }
        }
    }
}
