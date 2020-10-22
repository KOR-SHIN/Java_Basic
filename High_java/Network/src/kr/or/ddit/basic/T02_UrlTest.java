package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class T02_UrlTest {
	public static void main(String[] args) throws URISyntaxException {
		// <URL 클래스>
		// 인터넷에서 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
		// http://ddit.or.kr:80/index.html?ttt=123
		URL url = null;
		try {
			url = new URL("http", "ddit.or.kr", 80, "main/index.html?ttt=123#kkk");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.println("전체 URL 주소 : http://ddit.or.kr:80/main/index.html?ttt=123#kkk");
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("file : " + url.getFile()); // query정보 포함
		System.out.println("query : " + url.getQuery()); // '?'다음문장
		System.out.println("path : " + url.getPath()); // query정보 미포함
		System.out.println("port : " + url.getPort());
		System.out.println("ref : " + url.getRef());
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
		
		/**
		 * <URL 예제>
		 * http://java.sun.com/j2se/1.3/docs/guide/collection/designfaq.html#28
		 * ../../../demo/ifc/src/hello.java
		 * file:///~/calendar
		 * mailto:java-net@abc.com
		 */
	}
}
