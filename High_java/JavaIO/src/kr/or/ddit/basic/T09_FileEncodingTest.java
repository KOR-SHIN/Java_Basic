package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	/**
	 * InputStreamReader 객체는 파일의 인코딩 양식을 지정할 수 있다.
	 * 형식) new InputStreaReader(Byte기반 스트림객체, 인코딩방식) 
	 * 
	 * <Encoding 방식>
	 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나뉜다.
	 * 원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서
	 * EUC-KR 방식에서 확장하였기 때문에 MS949라고도 부른다.
	 * CP949는 EUC-KR의 확장이며, 하위호환성이 있다.
	 * 
	 * MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
	 * UTF-8 => 유니코드 UTF-8인코딩 방식(영문자 및 숫자 : 1byte, 한글: 3byte), 가변적
	 * 	(한글 인코딩은 크게 ANSI계열과 유니코드 계열로 나눌 수 있다)
	 * US-ASCII => 영문전용 인코딩 방식
	 * 
	 * ANSI는 영어를 표기하기 위해 만든 코드 규격으로 자체에 한글이 없었다가
	 * 나중에 EUC-KR(Unix 계열), CP949(Window 계열)라는 식으로 한글이 포함되었다.
	 */
	
	public static void main(String[] args) {
		// 파일 인코딩을 이용하여 읽어오기
		FileInputStream fis = null; // Byte기반 Stream
		InputStreamReader isr = null; // Byte기반 Stream을 문자기반 Stream으로 바꿔줌 (보조 Stream)
		
		try {
			/**
			 * FileInputStream객체를 생성한 후 이 객체를 매개변수로 받는
			 * InputStreamReader객체를 생성한다.
			 * (Byte 입력 스트림에 연결되어 문자 입력 스트림인 Reader로 변환시킨다)
			 */
			
//			fis = new FileInputStream("d:/D_Other/text_utf8.txt");
			fis = new FileInputStream("d:/D_Other/text_ansi.txt");
//			isr = new InputStreamReader(fis, "UTF-8");
			isr = new InputStreamReader(fis, "CP949"); // ms949, euc-kr도 ANSI계열이기 때문에 인코딩이 된다.
			
			int c;
			
			while( (c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println(" << 문서출력 완료 >> ");
			
			// 기반스트림을 close하면 보조스트림도 같이 close된다.
			fis.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
}
