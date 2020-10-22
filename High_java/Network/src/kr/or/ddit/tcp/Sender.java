package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	
	private Socket socket;
	private DataOutputStream dos;
	private String name;
	
	public Sender(Socket socket) {
		this.socket = socket;
	
		name = "[" + socket.getInetAddress() + " : " + socket.getLocalPort() + "]";
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		
		while(dos != null) {
			try {
				dos.writeUTF(name + " >>> " + scan.nextLine());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		scan.close();
	}
	
	
}
