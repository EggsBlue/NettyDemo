package com.huage.demo8.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端,演示对象序列化使用(ObjectDecoder+ObjectEncoder)
 */
public class SubReqServer {
    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,100).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //加入此类,实现对序列化对象的解码
                    ch.pipeline().addLast(new ObjectDecoder(1024* 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                    //加入此类,可以再发送消息时自动将实现序列化接口的pojo对象进行编码,无需手动编码
                    ch.pipeline().addLast(new ObjectEncoder());
                    ch.pipeline().addLast(new SubReqServerHandler());
                }
            });

            ChannelFuture future = b.bind(port).sync();
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        int port =1234;
        try {
            new SubReqServer().bind(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
