package proxy.demo;

/**
 * @Author: zl
 * @Date: Created in 2020/1/5
 * <p>
 * 代理实体，需要持有真实实体的引用
 */
public class Proxy implements Subject {
    /* 持有真正的实体 */
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        realSubject.request();
    }
}
