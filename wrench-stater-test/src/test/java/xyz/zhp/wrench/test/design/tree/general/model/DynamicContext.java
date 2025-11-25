package xyz.zhp.wrench.test.design.tree.general.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 17:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicContext{

    private int level;

    private Map<String, Object> dataobjs = new HashMap<>();

    public <T> void set(String key, T val){
        dataobjs.put(key, val);
    }

    public <T> T getValue(String key){
        return (T)dataobjs.get(key);
    }

}
