package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class T03_ListSortTest {

	public static void main(String[] args) {

		List<Member> memList = new ArrayList<>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(2, "일지매", "010-2222-2222"));
		memList.add(new Member(3, "강감찬", "010-3333-3333"));
		memList.add(new Member(4, "장보고", "010-4444-4444"));
		memList.add(new Member(5, "이순신", "010-5555-5555"));
		memList.add(new Member(6, "정약용", "010-6666-6666"));

		System.out.println("===정렬 전===");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println();
		System.out.println();

		Collections.sort(memList); // 정렬하기

		System.out.println("===정렬 후===");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println();
		System.out.println();

		Collections.shuffle(memList);
		// 외부정렬자를 이용한 정렬
		System.out.println("===정렬 전===");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println();
		System.out.println();

		Collections.sort(memList, new SortNumDesc());

		System.out.println("===정렬 후===");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println();
	}

}

/**
 * 외부 정렬자이기 때문에 Comparator를 사용한다. (Compare Method를 사용하기 위함) Member객체의 번호(num)을
 * 내림차순으로 정렬하기
 */
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {

//		 if(mem1.getNum() > mem2.getNum()) {
//		 // 앞에 있는 값이 크면 음수를 반환한다 (내림차순이기 때문에 음수 반환)
//		 return -1;
//		 } else if(mem1.getNum() == mem2.getNum()) {
//		 return 0;
//		 } else {
//		 return 1;
//		 }

		// Wrapper Class에서 제공하는 메서드를 이용하는 방법
//		return Integer.compare(mem1.getNum(), mem2.getNum());
		
		// Wrapper Class에서 제공하는 메서드를 이용하는 방법 2
		// 내부적으로 Comparable이 구현되어 있기때문에 compareTo 활용가능
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}
}

/**
 * 회원의 정보를 저장하는 클래스 (회원이름을 기준으로 오름차순 정렬이 될 수 있는 클래스 만들기)
 *
 */
class Member implements Comparable<Member> {

	private int num;
	private String name;
	private String tel;

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	/**
	 * 이름을 기준으로 오름차순 정렬이 되도록 오버라이드한 compareTo() Method
	 */
	@Override
	public int compareTo(Member member) {

		return getName().compareTo(member.getName()) * 1;
	}

}
