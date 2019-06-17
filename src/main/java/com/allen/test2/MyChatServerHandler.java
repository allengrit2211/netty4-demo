package com.allen.test2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName: MyChatServerHandler
 * @description:
 * @author: Allen
 * @create: 2019-06-15 22:57
 **/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /****
     * 用户连接组
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        System.out.println("服务器收到消息:"+msg);
        for(Channel ch:channelGroup){
            if (ch != channel) {
                ch.writeAndFlush(channel.remoteAddress() + " 发送的消息" + msg+"\r\n");
            } else {
                ch.writeAndFlush("【自己】" + msg + "\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /***
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        //将会通知到channelGroup里面的所有的channel
        channelGroup.writeAndFlush("【服务器消息】-" + channel.remoteAddress() + "加入\n");
        //将channel放入channelGroup中
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("【服务器消息】-" + channel.remoteAddress() + "离开\n");
        //这里不需要写channelGroup.remove(channel)移除channel,因为当连接断开时,netty会自动处理
        //这个方法写不写都可以
        //channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线了");
    }
}
