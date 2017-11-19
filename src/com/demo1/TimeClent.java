package com.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * BIO客户端
 * @author 17194
 *
 */
public class TimeClent {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1",TimeServer.PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME");
			String resp = in.readLine();
			System.out.println("服务端消息："+resp);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
