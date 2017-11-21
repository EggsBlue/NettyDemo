package com.huage.demo3;
/**
 * 使用NIO来写这个demo,服务端(复杂到太恶心了...)
 * @author 王庆华
 *
 */
public class TimeServer {
	protected static final int PORT = 1234;
	public static void main(String[] args) {
		
		MulitiplexerTimeServer timeServer = new MulitiplexerTimeServer(PORT);
		new Thread(timeServer,"NIO001").start();
		
	}
}
