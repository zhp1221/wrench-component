package xyz.zhp.wrench.test.design.tree.general;

/**
 * @author zhp
 * @description
 * @since 2025-05-07 15:35
 */
public interface A {

    A DEFAULT = () ->{
        System.out.println("interface A default");
    };

    void test();
}
class  Aa implements A{

    A a = A.DEFAULT;

    public void router(){
        a.test();
    }

    @Override
    public void test() {
        System.out.println("Aa test");
    }
}

class B{
    public static void main(String[] args) {
        Aa aa = new Aa();
        aa.router();
    }
}
