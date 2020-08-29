package package_view;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import package_VO.CinemaVO;
import package_VO.CommentVO;
import package_VO.FreeBoardVO;
import package_VO.MemberVO;
import package_VO.MovieVO;
import package_VO.ReserveVO;
import package_VO.ScreenVO;
import package_VO.SeatVO;
import package_VO.ShowVO;
import package_service.IService;
import package_service.IServiceImpl;

public class View {

	// 서비스를 불러오기 위한 객체 service
	private IService service = new IServiceImpl();
	private Reg reg = new Reg();
	private MemberVO currentMemmer = null;
	
	
	Calendar cal = Calendar.getInstance(); // 캘린더객체 받아오기 위해 캘린더 클래스 사용
	
	/**
	 * 배너 디자인
	 * 
	 * @param str
	 */
	private void showBanner(String str) {
		System.out.println("────────────────────────────────────");
		System.out.println(str);
		System.out.println("────────────────────────────────────");
	}

	/**
	 * <b>메인페이지</b> <br>
	 * 1.회원가입 <br>
	 * 2.로그인 <br>
	 * 3.종료
	 */
	public void mainView() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 종료");
			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}

			switch (input) {
			case 1:
				signUp();
				break;
			case 2:
				sc.nextLine();
				logIn();
				break;
			case 3:
				System.out.println("시스템을 종료합니다.");
				return;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			}
		}

	}

	/**
	 * 메인페이지 - 1.회원가입 메서드
	 * 
	 * <br>
	 * memberVo의 모든 요소 -> 정규식확인 -> iservice인터페이스에서 추상메서드 -> iserviceImpl 메서드 구현
	 * -> Database에서 메서드 만들기
	 * 
	 * 
	 */
	private void signUp() {
		MemberVO mvo = new MemberVO();
		String member_id = inputId(); // 기본키
		String member_pw = inputPw(); //회원비밀번호
		String member_name = inputName(); //회원이름
		String member_phoneNum = inputPh(); //회원핸드폰번호
		Calendar member_birth = inputBirth(); //회원생일(연령제한)
		int member_cash = inputCash(); //보유금액
		
		mvo.setMember_id(member_id);
		mvo.setMember_pw(member_pw);
		mvo.setMember_name(member_name);
		mvo.setMember_phoneNum(member_phoneNum);
		mvo.setMember_birth(member_birth);
		mvo.setMember_cash(member_cash);
		
		service.signUp(mvo);
	}

	/**
	 * 아이디 받는 메서드
	 * 
	 * @return String id
	 */
	private String inputId() {
		Scanner sc = new Scanner(System.in);
		String mem_id = null;
		while (true) {
			System.out.println("아이디를 입력해주세요 (대소문자영문자 조합 5~12자리)");
			mem_id = sc.next();
			// 정규식체크
			boolean result = reg.id(mem_id);
			if (!result) {
				System.out.println("아이디는 대소문자영문자 조합 5~12자리 이어야 합니다.");
			} else if (!service.duplicate_id(mem_id)) {
				System.out.println("아이디가 중복됩니다. 다시 입력해주세요.");
			} else {
				break;
			}
		}
		return mem_id;
	}

	/**
	 * 패스워드를 받는 메서드
	 * 
	 * @return String pw
	 */
	private String inputPw() {
		Scanner sc = new Scanner(System.in);
		String mem_pw = null;
		while (true) {
			System.out
					.println("비밀번호를 입력해주세요(비밀번호는 영문, 숫자, 특수문자 조합 4자리 이상 11자리 이하이어야 합니다.)");
			mem_pw = sc.next();
			// 정규식체크
			boolean result = reg.password(mem_pw);
			if (!result) {
				System.out.println("비밀번호는 영문, 숫자, 특수문자 조합 4자리 이상 11자리 이하이어야 합니다.");
			} else {
				break;
			}
		}
		return mem_pw;
	}
	
	
	/**
	 * 이름을 반환하는 메서드
	 * 
	 * @return String name
	 */
	private String inputName() {
		Scanner sc = new Scanner(System.in);
		String mem_name = null;
		while (true) {
			System.out.println("이름을 입력해주세요(only 한글)");
			mem_name = sc.next();
			// 정규식체크
			boolean result = reg.name(mem_name);

			if (!result) {
				System.out.println("이름은 한글로만 입력해주세요.");
			} else {
				break;
			}
		}
		return mem_name;
	}

	/**
	 * 핸드폰번호를 반환하는 메서드
	 * 
	 * @return String Phonenumber
	 */
	private String inputPh() {
		Scanner sc = new Scanner(System.in);
		String mem_ph = null;
		while (true) {
			System.out.println("핸드폰번호를 입력해주세요");
			mem_ph = sc.next();
			// 정규식체크
			boolean result = reg.phone(mem_ph);
			if (!result) {
				System.out.println("");
			} else {
				break;
			}
		}
		return mem_ph;
	}

	/**
	 * 생일을 반환하는 메서드
	 * 
	 * @return
	 */
	private Calendar inputBirth() {
		Calendar mem_birth = Calendar.getInstance(); // 날짜 데이터를 출력하기 위한 객체 생성방법
		Scanner sc = new Scanner(System.in);
		String tmp_birth = null; // 생일을 입력
		while (true) {
			System.out.println("생일을 yyyy/MM/dd 의 형태로 입력해주세요.(입력예:2015/12/31)");
			tmp_birth = sc.next();

			boolean result = reg.birth(tmp_birth);
			if (!result) {
				System.out.println("yyyy/MM/dd 의 형태로 입력!!");
			} else {
				break;
			}
		}

		mem_birth.set(Integer.parseInt(tmp_birth.substring(0, 4)),
				Integer.parseInt(tmp_birth.substring(5, 7)) - 1,
				Integer.parseInt(tmp_birth.substring(8)));

		// System.out.println(mem_birth.get(Calendar.YEAR)+"."+(mem_birth.get(Calendar.MONTH)+1)+"."+mem_birth.get(Calendar.DATE));
		return mem_birth;
	}

	/**
	 * 입력한 돈을 반환하는 메서드
	 * 
	 * @return cash
	 */
	private int inputCash() {
		Scanner sc = new Scanner(System.in);
		int mem_cash = 0;
		while (true) {
			System.out.println("돈을 입력해주세요.");
			try {
				mem_cash = sc.nextInt();

			} catch (NumberFormatException e) {
				System.out.println("! 숫자만 입력하세요 !");
				sc.nextLine();
				continue;

			} catch (Exception e) {
				System.out.println("! 숫자만 입력하세요 !");
				sc.nextLine();
				continue;
			}
			break;
		}
		return mem_cash;
	}

	/**
	 * 메인페이지 - 2. 로그인
	 */
	private void logIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력해주세요.");
		String mem_id = sc.next();
		sc.nextLine();
		System.out.println("비밀번호를 입력해주세요.");
		String mem_pw = sc.next();

		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pw", mem_pw);
		
		
		currentMemmer = service.logIn(params);
		if (currentMemmer == null) {
			System.out.println("해당 회원이 존재하지 않거나 비밀번호가 다릅니다.");
		} else {
			System.out.println("*** " + currentMemmer.getMember_name() + "님 환영합니다. ***");
			if (currentMemmer.getMember_auth()) {
				// 관리자 페이지
				adminView();
			} else {
				// 일반회원 페이지
				memberView();
			}
		}
	}

	/**
	 * 관리자모드
	 */
	private void adminView() {

		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 회원관리");
			System.out.println("2. 영화 관리");
			System.out.println("3. 영화관 관리");
			System.out.println("4. 상영관 관리");
			System.out.println("5. 상영 관리");
			System.out.println("6. 좌석 관리");
			System.out.println("7. 예매 관리");
			System.out.println("8. 한줄평 관리");
			System.out.println("9. 자유게시판 관리");
			System.out.println("10.나가기");
			
			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}

			switch (input) {
			case 1: // 1. 회원관리
				memberMng();
				break;
			case 2: // 2. 영화관리
				movieMng();
				break;
			case 3: // 3. 영화관 관리
				cinemaMng();
				break;
			case 4: // 4. 상영관관리
				screenMng();
				break;
			case 5: // 5. 상영관리
				showMng();
				break;
			case 6: // 6. 좌석관리
				seatMng();
				break;
			case 7: // 7. 예매관리
				reserveMng();
				break;
			case 8: // 8. 한줄평 관리
				commentMng();
				break;
			case 9: // 9.자유게시판 관리
				freeboardMng();
				break;
			case 10: // 10. 나가기
				System.out.println("로그아웃 합니다.");
				currentMemmer = null; // 로그아웃상태
				return;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			}
		}

	}

