package com.allen.decorator;

/**
 * @ClassName: ConcreateDecorator1
 * @description:
 * @author: Allen
 * @create: 2019-06-16 21:17
 **/
public class ConcreateDecorator2 extends Decorator {

    public ConcreateDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("功能C");
    }


}
