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
        //获得动态的代理对象---在运行时 在内存中动态的为Target创建一个虚拟的代理对象
        //object是代理对象，根据参数确定是谁的代理对象
        TargetInterface object = (TargetInterface)Proxy.newProxyInstance(
                Target.class.getClassLoader(),//与目标对象相同的类加载器
                new Class[]{TargetInterface.class}, //要实现的接口，动态代理对象要和目标对象实现相同的接口
                new InvocationHandler() {
                    //invoke 代表的是执行代理对象的方法
                    //method 代表目标对象的方法的字节码对象
                    //args 代表目标对象的相应的方法的参数
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("执行目标方法前");
                        //目标对象的方法
                        Object invoke = method.invoke(new Target(), args);
                        System.out.println("执行目标方法后");
                        return invoke;
                    }
                }
        );
        object.method();
        String result = object.method2();
        System.out.println(result);
    }
}
