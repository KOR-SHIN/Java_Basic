package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpFileClient {
	
	/**
	 * 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 
	 * d:/C_Lib 폴더에 저장한다.
	 */
	
//	private Socket socket;
//	private FileOutputStream fos;
//	private InputStream is;
	
	public void clientStart() {
		
		File file = new File("d:/C_Lib/Tulips_clone.jpg");
		
		try (
				// try with 문법
				// try 괄호안에서 선언한 변수들은 try-catch문이 끝나면
				// 모두 close() 해준다.
				Socket socket = new Socket("127.0.0.1", 7777);
				FileOutputStream fos = new FileOutputStream(file);
				InputStream is = socket.getInputStream()
			) 
		{
			System.out.println("파일 다운로드 시작");
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			while( (len = is.read(temp)) != -1) {
				fos.write(temp, 0, len);
			}
			
			fos.close();
			System.out.println("파일 다운로드 완료");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
	
}
