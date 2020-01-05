package design.patterns.builder;

/**
 * @Author: zl
 * @Date: Created in 2019/12/30
 *
 * 具体的建造者，实现抽象建造者接口，由于实现了建造者接口，因此必须重新接口中的方法，否则编译不通过
 * 虽然该类中不会造成遗失建造产品步骤，但是如果直接使用该类创建产品时，还是需要知道创建该产品的具体细节
 * 因此还需要一个很关键的角色-指挥者，通过指挥者来隔离用户与建造过程的关联
 */
public class SpecificBuilder2 implements Builder{
    Product product = new Product();
    @Override
    public void addPar1() {
        product.add("part1");
    }

    @Override
    public void addPar2() {
        product.add("part2");
    }

    @Override
    public Product createProduct() {
        return product;
    }
}
