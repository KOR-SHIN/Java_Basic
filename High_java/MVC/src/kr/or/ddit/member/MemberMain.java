package kr.or.ddit.member;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberMain {
	
	private Scanner scan = new Scanner(System.in);
	private IMemberService service = new MemberServiceImpl();
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 자료 입력
				insertMember();
				break;
			case 2: // 자료 삭제
				deleteMember();
				break;
			case 3: // 자료 수정
				updateMember();
				break;
			case 4: // 전체 자료 출력
				displayMemberAll();
				break;
			case 5: // 작업 끝
				searchMember();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	/**
	 * 회원정보를 삭제하는 메서드 (입력받은 회원 ID를 이용하여 삭제)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원 ID >> ");

		String mem_id = scan.next();
		
		if (service.deleteMember(mem_id) > 0) {
			System.out.println(mem_id + "회원 삭제완료");
		} else {
			System.out.println(mem_id + "회원 삭제실패");
		}
	}

	/**
	 * 회원정보를 수정하는 메서드
	 */
	private void updateMember() {
		boolean check = false; // 기존회원 존재여부 체크
		MemberVO mv = new MemberVO();
		String mem_id = "";

		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");

			mem_id = scan.next();

			check = getMember(mem_id);

			if (!check) {
				System.out.println(mem_id + "인 회원이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}

		} while (check == false);
		mv.setMem_id(mem_id);
		
		System.out.print("회원이름 >> ");
		String mem_name = scan.next();
		mv.setMem_name(mem_name);
		
		System.out.print("회원 전화번호 >> ");
		String mem_tel = scan.next();
		scan.nextLine();
		mv.setMem_tel(mem_tel);

		System.out.print("회원 주소 >> ");
		String mem_addr = scan.nextLine();
		mv.setMem_addr(mem_addr);
		
		if (service.updateMember(mv) > 0) {
			System.out.println(mv.getMem_id() + "회원의 정보를 수정했습니다.");
		} else {
			System.out.println(mv.getMem_id() + "회원의 정보를 수정실패.");
		}

	}

	/**
	 * 전체회원 리스트를 화면에 표시해주는 메서드
	 */
	private void displayMemberAll() {
		List<MemberVO> memberList = service.displayAllMember();
		
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t\t주  소");
		System.out.println("----------------------------------------------------------------");
		for(MemberVO mem : memberList) {
			System.out.print(mem.getMem_id() + "\t" + mem.getMem_name() + "\t" + mem.getMem_tel() + "\t\t" + mem.getMem_addr());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println("출력완료");

	}

	private void insertMember() {

		boolean check = false; // 기존회원 존재여부 체크
		MemberVO mv = new MemberVO();
		String mem_id = "";

		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");

			mem_id = scan.next();

			check = getMember(mem_id);

			if (check) {
				System.out.println("회원 ID가 " + mem_id + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}

		} while (check == true);
		mv.setMem_id(mem_id);
		
		System.out.print("회원이름 >> ");
		String mem_name = scan.next();
		mv.setMem_name(mem_name);
		
		System.out.print("회원 전화번호 >> ");
		String mem_tel = scan.next();
		scan.nextLine();
		mv.setMem_tel(mem_tel);

		System.out.print("회원 주소 >> ");
		String mem_addr = scan.nextLine();
		mv.setMem_addr(mem_addr);
		
		if (service.insertMember(mv) > 0) {
			System.out.println(mv.getMem_id() + " 회원 가입 성공");
		} else {
			System.out.println(mv.getMem_id() + " 회원 가입 실패");
		}

		
	}

	/**
	 * 회원 ID를 이용하여 가입여부를 확인하는 메서드 SQL injection 공격을 방어하기위해 PreparedStatement를 사용한다.
	 * PreparedStatement와 Statement 차이점, SQL injection개념 찾아보기
	 * 
	 * @param memId
	 * @return
	 */
	private boolean getMember(String mem_id) {
		return service.getMember(mem_id);
	}
	
	/**
	 * 회원 정보를 검색하는 메서드
	 */
	private void searchMember() {
		/**
		 * 검색할 회원ID, 회원이름, 회원번호, 주소를 입력하면
		 * 검색한 정보만 사용하여 검색하는 기능을 구현하시오.
		 * 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
		 * 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		 * ex) 주소만 대전으로 입력하고 검색하면 주소지가 대전인 회원을 모두 검색한다.
		 */
		scan.nextLine();
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		
		System.out.println("회원 ID : ");
		String mem_id = scan.nextLine().trim();
		
		System.out.println("이름 : ");
		String mem_name = scan.nextLine().trim();

		System.out.println("전화번호 : ");
		String mem_tel = scan.nextLine().trim();
		
		System.out.println("주소 : ");
		String mem_addr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_name(mem_name);
		mv.setMem_addr(mem_addr);
		mv.setMem_tel(mem_tel);
		
		List<MemberVO> memberList = service.getSearchMember(mv);
		
		// 입력한 정보로 검색한 내용을 출력하기.
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t\t주  소");
		System.out.println("----------------------------------------------------------------");
		for(MemberVO mem : memberList) {
			System.out.print(mem.getMem_id() + "\t" + mem.getMem_name() + "\t" + mem.getMem_tel() + "\t\t" + mem.getMem_addr());
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println("출력완료");
	}

	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}
}
