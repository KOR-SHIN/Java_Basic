package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	/**
	 * 서버는 클라이언트가 접속하면 서버 컴퓨터의
	 * d:/D_Other 폴더에 들어있는 Tulips.jpg 파일을 클라이언트로 전송한다.
	 */
	
	private ServerSocket server;
	private Socket socket;
	private OutputStream os;
	private FileInputStream fis;
	
	
	public void serverStart() {
		File file = new File("d:/D_Other/Tulips.jpg");
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			socket = server.accept();
			System.out.println("File 전송 시작");

			fis = new FileInputStream(file);
			os = socket.getOutputStream();
			
			byte[] temp = new byte[1024];
			int c = 0;
			
			while( (c = fis.read(temp)) != -1 ) {
				os.write(temp, 0, c);
			}
			
			os.close();
			System.out.println("전송완료");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {fis.close();} 
				catch (IOException e) {}
			}

			if(os != null) {
				try {os.close();} 
				catch (IOException e) {}
			}
			
			if(socket != null) {
				try {socket.close();} 
				catch (IOException e) {}
			}
			
			if(server != null) {
				try {server.close();} 
				catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
}
