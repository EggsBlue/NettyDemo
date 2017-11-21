package com.huage.demo3;
/**
 * NIO的客户端(复杂到太恶心了...)
 * @author 王庆华
 *
 */
public class TimeClent {
	public static void main(String[] args) {
		int port = 1234;
		new Thread(new TimeClentHandler("127.0.0.1", port),"timeClent-01").start();;
	}
}
