package kr.or.ddit.basic;

import java.io.IOException;
import java.net.InetAddress;

/**
 * IP주소를 다루는 예제 
 */
public class T01_InetAddressTest {
	public static void main(String[] args) throws IOException{
	
		// naver의 ip정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		// 호스트 이름은 머신이름, 도메인명 또는 ip주소 문자열
		// 도메인명, 머신이름이 없으면 HostAddress(ip주소)가 출력된다.
		System.out.println("Host name : " + naverIp.getHostName());
		System.out.println("Host Address : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자신의 컴퓨터 ip 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name : " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address : " + localIp.getHostAddress());
		System.out.println();
		
		// ip주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		
		for(InetAddress ip : naverIps) {
			System.out.println(ip.toString());
		}
	}
}
