package com.allen.test1;

import com.allen.test.TestServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName: MyServer
 * @description:
 * @author: Allen
 * @create: 2019-06-15 22:01
 **/
public class MyServer {

    public static void main(String[] args) throws InterruptedException {

        //获取连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //处理连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class).handler(new LoggingHandler()).childHandler(new MyServerInitializer());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
