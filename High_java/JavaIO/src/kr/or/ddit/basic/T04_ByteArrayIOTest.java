package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;

		byte[] temp = new byte[4];
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			// available() => 읽어 올 수 있는 byte() 길이를 반환
			int len;
			while( (len=bais.read(temp)) != -1) {
//				bais.read(temp); // 읽어온 정보를 temp에 저장한다(4byte씩 읽어서 저장하는 것, 4개가 반드시 채워져야 한다)
//				baos.write(temp); // 읽어온 정보가 들어있는 temp를 OutputStream에 저장한다.
				
				// temp배열의 내용 중에서 0번째부터 len개수만큼만 write하도록 한다.
				baos.write(temp, 0, len);
				
				
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			
			outSrc = baos.toByteArray();
			
			System.out.println("inSrc  => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			bais.close();
			baos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
