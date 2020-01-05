package proxy.demo;

/**
 * @Author: zl
 * @Date: Created in 2020/1/5
 * <p>
 * 代码模式测试
 */
public class Test {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }
}
