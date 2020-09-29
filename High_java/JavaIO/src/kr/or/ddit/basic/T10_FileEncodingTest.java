package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10_FileEncodingTest {
	public static void main(String[] args) throws IOException {
		/**
		 * <OutputStreamWriter>
		 * OutputStream(Byte기반 출력 객체)를
		 * Writer(문자기반의 출력용 객체)로 변환해주는 객체
		 * 인코딩 방식을 지정하여 저장할 수 있다.
		 */
		
		// 키보드로 입력한 내용을 파일로 저장하는데
		// out_utf8.txt파일은 utf-8 인코딩 방식으로,
		// out_ansi.txt파일은 ms949 인코딩 방식으로 저장한다.
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		// 파일 출력용
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "MS949");
		
		int c;
		
		System.out.println("아무거나 입력하세요");
		
		while( (c=isr.read()) != -1 ) {
			osw1.write((char)c);
			osw2.write((char)c);
		}
		
		System.out.println(" << 문서작업 완료 >>");
		isr.close();
		osw1.close();
		osw2.close();
	}
}
