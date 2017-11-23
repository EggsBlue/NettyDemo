package com.huage.demo9.test;

import com.huage.demo9.entity.SubscribeReqProto;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {

    private static byte[] encode (SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    public static SubscribeReqProto.SubscribeReq decode(byte[] body) throws Exception{
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("luli");
        builder.setProductName("Netty Book!");
        List<String> address = new ArrayList<>();
        address.add("Nanjing yuhuatai");
        address.add("Beijing liulichang");
        address.add("ShenZhen HongShuLin");
        builder.setAddress("yantai");
//        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Befor encode : "+ req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After decode : "+ req.toString());
        System.out.println("Assert equal : --> "+req2.equals(req));
    }
}
