package xyz.zhp.wrench.dynamic.config.center.domain.model.valobj;

/**
 * @author zhp
 * @description 属性vo
 * @since 2025-05-06 13:41
 */
public class AttributeVO {

    /**
     * 键-属性fieldName
     */
    private String attribute;

    /**
     * 值
     */
    private String val;

    public AttributeVO(String attribute, String val) {
        this.attribute = attribute;
        this.val = val;
    }

    public AttributeVO() {
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
