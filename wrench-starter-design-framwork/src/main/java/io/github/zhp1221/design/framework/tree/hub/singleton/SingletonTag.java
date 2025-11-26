package io.github.zhp1221.design.framework.tree.hub.singleton;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 16:42
 */
public class SingletonTag {

    private static volatile SingletonTag singletonTag;

    public static SingletonTag getInstance(){
        if (singletonTag == null) {
            synchronized (SingletonTag.class){
                if (singletonTag == null) {
                    return singletonTag = new SingletonTag();
                }
            }
        }
        return singletonTag;
    }
}
