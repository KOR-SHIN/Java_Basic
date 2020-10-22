package kr.or.ddit.tcp;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	// 대화명, 클라이언트의 Socket을 저장하기 위한 Map변수 선언
	private Map<String, Socket> clients;
	
	public MultiChatServer() {
		// 동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<>());
	}
	
	// 서버시작
	public void serverStart() {
		ServerSocket serverSocket = null;
		Socket socket;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				// 연결요청이 올 때까지 BLOCKED
				socket =  serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속하였습니다.");
			
				// 메세지 전송 처리를 하는 쓰레드 생성 및 실행
				ServerRecevier receiver = new ServerRecevier(socket);
				receiver.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 서버 소켓 닫기
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 대화방 측, Map에 저장된 전체 유저에게 안내메세지를 전송하는 메서드
	 * @param msg (보낼 메세지)
	 */
	public void sendMessage(String msg) {
		// Map에 저장된 유저의 대화명 리스트를 추출(key값)
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next();
				
				// 대화명에 해당하는 Socket의 OutputStream객체 생성하기
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg);
			} catch(IOException e) {
//				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 대화방 측, Map에 저장된 전체 유저에게 대화 메세지를 전송하는 메서드
	 * @param msg (보낼 메세지)
	 * @param from (보낸 사람 대화명)
	 */
	public void sendMessage(String msg, String from) {
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next();
				
				// 대화명에 해당하는 Socket의 OutputStream객체 생성하기
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF("[" + from + "] : " + msg);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 특정 유저에게만 메세지를 보내는 메서드(귓속말)
	 * @param msg (보내려는 메시지 내용)
	 * @param from (발신자 이름)
	 */
	public void personalMessage(String msg, String from) {
		try {
			// 귓속말을 하기위한 포맷이 공백을 기준으로
			// (귓속말기호 수신자아이디 메세지내용)으로 구분되어 있다.
			String[] temp = msg.split(" ");

			// 귓속말을 첫 번째 공백 뒤에는 수신자 아이디가 위치한다.
			String receiverName = temp[1];

			// 메세지의 내용이 공백을 포함하고 있을 수 있기때문에
			// 두 번째 공백이후의 내용을 모두 조합하여 메세지를 만든다.
			String message = "";
			for(int i=2; i<temp.length; i++) {
				message += temp[i] + " ";
			}
			
			// Map의 Key는 유저이름(String)으로 설정되어 있다.
			// 따라서 메세지를 수신하는 사람의 아이디를 이용하여
			// Socket을 관리하는 Map에서 Value(Socket 객체)를 가져온다.
			DataOutputStream dos = new DataOutputStream(clients.get(receiverName).getOutputStream());
			dos.writeUTF("[" + from + "](귓속말입니다) : " + message);
		} catch (IOException e) {
//			e.printStackTrace();
		}
		
	}
	
	// 서버에서 클라이언트로 메세지를 전송할 Thread를 innerClass로 정의
	// innerClass는 부모클래스의 멤버변수를 직접 사용가능하다.
	class ServerRecevier extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerRecevier(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch(IOException e) { 
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				// 서버에서 클라이언트가 보내는 최초의 메세지를 수신한다.
				// 즉, 대화명을 수신한다.
				name = dis.readUTF();
				
				// 대화명을 받아서 다른 모든 클라이언트에게
				// 대화방 참여 메세지를 보낸다.
				sendMessage("#" + name + " 님이 입장하셨습니다.");
				
				// 대화명과 소켓정보도 Map에 저장한다.
				clients.put(name, socket);
				
				// 서버에서 현재 접속자 수를 확인하기 위한 log
				System.out.println("현재 서버 접속자 수는 : " + clients.size() + "명 입니다.");
				
				// 이 후의 메세지 처리는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메세지를 다른클라이언트에게 보내준다.
				
				while(dis != null) {
					String msg = dis.readUTF();
					if(msg.length() > 2 && msg.substring(0, 2).equals("/w")) {
						personalMessage(msg, name);
					}
					else { 
						sendMessage(msg, name);
					}
				}
				
			} catch(IOException e) {
//				e.printStackTrace();
			} finally {
				// 클라이언트의 접속이 종료된 경우 실행되는 구간
				sendMessage(name + "님이 나가셨습니다.");
				
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress()
								+ " : " + socket.getPort()
								+ "] 에서 접속을 종료했습니다.");

				System.out.println("현재 서버 접속자 수는 : " + clients.size() + "명 입니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		MultiChatServer mcs =  new MultiChatServer();
		mcs.serverStart();
	}
	
}
