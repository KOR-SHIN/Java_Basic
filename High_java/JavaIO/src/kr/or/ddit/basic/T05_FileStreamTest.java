package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Byte기반 Stream
 */
public class T05_FileStreamTest {
	public static void main(String[] args) {
		
		// FileInputStream 객체를 이용한 파일 내용 읽기
		FileInputStream fis = null;
		
		try {
			// 파일경로 정보를 문자열로 지정
//			fis = new FileInputStream("d:/D_Other/sample.txt");

			File file = new File("d:/D_Other/sample.txt");
			
			// 매개변수에 파일객체를 넘겨주는 방법
			fis = new FileInputStream(file);
			int c; // 읽어온 데이터를 저장할 변수
			
			while((c = fis.read()) != -1) {
				System.out.print((char)c);
			}
			
			fis.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
