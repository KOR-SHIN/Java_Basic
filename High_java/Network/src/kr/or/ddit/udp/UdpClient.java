package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
	
	// 시작 메서드
	public void start() throws IOException {
		DatagramSocket socket = new DatagramSocket();
		InetAddress serverAddr = InetAddress.getByName("localhost");
		
		// 데이터가 저장될 바이트배열 (패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddr, 8888);
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		socket.send(outPacket);
		socket.receive(inPacket);
		
		System.out.println("현재 서버 시간 : " + new String(inPacket.getData()));
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
}
