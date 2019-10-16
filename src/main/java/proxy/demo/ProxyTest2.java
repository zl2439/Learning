package proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest2 {
    public static void main(String[] args) {
        Target target = new Target();

        //创建动态代理对象
        TargetInterface newProxyInstance = (TargetInterface)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),  //获得字节码对象实现的所有接口
                //句柄
                new InvocationHandler() {
                    //invoke 方法被执行几次？ 看代理对象调用方法几次
                    //代理对象调用接口相应的方法都是调用invoke

                    /**
                     *
                     * @param proxy 是代理对象，开发过程中一般不使用，该对象和newProxyInstance()返回的是同一个对象
                     * @param method    代表的是目标对象的方法的字节码对象
                     * @param args  代表的是调用目标方法时的参数
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //反射知识
                        //result, 目标对象相应方法的返回值
                        Object result = method.invoke(target, args);
                        //返回的值给代理对象
                        return result;
                    }
                }
        );

        newProxyInstance.method();  //调用invoke, Method:目标对象的method2方法，args:null, 返回值为null
        newProxyInstance.method2(); //调用invoke, Method:目标对象的method2方法，args:null, 返回值为null
        int i = newProxyInstance.method3(100);//调用invoke, Method:目标对象的method2方法，args:Object[]{100}
        System.out.println("method3的返回结果："+i);
    }
}
