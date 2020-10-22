package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBundle 객체 
 * 확장자가 properties인 파일 정보를 읽어와 key, value값을 분리한 정보를 갖는 객체
 * 읽어올 파일은 'key = value' 형태로 되어있어야 한다.
 */
public class T04_ResourceBundle {
	// ResouceBundle 객체를 이용한 파일 읽어오기
	
	public static void main(String[] args) {
		/**
		 * ResourceBundle객체 생성하기
		 * 확장자는 항상 properties이기 때문에 파일을 저장할 때는 '파일명'만 지정하고 확장자는 지정하지 않는다.
		 * 생성자 Locale정보를 지정해주면 Locale에 지정된 국가에 맞는 properties파일을 읽어온다.
		 * properties파일을 생성할때 파일명_locale.properties로 만들면 Locale속성을 사용할 수 있다.
		 * Locale은 국제화를 위해 사용된다.
		 */
		
		// Locale이 ENGLISH로 지정되어 있기 때문에 db_en.properties file을 읽어온다.
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.ENGLISH);
		
		// locale정보를 통해 프로그램이 실행되고 있는 국가의 정보를 얻을 수 있다.
		// locale정보를 이용하여 해당 국가에 맞게 정보를 표시할 수 있다.
		// ex. 화폐단위, 날짜 등을 국가에 맞게 보여줌
//		System.out.println(Locale.getDefault());

		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		// key값 개수만큼 반복해서 value값 가져오기
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			// key값을 이용하여 value값을 읽어오는 방법
			// bundle객체 변수.getString(key)
			String value = bundle.getString(key);
			
			System.out.println(key + " : " + value);
		}
		System.out.println("출력완료");
		
	}


}
