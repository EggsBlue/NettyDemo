package com.huage.demo10.entity;

import java.io.Serializable;

/**
 * 请求实体
 */
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subREqID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;



    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subREqID=" + subREqID +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSubREqID() {
        return subREqID;
    }

    public void setSubREqID(int subREqID) {
        this.subREqID = subREqID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
