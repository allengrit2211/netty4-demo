package com.allen.decorator;

/**
 * @ClassName: Decorator
 * @description: 如果继承 Component 的子类，来做一些列的操作会产生 Component 子类 与 功能类组合的情况
 *                会创建1+n+ n*m!/2 个类 违背了单一职责
 *                假设 ConcreateComponent1 需要完成  一些列的功能 例如需要 包装 功能1 功能2 功能3 或者更多
 *                那么每次 创建 一个 Component 的子类同样需要功能1功能2功能3等一些列的包装，这样会产生相当多的类
 *                通过 Decorator 实现 Component 规范接口的基础上封装功能，对功能接口进行抽象，并把功能类中的继承
 *                关系转化成引用的关系，并引用接口对象作为属性调用抽象方法
 * @author: Allen
 * @create: 2019-06-16 21:16
 **/
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }

}
