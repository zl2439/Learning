package design.patterns.builder;

/**
 * @Author: zl
 * @Date: Created in 2019/12/30
 *
 * 指挥者，将创建Product实例的步骤封装起来
 */
public class Director {
    /* 用户告诉指挥者需要创建哪类产品，有指挥者实现具体的创建 */
    public static Product create(Builder builder){
        builder.addPar1();
        builder.addPar2();
        return builder.createProduct();
    }
}
