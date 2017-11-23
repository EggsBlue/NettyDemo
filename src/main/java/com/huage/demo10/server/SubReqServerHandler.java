package com.huage.demo10.server;

import com.huage.demo8.entity.SubscribeReq;
import com.huage.demo8.entity.SubscribeResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 处理客户端请求
 */
public class SubReqServerHandler extends ChannelHandlerAdapter{


    /**
     * 读取消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //由于这里已经被编解码过,所以可以直接使用
        SubscribeReq req = (SubscribeReq) msg;
        if("Luli".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscribe req : [" + req.toString()+"]");
            ctx.writeAndFlush(resp(req.getSubREqID()));
        }
    }

    private SubscribeResp resp(int subReqId){
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqId);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed,3 days later ,sent to the designated address...");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();;
        ctx.close();
    }
}
