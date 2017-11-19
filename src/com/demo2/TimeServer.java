package com.demo2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.demo1.TimeServerHandler;

/**
 * 使用线程池，伪异步的方式写一下demo
 * @author 17194
 *
 */
public class TimeServer {
	protected static final int PORT = 1234;
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
			TimeServerHandlerExecutePool poll = new TimeServerHandlerExecutePool(4,10);
			while(true) {
				Socket socket = server.accept();
				//这里不开启新线程来处理，而是采用线程池管理
				poll.execute(new com.demo2.TimeServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				server.close();
				server = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
