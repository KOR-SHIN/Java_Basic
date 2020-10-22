package httpTest;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// 서버에 접근하기 위한 Socket 객체생성
		Socket socket = new Socket("127.0.0.1", 8888);
		
		// 서버에서 보낸 데이터를 읽어오기 위한 Stream객체 생성
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 서버에서 보낸 데이터 읽어오기
		System.out.println("서버에게 받은 메세지 : " + dis.readUTF());
		
		dis.close();
		socket.close();
	}
}
