package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 * 입출력 스트림의 중간에 buffer를 넣음으로써, 데이터의 입출력이 buffer를 통해 이루어진다.
 * buffer에 데이터를 미리 가져다놓기 때문에 효율적인 입출력 처리가 가능하다.
 */
public class T11_BufferedIOTest {
	public static void main(String[] args) throws IOException {
		
		// 입출력의 성능 향상을 위해서 버퍼를 이용하는 보조스트림
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/test/bufferedTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192(8kb)로 지정
			// 버퍼의 크기가 5인 스트림 객체 생성
			bos = new BufferedOutputStream(fos, 5);
			
			for(int i='1'; i<='9'; i++) {
				// 지정한 buffer의 크기가 채워지면 write() 작업을 수행한다.
				bos.write(i);
			}
			
			// 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력
			// close()호출 시 자동으로 호출되는 메서드
			// 지정한 버퍼의 크기만큼 데이터가 저장되어야 write작업을 하지만
			// 버퍼가 모두 안채워지고 작업이 종료될 수 있으니 강제로 write하기 위해 flush() 호출
			bos.flush();
			
			bos.close();
			System.out.println("<< 작업종료 >>");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
