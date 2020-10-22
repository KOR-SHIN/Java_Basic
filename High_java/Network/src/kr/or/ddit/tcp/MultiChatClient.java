package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiChatClient {
	private Scanner scan = new Scanner(System.in); 
	private String name; // 대화명
	
	public void clientStart() {
		// 대화명 입력
		System.out.print("대화명입력 >> ");
		name = scan.next();
		scan.nextLine();
		
		Socket socket = null;
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			// 송신용 쓰레드 생성
			ClientSender sender =  new ClientSender(socket, name);
			sender.start();
			
			// 수신용 쓰레드 생성
			ClientRecevier receiver = new ClientRecevier(socket);
			receiver.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 메시지를 전송하는 Thread
	class ClientSender extends Thread {
		private Socket socket;
		private DataOutputStream dos;
		private String name;
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				// 시작과 동시에 대화명을 서버로 전송한다.
				if(dos != null) {
					dos.writeUTF(name);
				}
				
				while(dos != null) {
					// 키보드로 입력받은 메세지를 서버로 전송
					String message = scan.nextLine();
					dos.writeUTF(message);
				}
				
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 수신용 Thread 클래스
	class ClientRecevier extends Thread {
		private Socket socket;
		private DataInputStream dis;
		
		public ClientRecevier(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					// 서버로부터 받은 메세지 출력하기
					System.out.println(dis.readUTF());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		MultiChatClient mcc = new MultiChatClient();
		mcc.clientStart();
	}
}