/*/*********************************************************************************************************************************
 * 
 * 윤홍식
 * : 회원관리와 영화관리,한줄평을 맡고 있습니다.
 * 
 * *********************************************************************************************************************************
 */

	/**
	 * 회원관리메서드
	 * @author 윤홍식
	 */
	private void memberMng() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 회원 등록");
			System.out.println("2. 회원 조회");
			System.out.println("3. 회원 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}
			
			switch (input) {
			case 1: // 1. 회원등록
				memberCreate();
				break;
			case 2: // 2. 회원조회
				memberRead();
				break;
			case 3: // 3. 회원수정
				memberUpdate();
				break;
			case 4: // 4. 회원삭제
				memberDelete();
				break;
			case 5: // 5. 나가기
				return;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			}
		}
	}
	
	/**
	 * 회원등록<br>
	 * @author 윤홍식
	 */
	private void memberCreate() {
		signUp();	// 회원가입등록 메서드
		System.out.println("*** 회원가입이 완료되었습니다. ***");
	}
	
	/**
	 * 회원관리<br>
	 * 회원 조회
	 * @author 윤홍식
	 */	
	private void memberRead() { 
		while(true){
			showBanner("\t= 전체 회원 정보 =");
			List<MemberVO> memberList = service.memberRead();
			for(int i=0; i< memberList.size(); i++){
				System.out.print("회원No." + (i+1));
				System.out.print("\t회원ID : " + memberList.get(i).getMember_id());
				System.out.print("\t회원명 : " + memberList.get(i).getMember_name());
				System.out.println("\t보유금액 : " + memberList.get(i).getMember_cash());
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("회원번호를 입력해주세요(상세보기 페이지 이동)");
			System.out.println("나가기 : (0)");
			
			int tmp = 0; // 값을 입력받을 변수 tmp
			//상세보기 과정	
			MemberVO member = null;
			try{
				tmp = sc.nextInt();
				if(tmp==0){
					return;
					}	
				member = memberList.get(tmp-1); //mList의 값을 tmp로 받아서 member객체에 저장
			}catch(IndexOutOfBoundsException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}catch(InputMismatchException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}
			
		    System.out.println("---------------------------------");
			System.out.println("No." + (tmp));
			System.out.println("이름 : " + member.getMember_name());
			System.out.println("회원ID : " + member.getMember_id());
			System.out.println("비밀번호 : " + member.getMember_pw());
			System.out.println("전화번호 : " + member.getMember_phoneNum());		
			System.out.println("---------------------------------");
			return;
			//System.out.println("조회 메서드 실행");
		}
	}
	
	/**
	 * 회원삭제<br>
	 * 회원 삭제
	 * @author 윤홍식
	 */
	private void memberDelete() {
		while(true){
			List<MemberVO> mList = service.memberRead();
			for(int i = 0; i < mList.size(); i++){
				MemberVO member = mList.get(i);
				System.out.println("No. " + (i+1));
				System.out.println("회원의 ID : " + member.getMember_id());
				System.out.println("회원의 이름 : " + member.getMember_name());
				System.out.println("---------------------------------");
			}
			System.out.println("삭제할 회원번호를 입력해주세요");
			System.out.println("==>");
			Scanner sc = new Scanner(System.in);
			int tmp = 0;
			String mem_id = null;
			try{
				tmp = sc.nextInt();
				mem_id = mList.get(tmp-1).getMember_id();
			}catch(IndexOutOfBoundsException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}catch(InputMismatchException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}
			
			boolean d = service.memberDelete(mem_id);
			return;
		}
	}
	/**
	 * 회원수정<br>
	 * 회원 수정
	 * @author 윤홍식
	 */
	private void memberUpdate() {
		//memberRead();
		while(true){
			List<MemberVO> mList = service.memberRead();
			Scanner sc = new Scanner(System.in);
			System.out.println("---------------------------------");
			for(int i = 0; i < mList.size(); i++){
				MemberVO member = mList.get(i);
				System.out.println("No. " + (i+1));
				System.out.println("회원의 ID : " + member.getMember_id());
				System.out.println("회원의 이름 : " + member.getMember_name());
				System.out.println("---------------------------------");
			}
			System.out.println("수정하실 회원번호를 입력해주세요.");
			int tmp = 0; // 값을 입력받을 변수 tmp
			//상세보기 과정	
			MemberVO member = null; //mList의 값을 tmp로 받아서 member객체에 저장
			try{
				tmp = sc.nextInt();
				member = mList.get(tmp-1); //mList의 값을 tmp로 받아서 member객체에 저장
			}catch(IndexOutOfBoundsException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				return;
			}catch(InputMismatchException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				return;
			}
		
				System.out.println("No." + (tmp));
				System.out.println("이름 : " + member.getMember_name());
				System.out.println("회원ID : " + member.getMember_id());
				System.out.println("비밀번호 : " + member.getMember_pw());
				System.out.println("전화번호 : " + member.getMember_phoneNum());			
			
			// 회원정보수정을 (Y/N)입력으로 판단하게함
			System.out.println(member.getMember_name()+" 님의 회원정보를 수정하시겠습니까?(Y/N)");
			String memberfix = sc.next();
			sc.nextLine();
			if(memberfix.equals("y")||memberfix.equals("Y")){
			System.out.println("1. 회원ID");
			System.out.println("2. 비밀번호");
			System.out.println("3. 이름");		
			System.out.println("4. 전화번호");	
			System.out.println("5. 생일");	
			System.out.println("6. 보유금액");	
			System.out.println("수정하고 싶은 항목번호를 입력해주세요.");
			} else {
				return;
			}
			
			// case문에서 수정할 항목들을 따로 걸러서 입력할 수 있게 하는 로직
			int input = 0;
			input = sc.nextInt();
			switch (input) {
			case 1: // 1.회원ID
				System.out.println("수정할 아이디를 입력해주세요.");
				System.out.println("==>");
				member.setMember_id(sc.next());
				break;
			case 2: // 2.회원비밀번호
				System.out.println("수정할 비밀번호를 입력해주세요.");
				System.out.println("==>");
				member.setMember_pw(sc.next());
				break;
			case 3: // 3.회원이름
				System.out.println("수정할 이름을 입력해주세요.");
				System.out.println("==>");
				member.setMember_name(sc.next());
				System.out.println("수정이 완료되었습니다.");
				break;
			case 4: // 4.회원핸드폰번호
				System.out.println("수정할 핸드폰번호를 입력해주세요.");
				System.out.println("==>");
				member.setMember_phoneNum(sc.next());
				System.out.println("수정이 완료되었습니다.");
				break;
			case 5: // 5.회원생일
				System.out.println("수정할 생일을 입력해주세요.");
				System.out.println("==>");
			//	member.setMember_birth(sc.next());
				System.out.println("수정이 완료되었습니다.");
				break;
			case 6: // 6.회원보유금액
				System.out.println("수정할 금액을 입력해주세요.");
				System.out.println(member.getMember_cash());
				System.out.println("==>");
				member.setMember_cash(sc.nextInt());
				System.out.println("수정이 완료되었습니다.");
				break;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력해주세요 !");
				return;
			}
			return;
		}
			
		}
	
		
//정석
		
//		String memId = mList.get(tmp).getMember_id();
//		Map<String, Object> param = new HashMap<String, Object>();
//		
//		param.put("member_id", memId);
//		System.out.print("바꿀 이름 : ");
//		String member_name = sc.nextLine();
//		param.put("member_name", member_name);
		
		
		
		//sql
		//void updateMember(Map<String, Object>() param);
		
		
	private void movieMng() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 영화 등록");
			System.out.println("2. 영화 조회");
			System.out.println("3. 영화 수정");
			System.out.println("4. 영화 삭제");
			System.out.println("5. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}
			
			switch (input) {
			case 1: // 1. 영화등록
				movieCreate();
				break;
			case 2: // 2. 영화조회
				movieRead();
				break;
			case 3: // 3. 영화수정
				movieUpdate();
				break;
			case 4: // 4. 영화삭제
				movieDelete();
				break;
			case 5: // 5. 나가기
				return;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			}
		}
	
	}
	/**
	 * 영화를 등록하는 메서드
	 * @author 윤홍식
	 */
	private void movieCreate(){
		showBanner("\t영화 등록");
		HashMap<String, Object> map = new HashMap<String, Object>();
		Scanner sc = new Scanner(System.in);
		System.out.println("영화 제목을 입력해 주세요");
		String movie=sc.next(); //영화 입력
		
		System.out.println("관람등급을 입력해 주세요.");
		int agegrade = 0;
		try{
			agegrade=sc.nextInt(); //등급 입력
			sc.nextLine();
		}catch(InputMismatchException e){
			System.out.println("! 숫자만 입력하세요 !");
			System.out.println("등록하는데에 실패했습니다");
			return;
		}
		
		System.out.println("러닝타임을 입력해 주세요");
		int runT = 0;
		try{
			runT = sc.nextInt();//러닝타임 입력
			sc.nextLine();
		}catch(InputMismatchException e){
			System.out.println("! 숫자만 입력하세요 !");
			System.out.println("등록하는데에 실패했습니다");
			return;
		}
		
		System.out.println("장르를 입력해 주세요");
		String genre = sc.next(); //장르입력
		sc.nextLine();
		
		map.put("movie", movie);	// key, value 
		map.put("agegrade", agegrade);
		map.put("runT", runT);
		map.put("genre", genre);
		
		service.movieCreate(map);
		
		
	}
	
	/**
	    * 영화 조회 메서드
	    * @author 윤홍식
	    */
	private void movieRead() {
		while(true){
		      showBanner("\t영화 조회");
		      List<MovieVO> movieList = service.movieRead();
		      for (int i = 0; i < movieList.size(); i++) {
		         System.out.print("영화No." + (i + 1));
		         System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
		         System.out.println("\t     러닝 타임 : " + movieList.get(i).getMovie_runT()+"분");
		      }
		      
		      Scanner sc = new Scanner(System.in);
		      System.out.println("영화번호를 입력해주세요(상세보기 페이지 이동)");
		      // 조회창 나가기
		      System.out.println("나가기 : (0)");
		      int tmp = 0; // 값을 입력받을 변수 tmp
		      MovieVO member = null; //mList의 값을 tmp로 받아서 member객체에 저장
		      try{
		    	  tmp = sc.nextInt(); // 값을 입력받을 변수 tmp
		    	  if(tmp==0){
					return;
					}	
		    	  member = movieList.get(tmp-1); //mList의 값을 tmp로 받아서 member객체에 저장
		      }catch(NumberFormatException e) {
		    	  System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		    	  continue;
		      }catch(InputMismatchException e){
		    	  System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		    	  continue;
		      }catch(NullPointerException e){
		    	  System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		      }catch(IndexOutOfBoundsException e){
		    	  System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		      }
				
				//상세보기 과정	
		      System.out.println("No." + (tmp));
		      System.out.println("영화 제목 : " + member.getMovie_name());
		      System.out.println("관람 등급 : " + member.getMovie_ageGrade()+"세 관람가");
		      System.out.println("러닝 타임 : " + member.getMovie_runT()+"분");
		      System.out.println("장르 : " + member.getGenre_name());
				//System.out.println("조회 메서드 실행");
		      return;
		}
	}	
	   /**
	    * 영화수정메서드
	    * @author 윤홍식
	    */
	private void movieUpdate(){
		showBanner("\t영화 수정");
		List<MovieVO> mList = service.movieRead();
		for (int i = 0; i < mList.size(); i++) {
	         System.out.print("영화No." + (i + 1));
	         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
	         System.out.println("\t러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
	         
	      }
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정하실 영화번호를 입력해주세요.");
		int tmp = sc.nextInt(); // 값을 입력받을 변수 tmp
		//상세보기 과정	
		MovieVO movie = mList.get(tmp-1); //mList의 값을 tmp로 받아서 member객체에 저장
		
		System.out.println("No." + (tmp));
		System.out.println("영화제목 : " + movie.getMovie_name());
		System.out.println("러닝타임 : " + movie.getMovie_runT() + "분");
		System.out.println("관람 등급 : " + movie.getMovie_ageGrade()+"세관람가");
		System.out.println("장르 : " + movie.getGenre_name());
			// 회원정보수정을 (Y/N)입력으로 판단하게함
		System.out.println(movie.getMovie_name()+"를 수정하시겠습니까?(Y/N)");
		String memberfix = sc.next();
		sc.nextLine();
		if(memberfix.equals("y")||memberfix.equals("Y")){
		System.out.println("1. 영화제목");
		System.out.println("2. 연령제한");
		System.out.println("3. 러닝타임");		
		System.out.println("4. 장르");	
		System.out.println("수정하고 싶은 항목번호를 입력해주세요.");
		} else {
			return;
		}
		
		// case문에서 수정할 항목들을 따로 걸러서 입력할 수 있게 하는 로직
		int input = 0;
		input = sc.nextInt();
		switch (input) {
		case 1: // 1.영화 제목
			System.out.println("수정할 영화제목을 입력해주세요.");
			System.out.println("==>");
			movie.setMovie_name(sc.next());
			break;
		case 2: // 2.영화 관람등급
			System.out.println("수정할 관람등급을 입력해주세요.");
			System.out.println("==>");
			movie.setMovie_ageGrade(sc.nextInt());
			break;
		case 3: // 3.영화 러닝타임
			System.out.println("수정할 러닝타임을 입력해주세요.");
			System.out.println("==>");
			movie.setMovie_runT(sc.nextInt());
			break;
		case 4: // 4.영화 장르
			System.out.println("수정할 장르를 입력해주세요.");
			System.out.println("==>");
			movie.setGenre_name(sc.next());
			break;
		default:
			System.out.println("숫자만 입력해 주세요");
		}
	}
	/**
	 * 영화를 삭제시키는 메서드
	 * @author 윤홍식
	 */
	private void movieDelete(){
		while(true){
			showBanner("\t영화 삭제");
			List<MovieVO> mList = service.movieRead();
			for (int i = 0; i < mList.size(); i++) {
		         System.out.print("영화No." + (i + 1));
		         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
		         System.out.print("\t관람 등급 : " + mList.get(i).getMovie_ageGrade()+"세관람가");
		         System.out.print("\t러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
		         System.out.println("\t장르 : " + mList.get(i).getGenre_name());
		      }
			System.out.println("삭제할 영화번호를 입력해주세요");
			System.out.println("==>");
			Scanner sc = new Scanner(System.in);
			int tmp = 0;
			int movie_num = 0;
			try{
				tmp = sc.nextInt();
				movie_num = mList.get(tmp-1).getMovie_num();
			}catch(IndexOutOfBoundsException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}catch(InputMismatchException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}
			
			boolean d = service.movieDelete(movie_num);
			return;
		}
	}
	
	/**
	 * 한줄평 관리 메서드
	 * @author 윤홍식
	 */
	private void commentMng() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 한줄평 등록");
			System.out.println("2. 한줄평 조회");
			//System.out.println("3. 한줄평 수정");
			System.out.println("3. 한줄평 삭제");
			System.out.println("4. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("숫자만 입력하세요");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				continue;
			}
			
			switch (input) {
			case 1: // 1. 한줄평등록
				commentCreate();
				break;
			case 2: // 2. 한줄평조회
				commentRead();
				break;
			case 3: // 3. 한줄평삭제
				commentDelete();
				break;
			case 4: // 4. 나가기
				return;
			default:
				System.out.println("숫자만 입력해 주세요");
			}
		}
	}

	/**
	* 한줄평 등록
	* @author 윤홍식
	*/
	private void commentCreate() {
		showBanner("한줄평 관리 - 영화선택");
		List<MovieVO> mList = service.movieRead();//영화 가져오기
		for (int i = 0; i < mList.size(); i++) {
	         System.out.print("영화No." + (i + 1));
	         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
	         System.out.println("\t     러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
	      }
		Scanner sc = new Scanner(System.in);
		HashMap<String, Object> map = new HashMap<String, Object>(); // 등록을 위한 해쉬맵
		
		System.out.println("한줄평을 남기실 영화를 선택해주세요.");
		int tmp = sc.nextInt();
		int movie_num;
		movie_num = mList.get(tmp-1).getMovie_num(); 
		
		if(tmp-1==movie_num){	//입력한 tmp값과 movie_num(영화번호)가 같으면 그영화에 대한 한줄평
		
		System.out.println(mList.get(tmp-1).getMovie_name() + "에 대한 한줄평을 입력해주세요"); //입력한 숫자에서 -1을 하면 movie인덱스에서 이름추출이 가능함
		String content=sc.next(); //한줄평 입력
		sc.nextLine();
		
		System.out.println("평점을 입력해주세요");
		int grade=sc.nextInt(); //평점 입력 
		
		map.put("content", content);	// key, value 
		map.put("grade", grade);
		map.put("movie_num", movie_num);
		
		service.commentCreate(map);
		
		}
	}
	
	
	/**
	 * 한줄평 조회
	 * @author 윤홍식
	 */
	private void commentRead() {
		showBanner("한줄평 관리 - 영화선택");
		List<MovieVO> mList = service.movieRead();
		//movieRead();
		  for (int i = 0; i < mList.size(); i++) {
		         System.out.print("영화No." + (i + 1));
		         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
		         System.out.println("\t     러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
		      }
		
		
		System.out.println("한줄평을  조회할 영화를 선택해주세요.");
		System.out.println("==>");
		Scanner sc = new Scanner(System.in);
		int tmp = sc.nextInt(); // 값을 입력받을 변수 tmp
		int movie_num;
		movie_num = mList.get(tmp - 1).getMovie_num(); // 입력받은 tmp에 -1을 한값으로
														// Movie_num의 숫자를 불러옴

		List<CommentVO> commentList = service.commentReadSelect(movie_num); // 한줄평에 대한 정보가  담겨있는 cList를불러와 commentList에담는다
		for (int i = 0; i < commentList.size(); i++) {
			System.out.print("한줄평No." + (i + 1));
			System.out.print("\t 한줄평 : " + commentList.get(i).getComt_content());
			System.out.print("\t   평점 : " + commentList.get(i).getComt_grade());
			SimpleDateFormat date1 = new SimpleDateFormat("\t\t 날짜 : "
					+ "yyy:MM:dd-hh:mm"); // 날짜 형식 지정
			System.out.println(date1.format(cal.getTime()));

		}
	}
	/**
	 * 한줄평 수정 메서드
	 * @author 윤홍
	 *//*
	private void commentUpdate() {
		showBanner("\t한줄평수정");
		List<MovieVO> mList = service.movieRead();
		for (int i = 0; i < mList.size(); i++) {
	         System.out.print("영화No." + (i + 1));
	         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
	         System.out.println("\t러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
	         
	      }
		List<CommentVO> cList = service.commentRead();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정하실 번호를 입력해주세요.");
		String commentfix = sc.next();
		sc.nextLine();
		if(commentfix.equals("y")||commentfix.equals("Y")){
		System.out.println("1. 한줄평");
		System.out.println("2. 평점");
		System.out.println("수정하고 싶은 항목번호를 입력해주세요.");
		} else {
			return;
		}
		
	}*/
	
	/**
	 * 한줄평 삭제
	 * @author 윤홍식
	 */
	private void commentDelete() {
		showBanner("\t영화 조회");
		List<MovieVO> movieList = service.movieRead(); // DB에서 조회 추가해서 movieList에 넣어줌
		for (int i = 0; i < movieList.size(); i++) {
			System.out.print("영화No." + (i + 1));
			System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
			System.out.print("\t관람 등급 : "
					+ movieList.get(i).getMovie_ageGrade() + "세관람가");
			System.out.print("\t     러닝 타임 : " + movieList.get(i).getMovie_runT()
					+ "분");
			System.out.println("\t장르 : " + movieList.get(i).getGenre_name());
		} // for문돌려서 영화전체 조회 다받음

		Scanner sc = new Scanner(System.in);
		System.out.println("한줄평을 삭제하고 싶은 영화번호를 입력해주세요");
		int tmp = sc.nextInt(); // 값을 입력받을 변수 tmp

		int movie_num = movieList.get(tmp - 1).getMovie_num();
		List<CommentVO> commentList = service.commentSelectForMovie(movie_num);
		for (int i = 0; i < commentList.size(); i++) {
			System.out.println("한줄평No." + (i + 1) + "   "
					+ commentList.get(i).getComt_content());
		} // for문돌려서 한줄평전체 조회 다받음
		// 선택한 번호에 해당하는 한줄평 출력

		System.out.println("삭제할 한줄평 번호를 입력해주세요.");
		int tmp1 = sc.nextInt();

		int comment_num;
		comment_num = commentList.get(tmp1 - 1).getComt_num();
		boolean d = service.commentDelete(comment_num);
	}
	
	/*/*********************************************************************************************************************************
	 * 
	 * 정소미
	 * : 영화관 관리, 상영관 관리, 상영 관리를 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	/**
	 * 영화관 관리 메서드
	 * @author 정소미
	 */
	private void cinemaMng() {
		while (true) {
			showBanner("\t영화관 관리");
			System.out.println("1. 영화관 등록");
			System.out.println("2. 영화관 조회");
			System.out.println("3. 영화관 수정");
			System.out.println("4. 영화관 삭제");
			System.out.println("5. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
				sc.nextLine();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}

			switch (input) {
			case 1: // 1. 영화관 등록
				cinemaCreate();
				break;
			case 2: // 2. 영화관 조회
				cinemaRead();
				break;
			case 3: // 3. 영화관 수정
				cinemaUpdate();
				break;
			case 4: // 4. 영화관 삭제
				cinemaDelete();
				break;
			case 5: // 5. 나가기
				return;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			}
		}
	}
	
	/**
	 * 영화관 등록 메서드
	 * @author 정소미
	 */
	private void cinemaCreate() {
		showBanner("\t영화관 등록");
		Scanner sc = new Scanner(System.in);
		System.out.print("지역을 입력해주세요 : ");
		String area = sc.next();
		sc.nextLine();
		System.out.print("지점명을 입력해주세요 : ");
		String name = sc.next();
		sc.nextLine();
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("area", area);
	    params.put("name", name);
	    
	    boolean result;
	    result = service.cinemaCreate(params);
	    
	    if(result == true){
	    	System.out.println(params.get("name") + "이 성공적으로 추가되었습니다.");
	    }else{
	    	System.out.println("등록하는데에 실패했습니다.");
	    }
	}
	
	/**
	 * 영화관 조회 메서드
	 * @author 정소미
	 */
	private void cinemaRead() {
		showBanner("\t영화관 조회");
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
	}
	
	/**
	 * 영화관 수정 메서드
	 * @author 정소미
	 */
	private void cinemaUpdate() {
		showBanner("\t영화관 수정");
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 영화관의 번호를 선택해주세요 : ");
		int cinema_num = 0;
//		CinemaVO cvo = new CinemaVO();
		try{
			cinema_num = sc.nextInt();
			sc.nextLine();
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			return;
		}catch(InputMismatchException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			return;
		}
		try{
			if(cinemaList.get(cinema_num-1).getCinema_num() != cinema_num-1){
				return;
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("해당 영화관이 존재하지 않습니다.");
			return;
		}
		if(cinemaList == null){
			return;
		}
		//수정할 id
		//수정할 지역
		//수정할 지점명
//		변수 3개를 HashMap<String, Object> -> 형변환 꼭필요
//		HashMap의 참조변수를 데이터베이스 인자값으로 보냄
//		데이터베이스에서 수정할 id를 비교해서 리스트 항목 중 하나 추출해서 그에 대한 지역과 지점명 변경(set)
//		성공했으면 true 실패면 false
		System.out.print("새로운 지역을 입력해주세요 : ");
		String cinema_area = sc.nextLine();
		System.out.print("새로운 지점명을 입력해주세요 : ");
		String cinema_name = sc.nextLine();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cinema_num", cinema_num-1);
		params.put("cinema_area", cinema_area);
		params.put("cinema_name", cinema_name);
		boolean result = service.cinemaUpdate(params);
		if(result == true){
	    	System.out.println("성공적으로 수정되었습니다.");
	    }else{
	    	System.out.println("수정하는데에 실패했습니다.");
	    	System.out.println("다시 선택해주세요");
	    }
	}
	
	/**
	 * 영화관 삭제 메서드
	 * @author 정소미
	 */
	private void cinemaDelete() {
		showBanner("\t영화관 삭제");
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 영화관의 번호를 선택해주세요 : ");
		int cinema_num = 0;
		try{
			cinema_num = sc.nextInt();
			sc.nextLine();
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}catch(InputMismatchException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}
		
		if(cinemaList == null){
			return;
		}
		try{
			if(service.cinemaDelete(cinemaList.get(cinema_num-1).getCinema_num())){
				System.out.println(cinema_num + "번 영화관을 성공적으로 삭제했습니다.");	
			}else{
				System.out.println("삭제에 실패했습니다.");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("다시 선택해주세요.");
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			System.out.println("다시 선택해주세요.");
		}
		
	}

	/**
	 * 상영관 관리 메서드
	 * @author 정소미
	 */
	private void screenMng() {
		while (true) {
			showBanner("\t상영관 관리");
			System.out.println("1. 상영관 등록");
			System.out.println("2. 상영관 조회");
			System.out.println("3. 상영관 삭제");
			System.out.println("4. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
				sc.nextLine();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}

			switch (input) {
			case 1: // 1. 상영관 등록
				screenCreate();
				break;
			case 2: // 2. 상영관 조회
				screenRead();
				break;
			case 3: // 3. 상영관 삭제
				screenDelete();
				break;
			case 4: // 4. 나가기
				return;
			default:
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			}
		}
	}

	/**
	 * 상영관 등록 메서드
	 * @author 정소미
	 */
	private void screenCreate() {
		showBanner("\t상영관 등록");
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.print("상영관을 등록하고 싶은 영화관 번호를 선택해주세요 : ");
		int cinema_num = 0;
		try{
			cinema_num = sc.nextInt();
			sc.nextLine();
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}catch(InputMismatchException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}
		try{
			if(cinemaList.get(cinema_num-1).getCinema_num() != cinema_num-1){
				return;
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("해당 영화관이 존재하지 않습니다.");
			return;
		}
//		int tmp = cinemaList.get(cinema_num-1).getCinema_num();
		System.out.print("상영관 이름을 입력해주세요 : ");
		String screen_name = sc.nextLine();
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("cinema_num", cinema_num);
	    params.put("screen_name", screen_name);
	    
	    boolean result = service.screenCreate(params);
		if(result == true){
			System.out.println("상영관이 성공적으로 추가되었습니다.");
		}else{
			System.out.println("추가하는데 실패했습니다.");
		}
	}

	/**
	 * 상영관 조회 메서드
	 * @author 정소미
	 */
	private void screenRead() {
		showBanner("\t상영관 조회");
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("조회하고싶은 영화관 번호를 선택하세요 : ");
		int cinema_num = 0;
		try{
		cinema_num = sc.nextInt();
		sc.nextLine();
		}catch(InputMismatchException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}
		try{
			if(cinemaList.get(cinema_num-1).getCinema_num() != cinema_num-1){
				return;
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("해당 영화관이 존재하지 않습니다.");
			return;
		}

		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
			System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
	}

	/**
	 * 상영관 삭제 메서드
	 * @author 정소미
	 */
	private void screenDelete() {
		showBanner("\t상영관 삭제");
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("상영관을 삭제하고싶은 영화관 번호를 선택하세요 : ");
		int cinema_num = 0;
		try{
			cinema_num = sc.nextInt();
			sc.nextLine();
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			System.out.println("다시 선택해주세요.");
		}catch(InputMismatchException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			System.out.println("다시 선택해주세요.");
		}catch(Exception e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
			System.out.println("다시 선택해주세요.");
		}
		try{
			if(cinemaList.get(cinema_num-1).getCinema_num() != cinema_num-1){
				return;
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("해당 영화관이 존재하지 않습니다.");
			return;
		}
		
		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
			System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		
		System.out.print("삭제할 상영관 번호를 선택하세요 : ");
		int screen_num = 0;
		
		try{
			screen_num = sc.nextInt();
			sc.nextLine();
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}catch(InputMismatchException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}
		
		try{
		    if(service.screenDelete(screenList.get(screen_num-1).getScreen_num())){
				System.out.println("상영관을 성공적으로 삭제했습니다.");	
			}else{
				System.out.println("삭제에 실패했습니다.");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("다시 선택해주세요.");
		}catch(IndexOutOfBoundsException e){
			System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
		}
	}
	   /**
	    * 상영 관리 메서드
	    * @author 정소미
	    */
	   private void showMng() {
	      while (true) {
	         showBanner("\t상영 관리");
	         System.out.println("1. 상영 등록");
	         System.out.println("2. 상영 조회");
	         System.out.println("3. 상영 삭제");
	         System.out.println("4. 나가기");

	         Scanner sc = new Scanner(System.in);
	         int input = 0;
	         try {
	            input = sc.nextInt();
	            sc.nextLine();
	         } catch (NumberFormatException e) {
	            // e.printStackTrace();
	            System.out.println("숫자만 입력하세요");
	            continue;
	         } catch (InputMismatchException e) {
	            System.out.println("숫자만 입력하세요");
	            continue;
	         }

	         switch (input) {
	         case 1: // 1. 상영 등록
	            showCreate();
	            break;
	         case 2: // 2. 상영 조회
	            showRead();
	            break;
	         case 3: // 3. 상영 삭제
	            showDelete();
	            break;
	         case 4: // 5. 나가기
	            return;
	         default:
	            System.out.println("숫자만 입력해 주세요");
	         }
	      }
	   }
	   
	   /**
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 상영 등록
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    * 
	    */
	   private void showCreate() {
	      showBanner("\t상영등록");
	      List<MovieVO> movieList = service.movieRead();
	         for (int i = 0; i < movieList.size(); i++) {
	            System.out.print("영화No." + (i + 1));
	            System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
	            System.out.print("\t관람 등급 : " + movieList.get(i).getMovie_ageGrade()+"세관람가");
	            System.out.print("\t러닝 타임 : " + movieList.get(i).getMovie_runT()+"분");
	            System.out.println("\t장르 : " + movieList.get(i).getGenre_name());
	         }
	         
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         Scanner sc = new Scanner(System.in);
	         System.out.print("상영을 등록하고 싶은 영화 번호를 선택해주세요 : ");
	         int movie_num = sc.nextInt();
	         sc.nextLine();
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         
	         List<CinemaVO> cinemaList = service.cinemaRead();
	         for (int i = 0; i < cinemaList.size(); i++) {
	            System.out.print("No." + (i + 1));
	            System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
	            System.out.println   ("\t지점명 : " + cinemaList.get(i).getCinema_name());
	         }
	         
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         System.out.print("상영을 등록하고 싶은 영화관 번호를 선택해주세요 : ");
	         int cinema_num = sc.nextInt();
	         sc.nextLine();
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         
	         List<ScreenVO> screenList = service.screenRead(cinema_num-1);
	         System.out.println("───────────");
	         System.out.println("상영관 목록");
	         System.out.println("───────────");
	         for(int i=0; i<screenList.size(); i++){
	            System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
	         }
	         
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         System.out.print("상영을 등록하고 싶은 상영관 이름을 선택해주세요 : ");
	         int screen_num = sc.nextInt();
	         sc.nextLine();
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         
	         List<Calendar> timetable = timetable();
	         for (Calendar calendar : timetable) {
				System.out.println(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
			 }
	         
	         Map<String, Object> params = new HashMap<String, Object>();
	         params.put("movie_num", movie_num-1);
	         params.put("cinema_num", cinema_num-1);
	         params.put("screen_num", screen_num);
	         params.put("timetable", timetable);
	         
	         if(service.showCreate(params)){
	        	 System.out.println("등록이 완료 되었습니다.");
	         }else{
	        	 System.out.println("등록 실패!");
	         }
	   }

	   /**
	    * 상영 조회
	    */
	   private void showRead() {
	      showBanner("\t상영조회");
	     
	      ///////////////////////////////////////////////////////////////////////////////////////////////////////
	      List<MovieVO> movieList = service.movieRead();
	         for (int i = 0; i < movieList.size(); i++) {
	            System.out.print("영화No." + (i + 1));
	            System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
	            System.out.print("\t관람 등급 : " + movieList.get(i).getMovie_ageGrade()+"세관람가");
	            System.out.print("\t러닝 타임 : " + movieList.get(i).getMovie_runT()+"분");
	            System.out.println("\t장르 : " + movieList.get(i).getGenre_name());
	         }
	         
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         Scanner sc = new Scanner(System.in);
	         System.out.print("상영을 조회하고 싶은 영화 번호를 선택해주세요 : ");
	         int movie_num = sc.nextInt();
	         sc.nextLine();
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         
	         List<CinemaVO> cinemaList = service.cinemaRead();
	         for (int i = 0; i < cinemaList.size(); i++) {
	            System.out.print("No." + (i + 1));
	            System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
	            System.out.println   ("\t지점명 : " + cinemaList.get(i).getCinema_name());
	         }
	         
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         System.out.print("상영을 조회하고 싶은 영화관 번호를 선택해주세요 : ");
	         int cinema_num = sc.nextInt();
	         sc.nextLine();
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         
	         List<ScreenVO> screenList = service.screenRead(cinema_num-1);
	         System.out.println("───────────");
	         System.out.println("상영관 목록");
	         System.out.println("───────────");
	         for(int i=0; i<screenList.size(); i++){
	            System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
	         }
	         
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	         System.out.print("상영을 조회하고 싶은 상영관 번호를 선택해주세요 : ");
	         int screen_num = sc.nextInt();
	         sc.nextLine();
	         System.out.println("--------------------------------------------------------------------------------------------------------------------");
	       
	         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	      
	         List<List<ShowVO>> readshow = service.showRead(screen_num);
	      
	         System.out.println("'"+cinemaList.get(cinema_num-1).getCinema_name()+"' 의 '" + screenList.get(screen_num-1).getScreen_name() +"'에서 상영하는");
	         System.out.println("'"+movieList.get(movie_num-1).getMovie_name()+"'의 상영시간표는 다음과 같습니다.");
	         
	         //시간표 출력
	        int i = 1;
	        for (List<ShowVO> list : readshow) {
	        	System.out.println(list.get(0).getShow_time().get(Calendar.YEAR)+"년 "+(list.get(0).getShow_time().get(Calendar.MONTH)+1)+"월 "+list.get(0).getShow_time().get(Calendar.DATE )+"일");
	        	for (ShowVO showVO : list) {
	        		if(!showVO.getDeleted()){
	        		System.out.println("No." + i + "\t" + showVO.getShow_time().get(Calendar.HOUR_OF_DAY)+" : " + showVO.getShow_time().get(Calendar.MINUTE));
	        		i++;
	        		}
				}
	        	System.out.println();
	        	i=1;
			}
	         
	        
	        
	        
	   }

	   /**
	    * 상영 삭제
	    */
	   private void showDelete() {
		   
	      showBanner("\t상영삭제");
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		List<MovieVO> movieList = service.movieRead();
		for (int i = 0; i < movieList.size(); i++) {
		System.out.print("영화No." + (i + 1));
		System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
		System.out.print("\t관람 등급 : " + movieList.get(i).getMovie_ageGrade()+"세관람가");
		System.out.print("\t러닝 타임 : " + movieList.get(i).getMovie_runT()+"분");
		System.out.println("\t장르 : " + movieList.get(i).getGenre_name());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);
		System.out.print("상영을 조회하고 싶은 영화 번호를 선택해주세요 : ");
		int movie_num = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
		System.out.print("No." + (i + 1));
		System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
		System.out.println   ("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.print("상영을 조회하고 싶은 영화관 번호를 선택해주세요 : ");
		int cinema_num = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		
		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
		System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.print("상영을 조회하고 싶은 상영관 번호를 선택해주세요 : ");
		int screen_num = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		List<List<ShowVO>> readshow = service.showRead(screen_num);
	      
     System.out.println("'"+cinemaList.get(cinema_num-1).getCinema_name()+"' 의 '" + screenList.get(screen_num-1).getScreen_name() +"'에서 상영하는");
     System.out.println("'"+movieList.get(movie_num-1).getMovie_name()+"'의 상영시간표는 다음과 같습니다.");
      
     //시간표 출력
     int i = 1;
     for (List<ShowVO> list : readshow) {
     	System.out.println("No."+i+" "+list.get(0).getShow_time().get(Calendar.YEAR)+"년 "+(list.get(0).getShow_time().get(Calendar.MONTH)+1)+"월 "+list.get(0).getShow_time().get(Calendar.DATE )+"일");
     	i++;
		}
		
     System.out.println("삭제할 상영시간이 담긴 날짜의 번호를 선택해주세요");
     int deleteNum = sc.nextInt();
     
     
     i=1;
     List<ShowVO> deleteShowList = readshow.get(deleteNum-1);
     for (ShowVO showVO : deleteShowList) {
     	if(!showVO.getDeleted()){
     	System.out.println("No." + i + "\t" + showVO.getShow_time().get(Calendar.HOUR_OF_DAY)+" : " + showVO.getShow_time().get(Calendar.MINUTE));
 		i++;
     	}
		}
     
     deleteNum = -1;
     System.out.println("삭제할 시간을 선택하세요.");
     deleteNum = sc.nextInt();
     
     deleteShowList.get(deleteNum-1).setDeleted();
     
     System.out.println("삭제되었습니다.");
		
	   }

	   
	/**
	 * 시간 입력 메서드
	 * @author 이진영
	 * @return
	 */
	private List<Calendar> timetable(){
		List<Calendar> timetable = new ArrayList<Calendar>();
		
		Scanner sc = new Scanner(System.in);
		int year = 0;
		int month = 0;
		int date = 0;
		Calendar ct = Calendar.getInstance();
		System.out.print("현재 날짜 :");
		System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE));
		
		//1.연도 입력 ( 1.현재보다 이전일 때, 2현재와 같을 때, 2현재보다 미래일 때)
		//2.월 입력 (현재연도와 같으면서 현재월보다 이전일 때, 현재연도보다 미래일 때)
		//3.날짜 입력 (현재연도와 같으면서 현재월보다 이전이면서 해당 월의 최대 일수보다 작거나 같다,  해당월의 최대 일수보다 작거나 같다.)
		System.out.println("연도를 입력해 주세요");
		year = sc.nextInt(); // 연도 입력
		while(year> ct.getActualMaximum(Calendar.YEAR) || year < ct.get(Calendar.YEAR) ){ // 1. 현재연도 보다 과거일 때
			System.out.println("현재 연도보다 이전 연도를 입력할 수 없습니다.");
			System.out.println("다시 입력해주세요");
			year= sc.nextInt();
		}
		
		
		if ( year == ct.get(Calendar.YEAR)){ //현재와 같을 때
			ct.set(year, ct.get(Calendar.MONTH), ct.get(Calendar.DATE));
			
			//월입력
			System.out.println("월을 입력해 주세요 ("+(ct.get(Calendar.MONTH)+1)+"~"+"12)");
			month = sc.nextInt(); // 월 입력
			while(month > ct.getActualMaximum(Calendar.MONTH) || month < ct.get(Calendar.MONTH)){ //현재월보다 과거일 때
				System.out.println("현재 월보다 이전 월을 입력할 수 없습니다.");
				System.out.println("다시 입력해주세요");
				month = sc.nextInt();
			}
			ct.set(ct.get(Calendar.YEAR), month-1, ct.get(Calendar.DATE));
			
			if(month-1 == ct.get(Calendar.MONTH)){
			//날짜입력
			System.out.println("일자를 입력해 주세요 ("+(ct.get(Calendar.DATE)+1)+"~"+(ct.getActualMaximum(Calendar.DATE))+")");
			date = sc.nextInt(); // 일 입력
			while(date > ct.getActualMaximum(Calendar.DATE) || date <= ct.get(Calendar.DATE) ){
				System.out.println("현재 일자이후의 날짜, 해당 월의 최대일자를 넘을 수 없음");
				System.out.println("다시 입력해주세요");
				date = sc.nextInt();
			
			}
			}else{
				System.out.println("일자를 입력해 주세요 (1~"+ct.getActualMaximum(Calendar.DATE)+")");
				date = sc.nextInt(); // 일 입력
				while(date > ct.getActualMaximum(Calendar.DATE) || date < 0 ){
					System.out.println("해당 일자가 존재하지 않습니다.");
					System.out.println("다시 입력해주세요 (1~"+ct.getActualMaximum(Calendar.DATE)+")");
					date = sc.nextInt();
				}
			}
			
			
		}else {//현재보다 미래일 때
			
			
			ct.set(year, 1, 1);
			
			//월입력
			System.out.println("월을 입력해 주세요 (1~12)");
			month = sc.nextInt(); //월 입력
			while(month > ct.getActualMaximum(Calendar.MONTH) || month < 0){ //현재월보다 과거일 때
				System.out.println("해당 월은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요(1~12)");
				month = sc.nextInt();
			}
			
			ct.set(year, month-1, 1);
			
			//날짜입력
			System.out.println("일자를 입력해 주세요 (1~"+ct.getActualMaximum(Calendar.DATE)+")");
			date = sc.nextInt(); // 일 입력
			while(date > ct.getActualMaximum(Calendar.DATE) || date < 0 ){
				System.out.println("해당 일자가 존재하지 않습니다.");
				System.out.println("다시 입력해주세요 (1~"+ct.getActualMaximum(Calendar.DATE)+")");
				date = sc.nextInt();
			}
			
		}
		
		//캘린더 년월일 초기화
		ct.set(year, month-1, date , 0 , 0);
//		System.out.print("변경 날짜 :");
//		System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE) + "/"+ct.get(Calendar.HOUR) +":"+ ct.get(Calendar.MINUTE));
		
		
		
		
		//시간관리 ( 러닝타임+20분을 하루로 나누어 showVO 객체를 생성한다.
		int runtime = 150;
		int Totaltime = runtime + 20;
		int limited_date = ct.get(Calendar.DATE);
		
		Calendar tmptime = Calendar.getInstance();
		tmptime.set(year, month-1, date+1 , 0 , 0);
		tmptime.add(Calendar.MINUTE, -Totaltime);
		int limit_hour = tmptime.get(Calendar.HOUR_OF_DAY);
//		System.out.println(limit_hour);
		
		do {
			ct.add(Calendar.MINUTE, Totaltime);
			Calendar newct = Calendar.getInstance();
			newct.set(ct.get(Calendar.YEAR), ct.get(Calendar.MONTH), ct.get(Calendar.DATE) , ct.get(Calendar.HOUR_OF_DAY) , ct.get(Calendar.MINUTE));
			timetable.add(newct);
//			System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE) + "/"+ct.get(Calendar.HOUR_OF_DAY) +":"+ ct.get(Calendar.MINUTE));
		} while (ct.get(Calendar.HOUR_OF_DAY)<limit_hour);
		
		timetable.remove(timetable.size()-1);
		
		return timetable;
	}
	


	/*/*********************************************************************************************************************************
	 * 
	 * 이진영
	 * : 좌석관리와 예매관리를 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	
	
	/**
	/**
	 * 좌석관리
	 * @author 이진영
	 */
	private void seatMng() {
		while (true) {
			showBanner("\t좌석 관리");
			System.out.println("1. 좌석 등록");
			System.out.println("2. 좌석 조회");
			System.out.println("3. 좌석 수정(삭제된 좌석을 복구)");
			System.out.println("4. 좌석 삭제");
			System.out.println("5.나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("숫자만 입력하세요");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				continue;
			}

			switch (input) {
			case 1: // 1. 좌석 등록
				seatCreate();
				break;
			case 2: // 2. 좌석 조회
				seatRead();
				break;
			case 3: // 3. 좌석 수정
				seatUpdate();
				break;
			case 4: // 4. 좌석 삭제
				seatDelete();
				break;
			case 5: // 5. 나가기
				return;
			default:
				System.out.println("숫자만 입력해 주세요");
			}
		}
		
	}
	

	/**
	 * 좌석관리<br>
	 * 1.좌석 등록
	 * @author 이진영
	 */
	private void seatCreate() {
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("영화관 번호를 선택하세요 : ");
		int cinema_num = sc.nextInt();

		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
			System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		System.out.print("상영관 번호를 선택하세요 : ");
		int screen_num= sc.nextInt();
		screen_num = screenList.get(screen_num-1).getScreen_num();
		
		System.out.println("좌석 행 입력");
		int seat_row = sc.nextInt(); // 좌석 행 입력
		System.out.println("좌석 열 입력");
		int seat_cols = sc.nextInt(); // 좌석 열 입력
		
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("screen_num",screen_num);
		params.put("seat_row",seat_row);
		params.put("seat_cols", seat_cols);
		
		//추가해야될 것 screen_num이랑 일치하는 seat_vo가 있다면 back
		
		if(service.seatCreate(params)){//좌석등록
			System.out.println("좌석이 성공적으로 등록되었습니다.");
		}else{
			System.out.println("좌석등록에 실패하였습니다.");
			return;			
		}
	}
	

	/**
	 * 좌석관리<br>
	 * 2.좌석 조회
	 * @author 이진영
	 */
	private void seatRead() {
		showBanner("좌석조회");
		
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("영화관 번호를 선택하세요 : ");
		int cinema_num = sc.nextInt();

		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
			System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		System.out.print("상영관 번호를 선택하세요 : ");
		int screen_num= sc.nextInt();
		screen_num = screenList.get(screen_num-1).getScreen_num();
		
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("\t\t\t\tSCREEN");
		System.out.println("---------------------------------------------------------------------------------");
		
		List<SeatVO> svo_List = service.seatRead(screen_num);
		
		//좌석리스트가 존재하지 않을 때 이전화면으로 돌아감
		if(svo_List == null){
			System.out.println("상영관의 좌석이 존재하지 않습니다.");
			return;
		}
		
		
		for(int i = 0 ; i <= svo_List.get(svo_List.size()-1).getSeat_cols();i++){
			if(i<10){
				System.out.print(" ");
			}
			System.out.print("  "+(i+1));
		}
		
		System.out.println();
		int newLine = svo_List.get(0).getSeat_row();
		char alph = 'A';
		System.out.print(alph+" ");
		for (SeatVO seatVO : svo_List) {
			
			if(newLine < seatVO.getSeat_row()){
				alph++;
				newLine = seatVO.getSeat_row();
				System.out.println();
				System.out.print(alph+" "); 
			}
			
			
			if(!seatVO.getDeleted()){
				System.out.print(" ■  ");// 삭제되지 않은 좌석
			}else{
				System.out.print(" X  "); //삭제된 좌석
			}
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("---------------------------------------------------------------------------------");
	}
	
	/**
	 * 좌석수정<br>
	 * 3.좌석수정
	 * @author 이진영
	 */
	private void seatUpdate() {
		showBanner("\t좌석수정");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("영화관 번호를 선택하세요 : ");
		int cinema_num = sc.nextInt();

		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
			System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		System.out.print("상영관 번호를 선택하세요 : ");
		int screen_num= sc.nextInt();
		screen_num = screenList.get(screen_num-1).getScreen_num();
		
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("\t\t\t\tSCREEN");
		System.out.println("---------------------------------------------------------------------------------");
		
		List<SeatVO> svo_List = service.seatRead(screen_num);

		//좌석리스트가 존재하지 않을 때 이전화면으로 돌아감
		if(svo_List == null){
			System.out.println("상영관의 좌석이 존재하지 않습니다.");
			return;
		}
		
		for(int i = 0 ; i <= svo_List.get(svo_List.size()-1).getSeat_cols();i++){
			if(i<10){
				System.out.print(" ");
			}
			System.out.print("  "+(i+1));
		}
		
		System.out.println();
		int newLine = svo_List.get(0).getSeat_row();
		char alph = 'A';
		System.out.print(alph+" ");
		for (SeatVO seatVO : svo_List) {
			
			if(newLine < seatVO.getSeat_row()){
				alph++;
				newLine = seatVO.getSeat_row();
				System.out.println();
				System.out.print(alph+" "); 
			}
			
			
			if(!seatVO.getDeleted()){
				System.out.print(" ■  ");// 삭제되지 않은 좌석
			}else{
				System.out.print(" X  "); //삭제된 좌석
			}
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("---------------------------------------------------------------------------------");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
		
		System.out.print("복구할 ");
		/*
		 * 1. 삭제된걸 복구
		 * 
		 */
		int seat_code = inputSeatCode(svo_List.get(svo_List.size()-1).getSeat_cols()+1);
		if(svo_List.get(seat_code).getDeleted()){
			svo_List.get(seat_code).setDeleted();
			System.out.println("좌석상태가 사용가능 상태로 바뀌었습니다.");
			System.out.println("이전 화면으로 돌아갑니다.");
			return;
		}else{
			System.out.println("이미 사용가능한 좌석입니다.!!");
			System.out.println("이전 화면으로 돌아갑니다.");
		}
		
		
		
		
		
	}

	/**
	 * 좌석관리<br>
	 * 4.좌석삭제
	 * @author 이진영
	 */
	private void seatDelete() {
		showBanner("\t좌석삭제");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
			System.out.print("No." + (i + 1));
			System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
			System.out.println	("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("영화관 번호를 선택하세요 : ");
		int cinema_num = sc.nextInt();

		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
			System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		System.out.print("상영관 번호를 선택하세요 : ");
		int screen_num= sc.nextInt();
		screen_num = screenList.get(screen_num-1).getScreen_num();
		
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("\t\t\t\tSCREEN");
		System.out.println("---------------------------------------------------------------------------------");
		
		List<SeatVO> svo_List = service.seatRead(screen_num);

		//좌석리스트가 존재하지 않을 때 이전화면으로 돌아감
		if(svo_List == null){
			System.out.println("상영관의 좌석이 존재하지 않습니다.");
			return;
		}
		
		for(int i = 0 ; i <= svo_List.get(svo_List.size()-1).getSeat_cols();i++){
			if(i<10){
				System.out.print(" ");
			}
			System.out.print("  "+(i+1));
		}
		
		System.out.println();
		int newLine = svo_List.get(0).getSeat_row();
		char alph = 'A';
		System.out.print(alph+" ");
		for (SeatVO seatVO : svo_List) {
			
			if(newLine < seatVO.getSeat_row()){
				alph++;
				newLine = seatVO.getSeat_row();
				System.out.println();
				System.out.print(alph+" "); 
			}
			
			
			if(!seatVO.getDeleted()){
				System.out.print(" ■  ");// 삭제되지 않은 좌석
			}else{
				System.out.print(" X  "); //삭제된 좌석
			}
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("---------------------------------------------------------------------------------");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		System.out.print("삭제할 ");
		//svo_List.get(svo_List.size()-1).getSeat_cols()+1 
		int seat_code = inputSeatCode(svo_List.get(svo_List.size()-1).getSeat_cols()+1);
		if(svo_List.get(seat_code).getDeleted()){
			System.out.println("이미 삭제된 좌석입니다.");
			System.out.println("이전 화면으로 돌아갑니다.");
			return;
		}else{
			svo_List.get(seat_code).setDeleted();
			System.out.println("좌석이 성공적으로 삭제되었습니다.");
		}
		
	}

	/**
	 * 좌석번호를 list의 번호로 바꿔주는 메소드
	 * @author 이진영
	 * @param col 
	 * @return
	 */
	private int inputSeatCode(int col) {
		Scanner sc = new Scanner(System.in);
		String seat_code = null;
		int seat_num = 0;
		while (true) {
			System.out.println("좌석을 입력하세요 ( 예 : A12 )");
			seat_code = sc.next();
			// 정규식체크
			boolean result = reg.seatcode(seat_code);
			if (!result) {
				System.out.println("좌석의 행과 열을 순서대로 다시 입력해 주세요 ( 예 : B23 )");
			} else {
				break;
			}
		}
		
		// 알파벳 A(1)~Z(26) + 숫자 1~n 값을 반환 
		// charAt("A") - 64 => 1
		// charAt("Z") - 64 => 26
		/*
		 * +1
		1~20  (0 + 1~20 )-1    (1-1)*20    (tmp-65)*20 + Integer.parseInt(seat_code.substring(1))-(tmp-64)
		21~40 (20 + 1~20 )-1	(2-1)*20
		41~60 (40 + 1~20 )-1	
		
		
		1~10  (1-1)*10
		11~20 (2-1)*10
		
		 */
		char tmp = seat_code.charAt(0);
		seat_num = (tmp-65)*col + Integer.parseInt(seat_code.substring(1))-1;
		
		
		return seat_num;
	}
	/**
	 * 예매관리
	 * @author 이진영
	 */
	private void reserveMng() {
		
		while (true) {
			showBanner("예매관리");
			System.out.println("1. 예매 조회");
			System.out.println("5.나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("숫자만 입력하세요");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				continue;
			}

			switch (input) {
			case 1: // 1.예매 조회
				reserveRead();
				break;
			case 5: // 5. 나가기
				return;
			default:
				System.out.println("숫자만 입력해 주세요");
			}
		}
		
		
		
		
		
		
		
	}


	/**
	 * 예매조회 메서드
	 * @author 이진영
	 */
	private void reserveRead() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("member_auth",currentMemmer.getMember_auth());
		params.put("member_id", currentMemmer.getMember_id());
		
		List<ReserveVO> reList = service.memberReserveRead(params);
		if(reList==null) {
			System.out.println("조회할 예매내역이 없습니다.");
			return;
		}
		
		int i = 1;
		for (ReserveVO reserveVO : reList) {
			//상영번호를 통해 영화 영화관 상영관를 불러와야된다.
			//좌석번호를 통해 좌석을 불러와야 된다.
			System.out.println(reserveVO.getShow_num());
			Map<String, String> element = service.getReserveElement(reserveVO.getShow_num());
			System.out.println();
			System.out.println(i+"번째 예매");
			System.out.println("────────────────────────────────────");
			System.out.println("\t          영화예매표");
			System.out.println("────────────────────────────────────");
			System.out.println("["+element.get("movie_name")+"]");
			System.out.println(element.get("screen_name"));
			System.out.println(element.get("ymd"));
			//시작시간~끝나는시간
			System.out.println(element.get("show_time"));
			System.out.println();
			//좌석번호
			System.out.print("좌석 : ");
//			String CR = service.getColRow(reserveVO.getSeat_num()); //좌석번호 받기
//			System.out.println(CR);
			System.out.println("XX");
			System.out.println("────────────────────────────────────");
			System.out.println(element.get("cinema_name"));
			System.out.println("====================================");
			System.out.println("[주차안내]");
			System.out.println(" 당일 영화티켓 소지 시 3시간 무료주차");
			System.out.println(" 3시간 이후 30분당 1000원");
			System.out.println("====================================");
			System.out.println();
			System.out.println("∥∥∥∥∥∥∥  ∥∥ ∥∥∥∥∥∥ ∥∥∥∥∥∥∥ ∥∥  ");
			System.out.println("∥∥∥∥∥∥∥  ∥∥ ∥∥∥∥∥∥ ∥∥∥∥∥∥∥ ∥∥");
			System.out.println();
			System.out.println("────────────────────────────────────");
			System.out.println();
		}
		
	}

	/*/*********************************************************************************************************************************
	 * 
	 * 황효진
	 * : 자유게시판을 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	
	/**
	 * 자유게시판 관리
	 * @author 황효진
	 */
	private void freeboardMng() {
		while (true){
			showBanner("자유게시판 관리");
			System.out.println("1.게시글 조회");
			System.out.println("2.게시글 등록");
			System.out.println("3.게시글 수정");
			System.out.println("4.게시글 삭제");
			System.out.println("5.나가기");
			Scanner scan=new Scanner(System.in);
			int input=scan.nextInt();
			switch(input){
			case 1://1.게시글 조회
				postRead();
				break;
			case 2://2.게시글 등록
				postCreate();
				break;
			case 3://3.게시글 수정
				postUpdate();
				break;
			case 4://4.게시글 삭제
				postDelete();
				break;
			case 5://5.나가기
				return;
			}
		}
	}
	/**
	 * 게시글 삭제
	 * 
	 * 
	 * @author 황효진
	 */
	private void postDelete() {
		int post_id;
		showBanner("게시글 삭제");
		post_id=selectpost();
		if(post_id==-1){
			return;
		}
	}
	private void m_postDelete() {
		int member_id;
		showBanner("게시글 삭제");
		member_id=selectpost();
		if(member_id==-1){
			return;
		}
		if(currentMemmer.getMember_auth()){
			postDelete();//관리자
		}else{
			m_postDelete();//회원
		}
		
	}

	
	private int selectpost() {
		int post_id=0;
		int sq=0;
		return sq;
	}
		
	/**
	 * 게시글 조회
	 * 
	 * 
	 * @author 황효진
	 */
	private void postRead(){
		
		showBanner("게시글 보기");
		List<FreeBoardVO> lvo = service.postRead();
		System.out.println("=======================================================");
		for(int i = 0 ; i<lvo.size() ; i++){
			System.out.println("("+(i+1)+")"+" "+lvo.get(i).getFree_title()+"\t"+lvo.get(i).getMember_id()+"\t"+lvo.get(i).getFree_date());
		}
		System.out.println("=======================================================");
	}
	/**
	    * 게시글 수정
	    * 
	    * 
	    * @author 황효진
	    */
	   private void postUpdate() {
	      showBanner("게시글 수정");
	      List<FreeBoardVO> f_list = service.FB_Read(currentMemmer.getMember_id()); 
	      // 현재 로그인 중인 멤버의 글만 모아서 리스트에 저장
	      
	      // 출력
	      for(int i=0; i<f_list.size(); i++){
	         System.out.println("("+(i+1)+")"+" "+f_list.get(i).getFree_title()+"\t"+f_list.get(i).getMember_id()+"\t"+f_list.get(i).getFree_date());
	      }
	      // 선택
	      Scanner sc=new Scanner(System.in);
	      int input=sc.nextInt();
	      int fnum = f_list.get(input-1).getFree_num();
	      
	      // 수정할 내용 적어서 넘겨주기
	      System.out.println(f_list.get(input-1).getFree_title());
	      System.out.println("수정할 제목을 입력해주세요.");
	      Scanner sc1 = new Scanner(System.in);
	      String post_title=sc1.nextLine();   
	      System.out.println("수정할 내용을 입력해주세요.");
	      Scanner sc2 = new Scanner(System.in);
	      String post_content=sc2.nextLine();
	      
	      
	      Map<String,Object>params=new HashMap<String,Object>();
	      params.put(post_title, input-1);
	      params.put(post_content, input-1);
	      
	      
	      service.FB_Read(post_title);
	      service.FB_Read(post_content);
	      
//	      if(service.checkPostAutho(params)){
//	         System.out.println("제목:");
//	         Scanner sc1=new Scanner(System.in);
//	         post_title=sc1.nextLine();
//	         System.out.println("내용: ");
//	         Scanner sc11=new Scanner(System.in);
//	         post_content=sc11.nextLine();
//	         
//	         if(post_title.equals("")||post_content.equals("")){
//	            System.out.println("공백을 입력했습니다.");
//	            return;
//	         }
//	      }
	      
	      // 성공 실패
	      


	   }
	/**
	 * 게시글 작성
	 * 
	 * 
	 * @author 황효진
	 */
	private void postCreate() {
		Scanner scan=new Scanner(System.in);  
		String createTitle;
		String createContent;
		showBanner("게시글 작성");
		System.out.println("게시글 제목");
		createTitle= scan.nextLine();
		System.out.println("게시글 내용");
		createContent = scan.nextLine();
		
		if(createTitle.equals("")||createContent.equals("")){
			System.out.println("내용을 작성해주세요.");
			return; 
		}
		
		FreeBoardVO board = new FreeBoardVO();
		board.setFree_title(createTitle);
		board.setFree_content(createContent);
		board.setFree_date("2019-04-30");
		board.setMember_id(currentMemmer.getMember_id());
		
		if(service.saved(board)){
			System.out.println("저장완료");
		}else{
			System.out.println("저장실패");
		}
		/*	boolean saved(FreeBoardVO board);*/
	}
	
	/*/*********************************************************************************************************************************
	 * 회원모드는 영역이 겹칩니다.
	 * *********************************************************************************************************************************
	 */
	
	
	
	/**
	 * 회원모드 <br>
	 * 회원모드의 시작페이지 입니다.
	 * @author ALL
	 */
	private void memberView() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 영화 예매");
			System.out.println("2. 한줄평");
			System.out.println("3. 자유게시판");
			System.out.println("4. 마이페이지");
			System.out.println("5. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("숫자만 입력하세요");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				continue;
			}

			switch (input) {
			case 1: // 1. 영화예매
				m_reserve();
				break;
			case 2: // 2. 한줄평
				m_comment();
				break;
			case 3: // 3. 자유게시판
				m_freeboard();
				break;
			case 4: // 4.마이페이지
				mypage();
				break;
			case 5:
				return;
			default:
				System.out.println("숫자만 입력해 주세요");
			}
		}
	}
	
	/**
	 * 회원모드 <br>
	 * 1. 영화예매
	 * @author 이진영
	 */
	private void m_reserve() {
		
		List<MovieVO> movieList = service.movieRead();
		for (int i = 0; i < movieList.size(); i++) {
		System.out.print("영화No." + (i + 1));
		System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
		System.out.print("\t관람 등급 : " + movieList.get(i).getMovie_ageGrade()+"세관람가");
		System.out.print("\t러닝 타임 : " + movieList.get(i).getMovie_runT()+"분");
		System.out.println("\t장르 : " + movieList.get(i).getGenre_name());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);
		System.out.print("예매할 영화 번호를 선택해주세요 : ");
		int movie_num = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		
		List<CinemaVO> cinemaList = service.cinemaRead();
		for (int i = 0; i < cinemaList.size(); i++) {
		System.out.print("No." + (i + 1));
		System.out.print("\t지역 : " + cinemaList.get(i).getCinema_area());
		System.out.println   ("\t지점명 : " + cinemaList.get(i).getCinema_name());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.print("에매할 번호를 선택해주세요 : ");
		int cinema_num = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		
		List<ScreenVO> screenList = service.screenRead(cinema_num-1);
		System.out.println("───────────");
		System.out.println("상영관 목록");
		System.out.println("───────────");
		for(int i=0; i<screenList.size(); i++){
		System.out.println("No." + (i + 1) + "\t" + screenList.get(i).getScreen_name());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.print("예매할 상영관 번호를 선택해주세요 : ");
		int screen_num = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		List<List<ShowVO>> readshow = service.showRead(screen_num);
	      
        System.out.println("'"+cinemaList.get(cinema_num-1).getCinema_name()+"' 의 '" + screenList.get(screen_num-1).getScreen_name() +"'에서 상영하는");
        System.out.println("'"+movieList.get(movie_num-1).getMovie_name()+"'의 상영시간표는 다음과 같습니다.");
         
        //시간표 출력
        int i = 1;
        for (List<ShowVO> list : readshow) {
        	System.out.println("No."+i+" "+list.get(0).getShow_time().get(Calendar.YEAR)+"년 "+(list.get(0).getShow_time().get(Calendar.MONTH)+1)+"월 "+list.get(0).getShow_time().get(Calendar.DATE )+"일");
        	i++;
		}
		
        System.out.println("예매할 날짜의 번호를 선택해주세요");
        int selectNum = sc.nextInt();
        
        
        i=1;
        List<ShowVO> ShowList = readshow.get(selectNum-1);
        for (ShowVO showVO : ShowList) {
        	if(!showVO.getDeleted()){
        	System.out.println("No." + i + "\t" + showVO.getShow_time().get(Calendar.HOUR_OF_DAY)+" : " + showVO.getShow_time().get(Calendar.MINUTE));
    		i++;
        	}
		}
		
        System.out.println("예매할 시간의 번호를 선택해주세요");
        selectNum = sc.nextInt();
        
        //상영번호 저장
        int showNum = ShowList.get(selectNum-1).getShow_num();
        
        //같은 상영번호를 공유하는 리스트
        List<ReserveVO> reserve_list = service.reserveRead(showNum);
        
        //좌석조회
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("\t\t\t\tSCREEN");
		System.out.println("---------------------------------------------------------------------------------");
		
		List<SeatVO> svo_List = service.seatRead(screenList.get(screen_num-1).getScreen_num());

		//좌석리스트가 존재하지 않을 때 이전화면으로 돌아감
		if(svo_List == null){
			System.out.println("상영관의 좌석이 존재하지 않습니다.");
			return;
		}
		
		for(int f = 0 ; f <= svo_List.get(svo_List.size()-1).getSeat_cols();f++){
			if(f<10){
				System.out.print(" ");
			}
			System.out.print("  "+(f+1));
		}
		
		System.out.println();
		int newLine = svo_List.get(0).getSeat_row();
		char alph = 'A';
		System.out.print(alph+" ");
		for (SeatVO seatVO : svo_List) {
			
			if(newLine < seatVO.getSeat_row()){
				alph++;
				newLine = seatVO.getSeat_row();
				System.out.println();
				System.out.print(alph+" "); 
			}
			
//			for (ReserveVO rvo : reserve_list) {
//				if(seatVO.getDeleted()){
//					System.out.print(" x  "); //삭제된 좌석
//				}
//				else if(rvo.getSeat_num()==seatVO.getSeat_num()){
//					System.out.print(" ■  "); // 예매된 좌석
//					break;
//				} else{
//					System.out.print(" □  "); // 예매되지 않은 좌석
//				}
//					
//			}
			boolean reservedseat = false;
			for(ReserveVO rvo : reserve_list){
				if(rvo.getSeat_num()==seatVO.getSeat_num())
				reservedseat = true;
			}
			
			
			if(seatVO.getDeleted()){
				System.out.print(" x  "); //삭제된 좌석
			}
			else if(reservedseat){
				System.out.print(" ■  "); // 예매된 좌석
			} else{
				System.out.print(" □  "); // 예매되지 않은 좌석
			}
					
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("---------------------------------------------------------------------------------");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		Map<String, Object> params = new HashMap<String, Object>();
		while(!service.reserveCreate(params)){
	
		System.out.print("예매할 좌석을 선택하세요.");
		
		int seat_code = inputSeatCode(svo_List.get(svo_List.size()-1).getSeat_cols()+1);
		
		while(svo_List.get(seat_code).getDeleted()){
			System.out.println("삭제된 좌석입니다.");
			System.out.println("다시 좌석을 입력해주세요.");
			//다시 선택하게
			seat_code = inputSeatCode(svo_List.get(svo_List.size()-1).getSeat_cols()+1);
		}
		
		params.put("member_id", currentMemmer.getMember_id());
		params.put("show_num", showNum);
		params.put("seat_num", svo_List.get(seat_code).getSeat_num());
			
		}
		
		
	}
	
	/**
	 * 회원모드 <br>
	 * 2. 한줄평관리
	 * @author 윤홍식
	 */
	private void m_comment() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 한줄평 등록");
			System.out.println("2. 한줄평 조회");
			System.out.println("3. 한줄평 삭제");
			System.out.println("4. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("숫자만 입력하세요");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				continue;
			}
			
			switch (input) {
			case 1: // 1. 한줄평등록
				m_commentCreate();
				break;
			case 2: // 2. 한줄평조회
				m_commentRead();
				break;
			case 3: // 3. 한줄평삭제
				m_commentDelete();
				break;
			case 4: // 4. 나가기
				return;
			default:
				System.out.println("숫자만 입력해 주세요");
			}
		}
	}
	
	
	/**
	 * 회원-한줄평 등록
	 * @author 윤홍식
	 */
	private void m_commentCreate() {
	
		showBanner("한줄평 관리 - 영화선택");
		List<MovieVO> mList = service.movieRead();//영화 가져오기
		
		for (int i = 0; i < mList.size(); i++) {
	         System.out.print("영화No." + (i + 1));
	         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
	         System.out.println("\t     러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
	         
	      }
		Scanner sc = new Scanner(System.in);
		HashMap<String, Object> map = new HashMap<String, Object>(); // 등록을 위한 해쉬맵
		
		System.out.println("한줄평을 남기실 영화를 선택해주세요.");
		int tmp = sc.nextInt();
		int movie_num;
		movie_num = mList.get(tmp-1).getMovie_num(); 
		
		if(tmp-1==movie_num){	//입력한 tmp값과 movie_num(영화번호)가 같으면 그영화에 대한 한줄평
		
		System.out.println(mList.get(tmp-1).getMovie_name() + "에 대한 한줄평을 입력해주세요"); //입력한 숫자에서 -1을 하면 movie인덱스에서 이름추출이 가능함
		String content=sc.next(); //한줄평 입력
		sc.nextLine();
		
		System.out.println("평점을 입력해주세요");
		int grade=sc.nextInt(); //평점 입력 
		
		CommentVO com = new CommentVO();
		if(currentMemmer.getMember_auth()==false){
		
		map.put("content", content);	// key, value 
		map.put("grade", grade);
		map.put("movie_num", movie_num);
		
		service.commentCreate(map);
		}
		
		}
	}
	
	/**
	 * 회원-한줄평 조회
	 * @author 윤홍식
	 */
	private void m_commentRead() {
		currentMemmer.getMember_auth();
		showBanner("한줄평 관리 - 영화선택");
		List<MovieVO> mList = service.movieRead();
		//movieRead();
		  for (int i = 0; i < mList.size(); i++) {
		         System.out.print("영화No." + (i + 1));
		         System.out.print("\t영화 제목 : " + mList.get(i).getMovie_name());
		         System.out.println("\t     러닝 타임 : " + mList.get(i).getMovie_runT()+"분");
		      }
		
		
		System.out.println("한줄평을  조회할 영화를 선택해주세요.");
		System.out.println("==>");
		Scanner sc = new Scanner(System.in);
		int tmp = sc.nextInt(); // 값을 입력받을 변수 tmp
		int movie_num;
		movie_num = mList.get(tmp - 1).getMovie_num(); // 입력받은 tmp에 -1을 한값으로
														// Movie_num의 숫자를 불러옴

		List<CommentVO> commentList = service.commentReadSelect(movie_num); // 한줄평에 대한 정보가  담겨있는 cList를불러와 commentList에담는다
		for (int i = 0; i < commentList.size(); i++) {
			System.out.print("한줄평No." + (i + 1));
			System.out.print("\t 한줄평 : " + commentList.get(i).getComt_content());
			System.out.print("\t   평점 : " + commentList.get(i).getComt_grade());
			SimpleDateFormat date1 = new SimpleDateFormat("\t\t 날짜 : "
					+ "yyy:MM:dd-hh:mm"); // 날짜 형식 지정
			System.out.println(date1.format(cal.getTime()));
		}
		
	}
	/**
	 * 회원-한줄평 삭제
	 * @author 윤홍식
	 */
	private void m_commentDelete() {
		showBanner("\t영화 조회");
		List<MovieVO> movieList = service.movieRead(); // DB에서 조회 추가해서 movieList에 넣어줌
		for (int i = 0; i < movieList.size(); i++) {
			System.out.print("영화No." + (i + 1));
			System.out.print("\t영화 제목 : " + movieList.get(i).getMovie_name());
			System.out.print("\t관람 등급 : "
					+ movieList.get(i).getMovie_ageGrade() + "세관람가");
			System.out.print("\t     러닝 타임 : " + movieList.get(i).getMovie_runT()
					+ "분");
			System.out.println("\t장르 : " + movieList.get(i).getGenre_name());
		} // for문돌려서 영화전체 조회 다받음

		Scanner sc = new Scanner(System.in);
		System.out.println("한줄평을 삭제하고 싶은 영화번호를 입력해주세요");
		int tmp = sc.nextInt(); // 값을 입력받을 변수 tmp

		int movie_num = movieList.get(tmp - 1).getMovie_num();
		List<CommentVO> commentList = service.commentSelectForMovie(movie_num);
		for (int i = 0; i < commentList.size(); i++) {
			System.out.println("한줄평No." + (i + 1) + "   "
					+ commentList.get(i).getComt_content());
		} // for문돌려서 한줄평전체 조회 다받음
		// 선택한 번호에 해당하는 한줄평 출력

		System.out.println("삭제할 한줄평 번호를 입력해주세요.");
		int tmp1 = sc.nextInt();

		int comment_num;
		comment_num = commentList.get(tmp1 - 1).getComt_num();
		boolean d = service.commentDelete(comment_num);
	}
	
	/**
	 * 회원모드<br>
	 * 3. 자유게시판관리
	 * @author 황효진
	 */
	private void m_freeboard() {
		
	}

	/**
	 * 회원모드<br>
	 * 4.마이페이지
	 * @author 이진영, 윤홍식
	 */
	private void mypage() {
		while (true) {
			showBanner("\t즐거운 문화생활 CGV");
			System.out.println("1. 회원 정보 조회");
			System.out.println("2. 회원 정보 수정");
			System.out.println("3. 회원 탈퇴");
			System.out.println("4. 예매조회");
			System.out.println("5. 예매취소");
			System.out.println("6. 나가기");

			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				input = sc.nextInt();
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("숫자만 입력하세요");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				continue;
			}

			switch (input) {
			case 1: // 1. 회원 정보 조회
				m_memberRead();
				break;
			case 2: // 2. 회원 정보 수정
				m_memberUpdate();
				break;
			case 3: // 3. 회원 탈퇴
				m_memberdDelete();
				break;
			case 4: // 4. 예매 조회
				reserveRead();
				break;
			case 5: // 5. 예매 삭제
				reserveDelete();
				break;
			case 6: // 6. 나가기
				return;
			default:
				System.out.println("숫자만 입력해 주세요");
			}
		}
		
		
		
		
	}
	private void m_memberRead() {
	      
        // currentMember을 활용하세요
        showBanner("내 정보 조회");
        System.out.println("회원 ID : " + currentMemmer.getMember_id());
        System.out.println("회원 이름 : " + currentMemmer.getMember_name());
        System.out.println("핸드폰 번호 : " + currentMemmer.getMember_phoneNum());
        System.out.println("보유 캐시 : " + currentMemmer.getMember_cash());
  }
  /**
   * 사용자 수정
   * @author 윤홍식
   */
  private void m_memberUpdate() {
     String member_id; //기본키
     String member_pw; //회원비밀번호
     String member_name; //회원이름
     String member_phoneNum; //회원핸드폰번호
     Calendar member_birth; //회원생일(연령제한)
     int member_cash; //보유금액
     
     showBanner("회원 정보 수정");
     member_id = inputId();
     member_pw = inputPw();
     member_birth = inputBirth();
     member_phoneNum = inputPh();
     member_name=inputName();
     
     currentMemmer.setMember_name(member_name);;
     currentMemmer.setMember_pw(member_pw);
     currentMemmer.setMember_birth(member_birth);
     currentMemmer.setMember_phoneNum(member_phoneNum);

     System.out.println("성공적으로 회원정보를 변경하였습니다!");
  }
	/**
	 * 사용자-삭제
	 * @author 윤홍식
	 */
	private void m_memberdDelete() {
		while(true){
			List<MemberVO> mList = service.memberRead();
			for(int i = 0; i < mList.size(); i++){
				MemberVO member = mList.get(i);
				System.out.println("No. " + (i+1));
				System.out.println("회원의 ID : " + member.getMember_id());
				System.out.println("회원의 이름 : " + member.getMember_name());
				System.out.println("---------------------------------");
			}
			System.out.println("삭제할 회원번호를 입력해주세요");
			System.out.println("==>");
			Scanner sc = new Scanner(System.in);
			int tmp = 0;
			String mem_id = null;
			try{
				tmp = sc.nextInt();
				mem_id = mList.get(tmp-1).getMember_id();
			}catch(IndexOutOfBoundsException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}catch(InputMismatchException e){
				System.out.println("! 목록에 해당하는 숫자만 입력하세요 !");
				continue;
			}
			
			boolean d = service.memberDelete(mem_id);
			return;
		}
	}
	

	private void reserveDelete() {
		// TODO Auto-generated method stub
		return;
	}

}
