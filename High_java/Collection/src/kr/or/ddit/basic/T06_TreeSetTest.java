package kr.or.ddit.basic;

import java.util.SortedSet;
import java.util.TreeSet;

public class T06_TreeSetTest {

	public static void main(String[] args) {
		
		/**
		 * HashSet은 데이터의 순서가 없으나 (등록하는 순서와 일치하지 않음)
		 * TreeSet은 자동정렬 기능이 구현되어 있다.
		 * Tree구조를 사용했기 때문에 검색속도가 빠른 장점이 있다.
		 */
		
		
		TreeSet<String> ts = new TreeSet<>();
		
		//영어 대문자를 문자열로 반환하여 TreeSet에 저장하기
		for(char ch='Z'; ch>='A'; ch--) {
			// Z부터 입력해도 데이터가 삽입되는 동시에 정렬이 일어난다.
			String temp = String.valueOf(ch);
			ts.add(temp);
		}
		System.out.println("TreeSet Data : " + ts);

		// TreeSet에 저장된 자료 중 특정한 자료보다 작은 자료를 찾아서 sortedSet으로 반환하는
		// 메서드가 존재한다.
		// headSet(기준값) => 기본적으로 '기준값'은 포함하지 않는다.
		// headSet(기준값, 논리값) => 논리값이 true이면'기준값 포함'
		
		SortedSet<String> sts = ts.headSet("K");
		System.out.println("K 이전 자료 : " + sts);
		System.out.println("K 이전 자료(기준값 포함) : " + ts.headSet("K", true));
		
		// '기준값' 보다 큰 자료형을 찾아 sortedSet으로 반환하는 메서드
		// tailSet(기준값) => 기본적으로 '기준값'을 포함한다.
		// tailSet(기준값, 논리값) => 논리값이 false면 '기준값'을 미포함한다.
		SortedSet<String> sts2 = ts.tailSet("K");
		System.out.println("K 이후 자료 : " + sts2);
		System.out.println("K 이후 자료(기준값 미포함) : " + ts.tailSet("K", false));
		
		// subSet(기준값1, 기준값2) => 기준값1~기준값2 사이의 값을 가져옴 (포함~미포함)
		// subSet(기준값1, 논리값1, 기준값1, 논리값2) => 각 논리값을 포함할 지 여부를 결정한다 (논리값 true : 포함)
		System.out.println("K(포함) 부터 N(미포함) 까지 : "	+ ts.subSet("K", "N"));
		System.out.println("K(포함) 부터 N(포함) 까지 : " + ts.subSet("K", true, "N", true));
		System.out.println("K(포함) 부터 N(미포함) 까지 : " + ts.subSet("K", true, "N", false));
		System.out.println("K(미포함) 부터 N(포함) 까지 : " + ts.subSet("K", false, "N", true));
		System.out.println("K(미포함) 부터 N(미포함) 까지 : " + ts.subSet("K", false, "N", false));
		
	}
}
