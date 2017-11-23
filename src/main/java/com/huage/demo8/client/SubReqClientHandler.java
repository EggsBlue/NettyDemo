package com.huage.demo8.client;

import com.huage.demo8.entity.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter{

    /**
     * 连接成功后执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i) {
        SubscribeReq req= new SubscribeReq();
        req.setAddress("合肥蜀山区");
        req.setPhoneNumber("5211314");
        req.setProductName("My wife");
        req.setSubREqID(i);
        req.setUserName("Luli");
        return req;
    }

    /**
     * 接受到消息执行
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到消息:["+msg+"]");
    }


    /**
     * 发生异常后执行
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
