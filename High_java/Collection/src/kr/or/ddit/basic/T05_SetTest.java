package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05_SetTest {

	
	public static void main(String[] args) {
	
		/**
		 * <List와 Set>
		 * 
		 * List
		 * 입력 데이터의 순서가 유지된다
		 * 데이터의 중복을 허용한다.
		 * 
		 * Set
		 * 입력 데이터의 순서가 없다
		 * 데이터의 중복을 허용하지 않는다.
		 */
		
		Set set = new HashSet();
		
		// Set에 데이터를 추가할 때는 add()를 사용한다.
		set.add("DD");
		set.add("AA");
		set.add(2);
		set.add("CC");
		set.add("BB");
		set.add(1);
		set.add(3);
		
		System.out.println("Set 데이터 : " + set);
		System.out.println();
		
		// Set은 데이터의 순서가 없고, 중복을 허용하지 않는다.
		// 이미 있는 데이터를 add로 입력하면 false를 리턴하고 데이터가 추가되지 않는다.
		boolean isAdd = set.add("FF");
		System.out.println("중복되지 않을 때 (FF 추가) : " + isAdd);
		System.out.println("Set 데이터 : " + set);
		System.out.println();
		
		isAdd = set.add("BB");
		System.out.println("중복됐을 때 (BB 추가) : " + isAdd);
		System.out.println("Set 데이터 : " + set);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령어가 따로 없기 때문에
		// 해당 자료를 삭제한 뒤 새로운 데이터를 추가하는 방식으로 수정해야 한다.
		
		// Set데이터 삭제 메서드
		// clear() -> Set에 있는 데이터를 모두 삭제
		// remove(E e) -> 매개변수로 지정된 값을 Set 안에서 삭제
		
		// "FF"를 "EE"로 수정하기
		set.remove("FF");
		System.out.println("FF 삭제 후 Set 데이터 : " + set);
		System.out.println();
		
		set.add("EE");
		System.out.println("EE 추가 후 Set 데이터 : " + set);
		System.out.println();
		
		
		
		Iterator it = set.iterator();
		
		int i = 1;
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
			if(++i%3 == 0) { 
				System.out.println();
			}
		}
		System.out.println();
	
		set.clear();
		System.out.println("clear 후 Set 데이터 : " + set);
		System.out.println("Set의 자료 개수 : " + set.size());
		System.out.println();
	
		// 1~100사이의 중복되지 않는 정수 만들기
		
		Set<Integer> intSet = new HashSet<>();
		
		while(intSet.size() < 9) {
			intSet.add((int)(Math.random()*100) + 1);
		}
		
		System.out.println("intSet Data : " + intSet);
		
		List<Integer> intList = new ArrayList<>(intSet);
		Collections.sort(intList);
		
		System.out.println("intList(sorted) Data : " + intList);
		
		
	
	
	
	}
	
}
