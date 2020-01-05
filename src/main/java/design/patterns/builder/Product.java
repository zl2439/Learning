package design.patterns.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zl
 * @Date: Created in 2019/12/30
 * <p>
 * 产品类，一个完整的产品由多部分组成
 */
public class Product {
    /* 产品的构成 */
    private List<Object> list = new ArrayList<>();

    /* 添加产品的各个部件 */
    public void add(Object object) {
        list.add(object);
    }

    public void show() {
        list.forEach(row -> System.out.println(row));
    }

}
