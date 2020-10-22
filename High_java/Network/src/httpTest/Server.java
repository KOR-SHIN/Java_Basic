package httpTest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		
		// ServerSocket객체생성 및 port binding
		ServerSocket server = new ServerSocket(8888);
		
		System.out.println("Client 요청 대기 중");
		
		// accept()를 사용하여 연결요청이 올 때까지 대기
		// BLOCKED상태라고 생각하면 됨
		Socket socket = server.accept();
		System.out.println("Client 연결요청 수신");
		System.out.println("=====[연결요청 Client 정보]=====");
		System.out.println("Client IP : " + socket.getInetAddress());
		System.out.println("Client Port : " + socket.getPort());
		System.out.println("================================");
		
		// Client에게 메세지를 보내기 위해 Stream객체 생성
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		// Client에게 메세지 전송
		dos.writeUTF("Message From Server");
		System.out.println("Message 전송완료");
		
		dos.close();
		server.close();
		
		
	}
}
