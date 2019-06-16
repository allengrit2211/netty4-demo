package com.allen.test5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @ClassName: TestClientHandler
 * @description:
 * @author: Allen
 * @create: 2019-06-16 13:08
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("上线操作");
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        System.out.println("randomInt="+randomInt);


        switch (randomInt) {
            case 0: {
                myMessage = MyDataInfo.MyMessage.newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                        .setPerson(MyDataInfo.Person.newBuilder()
                                .setName("张三")
                                .setAge(20)
                                .setAddress("北京").build())
                        .build();
                break;
            }
            case 1: {
                myMessage = MyDataInfo.MyMessage.newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                        .setDog(MyDataInfo.Dog.newBuilder()
                                .setName("一只狗")
                                .setAge(2).build())
                        .build();
                break;
            }
            case 2: {
                myMessage = MyDataInfo.MyMessage.newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                        .setCat(MyDataInfo.Cat.newBuilder()
                                .setName("一只猫")
                                .setCity("上海").build())
                        .build();
                break;
            }
        }

        ctx.channel().writeAndFlush(myMessage);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }
}
