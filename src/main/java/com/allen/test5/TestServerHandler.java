package com.allen.test5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName: TestServerHandler
 * @description:
 * @author: Allen
 * @create: 2019-06-16 13:01
 **/
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());

    }
}
