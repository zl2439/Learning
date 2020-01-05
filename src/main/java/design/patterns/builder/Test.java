package design.patterns.builder;

/**
 * @Author: zl
 * @Date: Created in 2019/12/31
 *
 * 创建指定的Product实例
 */
public class Test {

    public static void main(String[] args) {
        SpecificBuilder1 specificBuilder1 = new SpecificBuilder1();
        /* 通过指挥者来创建对象，保证在任何情况下都有统一的流程 */
        Product product1 = Director.create(specificBuilder1);
        product1.show();

        SpecificBuilder2 specificBuilder2 = new SpecificBuilder2();
        Product product2 = Director.create(specificBuilder2);
        product2.show();
    }

}
