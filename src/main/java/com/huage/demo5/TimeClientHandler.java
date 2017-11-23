package com.huage.demo5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter{
	
	private final ByteBuf firstMessage = null;
	
	private int counter;
	private byte[] req;
	
	public TimeClientHandler() {
		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
	}
	
	//当客户端建立连接后执行
	public void channelActive(ChannelHandlerContext ctx) {
		ByteBuf message = null;
		for(int i=0; i<100; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	
	//接收到服务端响应的消息时执行
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
		String body = (String) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req,"utf-8");
		System.out.println("Now is :"+body+", the conter is :"+ ++counter);
	}
	
	//发生异常时执行
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
		ctx.close();
	}
	
}
