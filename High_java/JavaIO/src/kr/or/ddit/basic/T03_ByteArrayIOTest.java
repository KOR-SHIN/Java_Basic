package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data;
		
		// read() -> return range 0 ~ 255 (읽어올 내용이 없으면 -1이 return된다)
		// 매개변수가 없는 read()는 1byte씩 정보를 읽어온다.
		while( (data=bais.read()) != -1) {
			baos.write(data);
		}
		
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc  => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		try {
			// 사용한 byteStream객체를 닫아준다.
			bais.close();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
