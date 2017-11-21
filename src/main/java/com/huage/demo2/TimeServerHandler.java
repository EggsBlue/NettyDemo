package com.huage.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;
/**
 * 处理Socket请求的Thread
 * @author 17194
 *
 */
public class TimeServerHandler implements Runnable{
	
	private Socket socket = null;
	TimeServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		BufferedReader in  = null;
		PrintWriter out = null;
		try {
			 in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			 out = new PrintWriter(socket.getOutputStream(),true);
			 String body = in.readLine();
			 System.out.println("客户端消息："+body);
			 if(body == null) {
				 out.println("You Body Is Null!");
				 return;
			 }
			 String currentTime = body.equals("QUERY TIME")?new Date(System.currentTimeMillis()).toLocaleString():"BAD REQUEST";
			 System.out.println("响应："+currentTime);
			 out.print(currentTime);
			 out.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
			out = null;
		}
		
	}

}
