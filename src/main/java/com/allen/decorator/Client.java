package com.allen.decorator;

/**
 * @ClassName: Client
 * @description:
 * @author: Allen
 * @create: 2019-06-16 21:20
 **/
public class Client {


    public static void main(String[] args) {
        Component component = new ConcreateDecorator2(new ConcreateDecorator1(new ConcreateComponent()));
        component.doSomething();
    }
}
