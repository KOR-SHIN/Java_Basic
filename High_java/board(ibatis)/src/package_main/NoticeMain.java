package package_main;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import package_service.INoticeService;
import package_service.NoticeServiceImpl;
import package_vo.NoticeVO;

public class NoticeMain {
	
	private Scanner scan = new Scanner(System.in);
	private INoticeService service = NoticeServiceImpl.getInstance();

	public static void main(String[] args) {
		NoticeMain main = new NoticeMain();
		main.start();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 작성");
		System.out.println("  2. 게시글 수정");
		System.out.println("  3. 게시글 삭제");
		System.out.println("  4. 게시글 검색");
		System.out.println("  5. 전체 게시글 출력");
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
			case 1: // 게시글 작성
				insertNotice();
				break;
			case 2: // 게시글 수정
				updateNotice();
				break;
			case 3: // 게시글 삭제
				deleteNotice();
				break;
			case 4: // 게시글 검색
				searchNotice();
				break;
			case 5: // 전체 게시글 출력
				displayNoticeAll();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void insertNotice() {
		NoticeVO notice = new NoticeVO();
		try {
			scan.nextLine();
			System.out.println("게시글 제목 : ");
			String boardTitle = scan.nextLine();
			notice.setBoardTitle(boardTitle);
			
			System.out.println("게시글 작성자 : ");
			String boardWriter = scan.next();
			scan.nextLine();
			notice.setBoardWriter(boardWriter);
			
			System.out.println("게시글 내용 : ");
			String boardContent  = scan.nextLine();
			notice.setBoardContent(boardContent);
		} catch(InputMismatchException e) { 
			e.printStackTrace();
		}
		
		if(service.insertNotice(notice) == null) {
			System.out.println("새로운 게시글 등록 완료");
		} else {
			System.out.println("새로운 게시글 등록 실패");
		}
	}

	private void updateNotice() {
		Object check = null; // 기존회원 존재여부 체크
		NoticeVO notice = new NoticeVO();
		String boardNo = "";

		do {
			displayNoticeAll();
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >> ");

			boardNo = scan.next();
			scan.nextLine();

			// 존재하면 true반환
			check = getNotice(boardNo);

			if (check == null) {
				System.out.println(boardNo + "번 게시글은 없습니다.");
				System.out.println("다시 입력해주세요.");
			}

		} while (check == null);
		notice.setBoardNo(boardNo);
		
		System.out.println("게시글 제목 : ");
		String boardTitle = scan.nextLine();
		notice.setBoardTitle(boardTitle);
		
		System.out.println("게시글 내용 : ");
		String boardContent  = scan.nextLine();
		notice.setBoardContent(boardContent);
		
		if (service.updateNotice(notice) > 0) {
			System.out.println(boardNo + "번 게시물 수정 완료");
		} else {
			System.out.println(boardNo + "번 게시물 수정 실패");
		}
	}

	/**
	 * 매개변수로 받은 게시글번호와 일치하는 게시글이 DB에 존재하는지 확인하는 메서드
	 * @param board_no (검색의 대상이되는 게시글의 번호)
	 * @return 존재 : true, 존재하지않음 : false
	 */
	private Object getNotice(String boardNo) {
		return service.getNotice(boardNo);
	}

	private void deleteNotice() {
		displayNoticeAll();
		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요.");
		System.out.print("게시글 번호 >> ");

		String boardNo = scan.next();
		scan.nextLine();
		
		if(service.deletcNotice(boardNo) > 0) {
			System.out.println("게시글 삭제 완료");
		} else {
			System.out.println("게시글 삭제 실패");
		}
	}

	/**
	 * 사용자가 원하는 조건에 맞게 기존의 게시글을 검색하는 메서드
	 */
	private void searchNotice() {
		scan.nextLine();
		NoticeVO notice = new NoticeVO();

		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		
		System.out.println("게시글 번호 : ");
		String boardNo = scan.nextLine().trim();
		notice.setBoardNo(boardNo);
		
		System.out.println("게시글 제목 : ");
		String boardTitle = scan.nextLine().trim();
		notice.setBoardTitle(boardTitle);
		
		System.out.println("게시글 작성자 : ");
		String boardWriter = scan.nextLine().trim();
		notice.setBoardWriter(boardWriter);
		
		System.out.println("게시글 작성날짜(ex. 20201006) : ");
		String boardDate  = scan.nextLine().trim();
		notice.setBoardDate(boardDate);
		
		System.out.println("게시글 내용(일부 입력가능) : ");
		String boardContent  = scan.nextLine().trim();
		notice.setBoardContent(boardContent);
		
		List<NoticeVO> noticeList = service.searchNotice(notice);
		
		if(valueIsEmpty(notice)) {
			System.out.println("입력한 정보가 없어서 검색할 수 없습니다.");
			return;
		}
		
		if(noticeList.isEmpty()) {
			System.out.println("검색결과가 존재하지 않습니다.");
			return;
		}
		
		print(noticeList);
	}

	/**
	 * DB에 저장되어 있는 모든 게시글 정보를 List로 가져오는 메서드
	 */
	private void displayNoticeAll() {
		List<NoticeVO> noticeList = service.displayNoticeAll();
		
		if(noticeList.isEmpty()) {
			System.out.println("저장되어 있는 게시글이 존재하지 않습니다.");
			return;
		}
		
		print(noticeList);
	}
	
	/**
	 * 매개변수로 받은 List를 format에 맞게 출력하는 메서드
	 * @param noticeList
	 */
	private void print(List<NoticeVO> noticeList) {
		
		// 게시글 번호를 정렬하기 위한 내부 클래스
		// 출력할때만 사용하기 때문에 내부클래스로 정의한다.
		// 이 외 방법으로는 NoticeVO클래스에 Comparable 인터페이스를 implements하는 방법이 있다.
		class sortClass implements Comparator<NoticeVO> {
			@Override
			public int compare(NoticeVO notice1, NoticeVO notice2) {
				return Integer.parseInt(notice1.getBoardNo()) - Integer.parseInt(notice2.getBoardNo());
			}
		}
		
		Collections.sort(noticeList, new sortClass());

		System.out.println();
		for(NoticeVO no : noticeList) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("게시글 번호 : " + no.getBoardNo()
							+"\n게시글 제목 : " + no.getBoardTitle()
							+"\n 작  성  자 : " + no.getBoardWriter()
							+"\n작 성 날 짜 : " + no.getBoardDate()
							+"\n게시글 내용 : " + no.getBoardContent());
			System.out.println("----------------------------------------------------------------");
			System.out.println();
		}
	}
	
	/**
	 * 검색기능에서 사용자가 입력한 정보가 존재하는지 확인하는 메서드
	 * @param notice
	 * @return
	 */
	private boolean valueIsEmpty(NoticeVO notice) {
		if(notice.getBoardContent().equals("")
				&& notice.getBoardDate().equals("")
				&& notice.getBoardNo().equals("")
				&& notice.getBoardTitle().equals("")
				&& notice.getBoardWriter().equals("")) {
			return true;
		}
		return false;
	}
}
