package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T13_HotelTest {

/*
 문제)

호텔 운영을 관리하는 프로그램 작성.(Map이용)
 - 키값은 방번호 
 
실행 예시)

**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************

 */
	Map<String, Hotel> info = new HashMap<>();
	
	private void run() {
		while(true) {
			System.out.println("**************************");
			System.out.println("호텔 문을 열었습니다.");
			System.out.println("**************************");
	
			System.out.println("*******************************************");
			System.out.println("\t어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인 2. 체크아웃 3. 객실상태 4. 업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
	
			Scanner sc = new Scanner(System.in);
			int cmd = -1;
			
			try {
				cmd = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("정확히 입력하십시오.");
				continue;
			} catch(Exception e) {
				System.out.println("정확히 입력하십시오.");
				continue;
			}
			
			switch(cmd) {
			case 1:
				checkIn();
				break;
				
			case 2:
				checkOut();
				break;
				
			case 3:
				checkRoom();
				break;
				
			case 4:
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				System.exit(0);
			}
		}
	}
	

	private void checkRoom() {
		
		if( info.isEmpty() ) {
			System.out.println("예약정보가 존재하지 않습니다.");
			return;
		}
		
		Set infoSet = info.entrySet();
		Iterator it = infoSet.iterator();
		
		System.out.println("\t[방번호]\t[투숙객]");
		
		while(it.hasNext()) {
			Map.Entry<String, Hotel> entry = (Map.Entry<String, Hotel>)it.next();
			System.out.println("\t[" + entry.getKey() + "]" + "\t\t" + "[" + entry.getValue().getName() + "]");
		}
		
	}


	private void checkOut() {
		
		while(true) {
			
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			try {
				Scanner sc = new Scanner(System.in);
				String room_no = sc.next();
				
				if( info.containsKey(room_no) ) {
					info.remove(room_no);
					System.out.println("[" + room_no + "]방 체크아웃을 완료하였습니다.");
					return;
				}
				
				System.out.println("[" + room_no + "]방은 투숙객이 없습니다.");
				return;
				
			} catch(InputMismatchException e) {
				System.out.println("정확히 입력하십시오.");
			} catch(Exception e) {
				System.out.println("정확히 입력하십시오.");
			}
		}
	}



	private void checkIn() {
		
		while(true) {
			
			try {
				Scanner sc = new Scanner(System.in);

				System.out.println("몇 번방을 선택하시겠습니까?");
				String room_no = sc.next();
				sc.nextLine();
				
				if( info.containsKey(room_no) ) {
					System.out.println("이미 투숙객이 있는 방입니다.");
					return;
				}
				
				System.out.println("누구를 체크인 하시겠습니까?");
				String name = sc.next();
				sc.nextLine();
				
				info.put(room_no, new Hotel(name, room_no));
				
				System.out.println("[" + name + "]고객님 [" + room_no + "]방에 체크인이 완료되었습니다.");
				System.out.println();
				return;
				
			} catch(InputMismatchException e) {
				System.out.println("정확히 입력하세요.");
				continue;
			} catch(Exception e) {
				System.out.println("정확히 입력하세요.");
				continue;
			}
		}
	}

	public static void main(String[] args) {
		new T13_HotelTest().run();
	}

}

class Hotel {
	String name;
	String room_no;
	
	
	public Hotel(String name, String room_no) {
		super();
		this.name = name;
		this.room_no = room_no;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRoom_no() {
		return room_no;
	}
	
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	
	
}
