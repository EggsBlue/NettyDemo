package com.huage.demo9.server;

import com.huage.demo8.entity.SubscribeResp;
import com.huage.demo9.entity.SubscribeReqProto;
import com.huage.demo9.entity.SubscribeRespProto;
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
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if("Luli".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscribe req : [" + req.toString()+"]");
            ctx.write(resp(req.getSubReqID()));
        }
        ctx.flush();
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqId){
        SubscribeRespProto.SubscribeResp.Builder builder  = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqId);
        builder.setRespCode("0");
        builder.setDesc("Netty book order succeed,3 days later ,sent to the designated address...");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();;
        ctx.close();
    }
}
