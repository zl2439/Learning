package proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过动态代理调用目标对象的方法
 * JDK的Proxy方式实现的动态代理，目标对象必须要实现接口，没有接口不能实现JDK版的动态代理
 */
public class ProxyTest {
    public static void main(String[] args) {
        /*
         * 获得动态的代理对象---在运行时 在内存中动态的为Target创建一个虚拟的代理对象
         * proxyInstance是代理对象，根据参数确定是谁的代理对象
         * */
        TargetInterface proxyInstance = (TargetInterface) Proxy.newProxyInstance(
                Target.class.getClassLoader(),//与目标对象相同的类加载器
                new Class[]{TargetInterface.class}, //要实现的接口，动态代理对象要和目标对象实现相同的接口
                new InvocationHandler() {
                    /**
                     * 代理对象调用接口相应的方法都是调用invoke
                     * invoke 方法被执行几次？ 看代理对象调用方法几次
                     * @param proxy 是代理对象，开发过程中一般不使用，该对象和newProxyInstance()返回的是同一个对象
                     * @param method    代表的是目标对象的方法的字节码对象
                     * @param args  代表的是调用目标方法时的参数
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("执行目标方法前");
                        //目标对象的方法
                        Object result = method.invoke(new Target(), args);
                        System.out.println("执行目标方法后");
                        return result;
                    }
                }
        );
        proxyInstance.method1();
        String result = proxyInstance.method2();
        System.out.println(result);
    }
}
