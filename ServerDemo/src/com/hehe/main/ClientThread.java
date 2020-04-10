package com.hehe.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

public class ClientThread extends Thread {

	private Socket clientSocket;
	private ServerDemo sd;
	private InputStream is;
	private OutputStream os;
	private byte[] recvbuf = new byte[556];
	
	private int readres;

	public ClientThread(Socket clientSocket,ServerDemo sd) {
		super();
		this.clientSocket = clientSocket;
		this.sd = sd;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			try {
				is = clientSocket.getInputStream();
				os = clientSocket.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
			try {
				/*
				 * typedef struct UserForClient 
				 * { int id; 
				 * int gender; 
				 * char nane[32];
				 * }USERFORCLIENT, * PUSERFORCLIENT;
				 * 
				 * typedef struct Message 
				 * { int type; 
				 * USERFORCLIENT USERFORCLIENT; 
				 * char message_data[512]; 
				 * }MESSAGE, * PMESSAGE;
				 * 4+4+32+4+512=556 bytes
				 */
				//is.read(recvbuf, 0, 556);
				readres = is.read(recvbuf, 0, 556);
				if (readres>0) {
					Message mes = ObjectTool.getMessageFormBytes(recvbuf);
					System.out.print(mes.data);
					sendMessageToEachClient(recvbuf);
				}else {
					if (readres ==0) {
						
					}
					if (readres == (-1)) {
						synchronized(sd) {
							sd.getUser_sockets().remove(clientSocket);
							Thread.currentThread().interrupt();
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void sendMessageToEachClient(byte[] sendData) {
		// TODO Auto-generated method stub
		Set<Socket> sockets = sd.getUser_sockets();
		OutputStream ops = null;
		for (Iterator it = sockets.iterator(); it.hasNext();) {
			Socket s = (Socket) it.next();
			try {
				ops = s.getOutputStream();
				ops.write(sendData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
	}
	


}
