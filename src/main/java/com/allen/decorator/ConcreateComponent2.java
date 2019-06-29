package com.allen.decorator;

/**
 * @ClassName: ConcreateComponent1
 * @description: 实现2
 * @author: Allen
 * @create: 2019-06-16 21:15
 **/
public class ConcreateComponent2 implements Component {


    @Override
    public void doSomething() {
        System.out.println("功能A");
    }


}
