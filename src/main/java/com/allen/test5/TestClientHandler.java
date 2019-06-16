package com.allen.test5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName: TestClientHandler
 * @description:
 * @author: Allen
 * @create: 2019-06-16 13:08
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("上线操作");
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("张三").setAge(20).setAddress("北京").build();
        ctx.channel().writeAndFlush(person);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }
}
