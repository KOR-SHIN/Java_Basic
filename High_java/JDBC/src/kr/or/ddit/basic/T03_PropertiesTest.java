package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 외부의 properties파일을 읽어와 Properties객체로 처리하기 
 */
public class T03_PropertiesTest {
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");
		
		try {
			// 파일 읽기를 수행할 FileInputStream객체 생성
			FileInputStream fis = new FileInputStream(file);
			
			// Properties객체로 파일 내용 읽기
			// 파일이 내용을 읽어와 key와 value값으로 분류한 후 Properties객체에 저장한다.
			prop.load(fis);
			
			// 읽어온 자료 출력하기
			
			// key값만 읽어와 Enumeration객체로 반환하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			
			// key값 개수만큼 반복해서 출력하기
			
			// keys.hasMoreElement()
			// 다음 포인터 위치에 자료가 있으면 true, 없으면 false
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " => " + value);
			}
			System.out.println("출력완료");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	
	
	}
}