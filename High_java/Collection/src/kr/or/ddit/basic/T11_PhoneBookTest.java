package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
문제) 이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고, 이 Phone클래스를 이용하여 
     전화번호 정보를 관리하는 프로그램을 완성하시오.
     이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
     
     전체의 전화번호 정보는 Map을 이용하여 관리한다.
     (key는 '이름'으로 하고 value는 'Phone클래스의 인스턴스'로 한다.)


실행예시)
===============================================
   전화번호 관리 프로그램(파일로 저장되지 않음)
===============================================

  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 1  <-- 직접 입력
  
  새롭게 등록할 전화번호 정보를 입력하세요.
  이름 >> 홍길동  <-- 직접 입력
  전화번호 >> 010-1234-5678  <-- 직접 입력
  주소 >> 대전시 중구 대흥동 111  <-- 직접 입력
  
  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 5  <-- 직접 입력
  
  =======================================
  번호   이름       전화번호         주소
  =======================================
   1    홍길동   010-1234-5678    대전시
   ~~~~~
   
  =======================================
  출력완료...
  
  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 0  <-- 직접 입력
  
  프로그램을 종료합니다...
  
*/
public class T11_PhoneBookTest {

	private Scanner scan;
	private Map<String, Phone> pb = new HashMap<>();

	public T11_PhoneBookTest() {
		scan = new Scanner(System.in);
	}

	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");
	}

	// 프로그램을 시작하는 메서드
	public void phoneBookStart() {
		System.out.println("===============================================");
		System.out.println("   전화번호 관리 프로그램(파일로 저장되지 않음)");
		System.out.println("===============================================");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력
			scan.nextLine();

			switch (menuNum) {
			case 1:
				insert(); // 등록
				break;
			case 2:
				update(); // 수정
				break;
			case 3:
				delete(); // 삭제
				break;
			case 4:
				search(); // 검색
				break;
			case 5:
				displayAll(); // 전체 출력
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	/**
	 * 조건에 맞는 정보를 검색하는 메서드
	 */
	private void search() {
		System.out.println();
		System.out.println("조회하려는 이름을 입력하세요.");
		System.out.print("이름 : ");
		String name = scan.next();
		scan.nextLine();
		
		Phone p = pb.get(name);
		
		if( p == null) {
			System.out.println("[" + name + "]님의 정보가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("[" + name + "]님의 정보");
		System.out.println("이    름 : " + pb.get(name).getName());
		System.out.println("전화번호 : " + pb.get(name).getHp());
		System.out.println("주    소 : " + pb.get(name).getAdd());
		
	}

	/**
	 * 등록되어 있는 정보를 삭제하는 메서드
	 */
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 : ");
		String name = scan.next();
		scan.nextLine();
		
		if( pb.remove(name) == null ) {
			System.out.println("[" + name + "]님의 정보가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("[" + name + "]님의 정보가 삭제되었습니다.");
		
	}

	/**
	 * 등록되어 있는 정보를 수정하는 메서드
	 */
	private void update() {
		
		System.out.println();
		System.out.println("===수정할 전화번호 정보를 입력하세요===");
		
		System.out.print("이름 : ");
		String name = scan.next();
		scan.nextLine();
		
		// 등록되어 있는지 검사한다.
		if(pb.get(name) == null) {
			System.out.println("[" + name + "]님은 등록된 정보가 없습니다.");
			return;
		}
		
		System.out.print("전화번호 : ");
		String hp = scan.next();
		scan.nextLine();
		
		System.out.print("주소 : ");
		String add = scan.nextLine();
		
		// 기존의 key값에 새로운 value값을 넣으면 value가 덮어쓰기 되어 수정된다.
		pb.put(name, new Phone(name, hp, add));
		System.out.println("[" + name + "]님의 정보가 수정되었습니다.");

	}

	/**
	 * 전체 자료를 출력하는 메서드
	 */
	private void displayAll() {
		Set<String> keySet = pb.keySet();
		
		if(keySet.isEmpty()) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}
		
		Iterator<String> it = keySet.iterator();
		
		System.out.println("==============================================");
		System.out.println("\t등록된 모든 정보");
		System.out.println("==============================================");
		int cnt = 0;
		while(it.hasNext()) {
			cnt++;
			String name = it.next();
			Phone temp = pb.get(name);
			
			System.out.println(" " + cnt + "\t" + temp.getName() +"\t" + temp.getHp() + "\t" + temp.getAdd());
		}
		System.out.println("==============================================");
		System.out.println("\t<출력이 완료되었습니다>");
	}

	/**
	 * 새로운 전화번호를 등록하는 메서드
	 */
	private void insert() {
		System.out.println();
		System.out.println("===새롭게 등록할 전화번호 정보를 입력하세요===");
		
		System.out.print("이름 : ");
		String name = scan.next();
		scan.nextLine();
		
		// 이미 등록된 사람인지 검사
		// get()메서드로 값을 가져올 때 자료가 없으면 null을 반환한다.
		if(pb.get(name) != null) {
			System.out.println("[" + name + "]님은 이미 등록되어 있습니다.");
			return;
		}
		
		System.out.print("전화번호 : ");
		String hp = scan.next();
		scan.nextLine();
		
		System.out.print("주소 : ");
		String add = scan.nextLine();
		
		pb.put(name, new Phone(name, hp, add));
		System.out.println("[" + name + "]님의 정보가 등록되었습니다.");
		
	}

	public static void main(String[] args) {
		new T11_PhoneBookTest().phoneBookStart();
	}
}


/**
 * 전화번호 정보를 저장하기 위한 VO 클래스
 *
 */
class Phone {
	
	private String name;
	private String hp;
	private String add;
	
	
	public Phone(String name, String hp, String add) {
		super();
		this.name = name;
		this.hp = hp;
		this.add = add;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHp() {
		return hp;
	}
	
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public String getAdd() {
		return add;
	}
	
	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", hp=" + hp + ", add=" + add + "]";
	}
	
	
	
	
	
}
