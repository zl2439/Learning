package design.patterns.builder;

/**
 * @Author: zl
 * @Date: Created in 2019/12/30
 *
 * 建造者接口，固定建造完整产品的步骤
 * 是任何人在创建产品时不会遗漏步骤
 */
interface Builder {
    void addPar1();
    void addPar2();
    Product createProduct();
}
