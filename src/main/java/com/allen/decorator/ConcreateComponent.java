package com.allen.decorator;

/**
 * @ClassName: ConcreateComponent
 * @description:
 * @author: Allen
 * @create: 2019-06-16 21:15
 **/
public class ConcreateComponent implements Component {


    @Override
    public void doSomething() {
        System.out.println("功能A");
    }


}
