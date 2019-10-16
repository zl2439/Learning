package proxy.demo;

/**
 * 动态代理练习---目标对象
 */
public class Target implements TargetInterface{
    @Override
    public void method() {
        System.out.println("动态代理目标对象,方法1");
    }

    @Override
    public String method2() {
        System.out.println("动态代理目标对象,方法2");
        return "method2";
    }

    @Override
    public int method3(int x) {
        return x;
    }
}
