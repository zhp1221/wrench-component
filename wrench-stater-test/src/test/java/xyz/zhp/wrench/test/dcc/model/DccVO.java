package xyz.zhp.wrench.test.dcc.model;

import org.springframework.stereotype.Component;
import xyz.zhp.wrench.dynamic.config.center.types.annotations.DccValue;

/**
 * @author zhp
 * @description
 * @since 2025-05-06 14:28
 */
@Component
public class DccVO {

    @DccValue("name:张三")
    private String name;

    @DccValue("age:18")
    private Integer age;

    public DccVO() {
    }

    public DccVO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
