package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T02_ListSortTest {

	public static void main(String[] args) {
		
		/**
		 * 정렬과 연관된 interface는 Comparable과 Comparator가 존재한다.
		 * 보통 객체 자체에 정렬기능을 넣기위해서는 Comparable을 구현하고,
		 * 정렬기준을 별도로 구현하고 싶은 경우에는 Comparator를 구현한다.
		 * 
		 * Comparable에서는 CompareTo(Object obj)메서드를 구현하고,
		 * Comparator에서는 Compare(Object obj1, Object obj2)를 구현한다.
		 * 
		 */
		
		List<String> list = new ArrayList<>();
		list.add("일지매");
		list.add("홍길동");
		list.add("최신섭");
		list.add("탐관오리");
		list.add("장보고");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
		// 정렬은 Collections.sort()메서드를 사용하여 정렬한다.
		// 기본적으로 '오름차순 정렬'을 수행한다.
		// 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 
		// Collections.sort()메서드에 변수로 넘겨주면 된다.
	
		Collections.sort(list);
		System.out.println("정렬 후(Collections.sort) : " + list);
		
		Collections.shuffle(list);
		System.out.println("섞기 후(Collections.shuffle) : " + list);
		
		// 정렬방식을 결정하는 객체(정렬자)를 이용하여 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("정렬 후 (정렬자 이용) : " + list);
		
		
		
		
	}
}


/**
 * 정렬방식을 결정하는 class는 Comparator라는 인터페이스를 구현한다.
 * Comparator인터페이스의 Compare()라는 메서드를 재정의 하여 구현한다.
 * 내가 정렬방식을 정하고 싶은경우 Comparator interface를 구현하여 Compare()메서드를 오버라이드한다.
 */
class Desc implements Comparator<String> {

	
	/**
	 * Compare()메서드의 반환값을 결정하는 방법
	 * 메서드가 양수를 반환하면 두 값의 순서가 바뀐다. (오름차순 기준)
	 * 
	 * 오름차순 정렬
	 * 앞의 값이 크면 `양수`, 같으면 `0`, 앞의 값이 작으면 `음수`를 반환한다.
	 * String 객체에서는 정렬을 위해서 CompareTo()메서드가 구현되어 있지만
	 * 이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	 * (Wrapper class의 Date, File클래스에도 구현되어 있다)
	 * 
	 * 내림차순 정렬
	 * 오름차순 정렬결과 * -1
	 */
	@Override
	public int compare(String str1, String str2) {
		
		return str1.compareTo(str2) * 1;
	}
	
}