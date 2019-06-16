package com.allen.decorator;

/**
 * @ClassName: Decorator
 * @description:
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
