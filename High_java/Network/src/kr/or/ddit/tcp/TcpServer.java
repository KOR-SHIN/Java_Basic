package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws IOException {
		// TCP 소켓 통신을 위한 server socket 객체 생성
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버가 접속을 기다리는 중입니다..");
		
		// accept() : Client의 연결요청이 올 때까지 대기
		// 			  연결요청이 들어오면 새로운 Socket 객체생성
		Socket socket = server.accept();
		
		System.out.println("접속한 Client 정보");
		System.out.println("주소 : " + socket.getInetAddress());
		
		// Client에게 메세지 보내기
		// OutputStream객체를 구성하여 전송
		// 접속한 Socket의 getOutStream()을 이용한다.
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		dos.writeUTF("Hello Client!");
		System.out.println("메세지 전송 완료");
		
		dos.close();
		server.close();
	}
}
