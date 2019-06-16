package com.allen.test5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName: TestServerHandler
 * @description:
 * @author: Allen
 * @create: 2019-06-16 13:01
 **/
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if (dataType == MyDataInfo.MyMessage.DataType.PersonType) {
            MyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        } else if (dataType == MyDataInfo.MyMessage.DataType.DogType) {
            MyDataInfo.Dog dag = msg.getDog();
            System.out.println(dag.getName());
            System.out.println(dag.getAge());
        } else {
            MyDataInfo.Cat dag = msg.getCat();
            System.out.println(dag.getName());
            System.out.println(dag.getCity());
        }
    }
}
