package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07_FileWriterTest {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장
		
		// 콘솔(표준 입출력장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader => Byte기반 스트림을 문자기반 스트림으로 변환해 주는 보조 스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		FileWriter fw = null;
		
		try {
			// 파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int c;
			
			System.out.println("아무거나 입력하세요.");
			
			// 콘솔에서 입력할 때 입력의 끝 표시는 Ctrl + z키를 누른다.(-1을 반환시키는 것)
			// isr.read()는 콘솔창에 사용자가 입력한 내용을 읽어온다.
			while( (c = isr.read() ) != -1) {
				// 콘솔에서 입력받는 값을 파일에 출력
				// 동일한이름의 문서가 이미 존재하는 경우
				// 이전 내용이 없어지고 가장 최근에 입력한 내용으로 바뀜
				fw.write(c);
			}
			
			System.out.println("<< 문서작업 종료 >>");
			
			isr.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
