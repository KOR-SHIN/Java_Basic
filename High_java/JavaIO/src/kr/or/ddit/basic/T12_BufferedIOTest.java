package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 * 문자기반의 BufferedStream
 */
public class T12_BufferedIOTest {

	public static void main(String[] args) {
		
		try { 
			// 이클립스에서 만든 자바프로그램이 실행되는 기본 위치는
			// 해당 프로젝트폴더이다. (LB_JavaIOTest Directory)
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
			
			// BufferedReader를 사용해서 Line단위로 읽어오기
			// 문자기반이기 때문에 내부에 문자단위의 buffer를 가지고있다.
			BufferedReader br = new BufferedReader(fr);
			String temp = "";
			
			for(int i=1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%d : %s\n", i, temp);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
