package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket client = new Socket("127.0.0.1", 7777);
		System.out.println("서버접속이 완료되었습니다.");
		
		InputStream is = client.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		System.out.println("서버에게 받은 메세지 : " + dis.readUTF());
		System.out.println("서버연결을 종료합니다.");
		
		dis.close();
		client.close();
	}
}
