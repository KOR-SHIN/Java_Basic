package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class T03_UrlConnetionTest {
	public static void main(String[] args) throws IOException {
		
		// URLConnection : 애플리케이션 URL간의 통신 연결을 위한 추상클래스
		// 특정 서버(ex. naver.com)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnetion 객체 구하기
		// URLConnection 클래스는 사용자 인증이나 보안이 설정되어 있지 않은 웹사이트에 접근하여
		// 파일 등을 다운로드 받는데 많이 사용된다.
		// 리소스 연결전에 구성되어야 하고, URLConnection 인스턴스는 재사용 불가능하다.
		URLConnection urlCon = url.openConnection();

		// header 정보가져오기
		System.out.println("Content-Type : " + urlCon.getContentType());
		System.out.println("Encoding : " + urlCon.getContentEncoding());
		System.out.println("Content : " + urlCon.getContent());
		
		// 전체 Header정보 출력하기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		Iterator<String> it = headerMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			System.out.println("key " + " : " + headerMap.get(key));
		}
		System.out.println("==================================================");
		
		// 해당 호스트의 페이지 내용 가져오기
		// 파일을 읽어오기 위한 스트림 객체 생성
		// 방법 1. URLConnection의 getInputStream() 이용
		//		InputStream is2 = url.openConnection().getInputStream();
		// 방법 2. URL객체의 openStream() 이용
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		
		int c;
		
		while( (c=isr.read()) != -1) {
			System.out.print((char)c);
		}
		
		// Stream 닫기
		isr.close();
		
		
	}
}
