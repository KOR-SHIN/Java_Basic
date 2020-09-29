package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 보조 스트림 예제
 */
public class T13_DataIOStream {
	public static void main(String[] args) {
		
		try {
			
			FileOutputStream fos = new FileOutputStream("d:/test/data.txt");
			
			// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF("홍길동");
			dos.writeInt(17);
			dos.writeFloat(3.14F);
			dos.writeDouble(3.14);
			dos.writeBoolean(true);
			System.out.println("<< 작업 완료 >>");
			
			dos.close();
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// 출력한 자료 읽어오기
			
			FileInputStream fis = new FileInputStream("d:/test/data.txt");
			
			DataInputStream dis = new DataInputStream(fis);
			
			// 저장된 순서대로 읽어야 한다 (순서가 바뀌면 복원이 제대로 되지 않는다)
			System.out.println("문자형 자료 : " + dis.readUTF());
			System.out.println("정수형 자료 : " + dis.readInt());
			System.out.println("실수형(float) 자료 : " + dis.readFloat());
			System.out.println("실수형(double) 자료 : " + dis.readDouble());
			System.out.println("논리형 자료 : " + dis.readBoolean());
			
			dis.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
