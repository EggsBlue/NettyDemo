package com.huage.demo11.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/main/java/com/huage";

    public void run(final int port,final String url) throws  Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                      ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                      ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                      ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                      ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                      ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));
                }
            });
            ChannelFuture sync = b.bind("127.0.0.1", port).sync();
            System.out.println("Http 文件服务器服务器启动,网址是:127.0.0.1:"+port+url);
            sync.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new HttpFileServer().run(1234,DEFAULT_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
