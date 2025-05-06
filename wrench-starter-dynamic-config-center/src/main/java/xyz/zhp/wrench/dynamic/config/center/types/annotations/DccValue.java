package xyz.zhp.wrench.dynamic.config.center.types.annotations;

import java.lang.annotation.*;

/**
 * @author zhp
 * @description 注解，动态配置中心标记
 * @since 2025-05-06 11:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface DccValue {

    /**
     * 属性名:默认值
     *
     * <p>
     *     根据自动装配，进行扫描带有{@link DccValue}的属性字段，进行反射处理
     * </p>
     *
     * ex:
     * <p>
     *     field:1
     * </p>
     *
     * @return dcc value
     */
    String value() default "";
}
