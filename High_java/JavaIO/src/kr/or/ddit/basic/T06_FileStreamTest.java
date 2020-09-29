package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 문자기반 Stream
 */
public class T06_FileStreamTest {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream("d:/D_Other/out.txt");
			
			for(char ch='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			System.out.println("<< 문서작성 완료 >>");
			
			// write작업 후 stream닫기
			fos.close();
			
			//============================================
			// 저장한 파일의 내용을 읽어와 화면에 출력하기.
			
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int c;
			System.out.println("<< 문서내용 출력 >>");
			while( (c=fis.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("<< 문서출력 완료 >>");
			
			fis.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
