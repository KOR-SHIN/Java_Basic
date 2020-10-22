package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket socket;
	
	// 시작메서드
	public void start() throws IOException {
		// 서버소켓과 8888번 포트 바인딩
		socket = new DatagramSocket(8888);
		
		// 패킷 송수신을 위한 객체
		DatagramPacket inPacket, outPacket;
		
		// 패킷 수신용 바이트배열
		byte[] inMsg = new byte[1];
		
		//패킷 송신용 바이트배열
		byte[] outMsg;
		
		while(true) {
			// 데이터를 수신학 위한 패킷생성
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			System.out.println("패킷 수신 대기중...");
			
			// 패킷을 통해 데이터를 수신
			// TCP방식에서 serverSocket의 accept()와 같은 기능을 수행한다.
			// 패킷을 수신하기 전까지 BLOCKED된다.
			// 패킷을 수신하면, socket에 사용된 packet의 buffer를 채운다.
			socket.receive(inPacket);
			System.out.println("패킷 수신 완료");
			
			// 수신용 패킷으로부터 client의 IP주소와 Port번호를 얻는다.
			InetAddress addr = inPacket.getAddress();
			int port = inPacket.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 변환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			
			// 문자열(time)을 byte배열로 변환한다.
			outMsg = time.getBytes();
			
			// 패킷을 생성해서 Client에게 전송한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, addr, port);
			socket.send(outPacket);
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
}
