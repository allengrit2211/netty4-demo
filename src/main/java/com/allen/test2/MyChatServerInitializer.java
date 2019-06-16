package com.allen.test2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @ClassName: MyChatServerInitializer
 * @description: 初始化器
 * @author: Allen
 * @create: 2019-06-15 22:53
 **/
public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //根据一定的分割符，来实现的一个解码器
        pipeline.addLast("delimiterBaseFrameDecoder",new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pipeline.addLast("stringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder",new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("myChatServerHandler",new MyChatServerHandler());

    }
}
