package com.hehe.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerDemo {
	
	private Set<Socket> user_sockets = null;
	
	
	public Set<Socket> getUser_sockets() {
		return user_sockets;
	}

	public ServerDemo() {
		this.user_sockets = new HashSet<Socket>();
	}

	public static void main(String[] args) {
		ServerDemo sd = new ServerDemo();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(20000);
		} catch (IOException e) {
			System.out.println("create server socket failed ...");
			serverSocket = null;
			e.printStackTrace();
		}
		Socket client = null;
		while (true) {
			try {
				client = serverSocket.accept();
				synchronized(sd) {
					sd.user_sockets.add(client);
				}
				new ClientThread(client, sd).run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
