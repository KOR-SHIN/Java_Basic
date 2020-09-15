package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class T09_Lotto {

	/**
	 * 사용자는 로또를 구매할 때 구매할 금액을 입력하고 입력한 금액에 맞게 로또번호를 출력한다. (단, 로또 한장의 금액은 1000원이고
	 * 거스름돈도 계산하여 출력한다.)
	 * 
	 * ========================== 
	 * Lotto 프로그램
	 * ========================== 
	 * 1. Lotto 구입
	 * 2. 프로그램 종료 
	 * ========================== 
	 * 메뉴선택 : 1 <-- 입력
	 * 
	 * Lotto 구입 시작
	 * 
	 * (1000원에 로또번호 하나입니다.) 금액 입력 : 2500 <-- 입력
	 * 
	 * 행운의 로또번호는 아래와 같습니다. 
	 * 로또번호1 : 2,3,4,5,6,7 
	 * 로또번호2 : 20,21,22,23,24,25
	 * 
	 * 받은 금액은 2500원이고 거스름돈은 500원입니다.
	 * 
	 * ========================== 
	 * Lotto 프로그램 
	 * 1. Lotto 구입
	 * 2. 프로그램 종료 
	 * ========================== 
	 * 메뉴선택 : 2 <-- 입력
	 * 
	 * 감사합니다
	 */
	
	void run() {
		
		while(true) {
		
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("==========================");
			System.out.println("1. Lotto 구매");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			
			Scanner sc = new Scanner(System.in);
			int cmd = -1;
			
			try {
				cmd = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
				System.out.println();
				continue;
			} catch(Exception e) {
				System.out.println("숫자만 입력하세요.");
				System.out.println();
				continue;
			}
			
			switch(cmd) {
			case 1:
				buyLotto();
				break;
				
			case 2:
				System.out.println("이용해주셔서 감사합니다.");
				System.exit(0);
			}
			
		}
	
	
	
	}

	private void buyLotto() {

		boolean flag = true;
		
		while(flag) {
			
			System.out.println();
			System.out.println("Lotto 한 장 가격 : 1000원");
			System.out.print("구매금액을 입력하세요 : ");
			System.out.println();

			Scanner sc = new Scanner(System.in);
			int price = 1000;
			int money = 0;
			
			try {
				money = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				System.out.println();
				continue;
			} catch(Exception e) {
				System.out.println("숫자만 입력 가능합니다.");
				System.out.println();
				continue;
			}
			
			if(money < 1000) {
				System.out.println("금액이 모자랍니다.");
				System.out.println("다시 입력해주세요.");
				System.out.println();
				continue;
			} else {
				Map<String, Integer> info = new HashMap<>();
				info.put("price", price);
				info.put("money", money);
				info.put("ticketNumber", money/price);
				makeNumber(info);
				return;
			}
		}
	}

	private void makeNumber(Map<String, Integer> info) {
		
		Set<Integer> lotto = new TreeSet<>();
		
		
		for(int i=0; i<info.get("ticketNumber"); i++) {
			
			while(true) {
				lotto.add((int)(Math.random()*45)+1);
				if(lotto.size() > 5) {
					System.out.println((i+1) + " 번째 Lotto ticket : " + lotto);
					lotto.clear();
					break;
				}
			}
		}
		
		System.out.println();
		System.out.println("받은금액은 " + info.get("money") + "이고, 거스름돈은 " + info.get("money")%info.get("price") + "원 입니다.");
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		T09_Lotto lotto = new T09_Lotto();
		lotto.run();
	}
	
}
