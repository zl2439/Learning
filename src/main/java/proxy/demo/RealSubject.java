package proxy.demo;

/**
 * @Author: zl
 * @Date: Created in 2020/1/5
 * <p>
 * 真实的实体
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("真实的请求");
    }
}
