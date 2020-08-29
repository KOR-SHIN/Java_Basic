package package_Database;

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

public class Database {
	private List<CinemaVO> cinemaList = new ArrayList<CinemaVO>();
	private List<CommentVO> commentList = new ArrayList<CommentVO>();
	private List<MemberVO> memberList = new ArrayList<MemberVO>();
	private List<MovieVO> movieList = new ArrayList<MovieVO>();
	private List<ReserveVO> reserveList = new ArrayList<ReserveVO>();
	private List<ScreenVO> screenList = new ArrayList<ScreenVO>();
	private List<SeatVO> seatList = new ArrayList<SeatVO>();
	private List<ShowVO> showList = new ArrayList<ShowVO>();
	private List<FreeBoardVO> freeList = new ArrayList<FreeBoardVO>();
	private List<List<SeatVO>> sl_List = new ArrayList<List<SeatVO>>();
	private List<List<ShowVO>> sw_List = new ArrayList<List<ShowVO>>();

	private static Database database = new Database();

	private Database() {

	}

	// get영역set은 DB를 수정하면 안되니까 만들면안
	public static Database getDatabase() {
		return database;
	}

	Calendar cal = Calendar.getInstance(); // Date객체 받아오기 위해 캘린더 클래스 사용
	// ==========================================DB생성===================================================

	// 초기화
	// 회원(Member) 초기화
	{
		MemberVO admin = new MemberVO(); // 관리자 1명
		admin.setMember_id("admin");
		admin.setMember_pw("1234");
		admin.setMember_name("관리자");
		admin.setMember_phoneNum("010-5775-4091");
		cal.set(1940, 12, 30);
		admin.setMember_birth(cal); // getTime->생일로 초기화해야함
		admin.setMember_cash(10000000);
		admin.setMember_auth(true);
		memberList.add(admin);

		MemberVO member1 = new MemberVO(); // 회원
		member1.setMember_id("member1");
		member1.setMember_pw("1234");
		member1.setMember_name("정준");
		member1.setMember_phoneNum("010-5771-4091");
		cal.set(1940, 11, 31);
		member1.setMember_birth(cal);
		member1.setMember_cash(10000000);
		member1.setMember_auth(false);
		memberList.add(member1);

		MemberVO member2 = new MemberVO();
		member2.setMember_id("member2");
		member2.setMember_pw("1234");
		member2.setMember_name("최수민");
		member2.setMember_phoneNum("010-5772-4091");
		cal.set(1999, 02, 02);
		member2.setMember_birth(cal);
		member2.setMember_cash(10000000);
		member2.setMember_auth(false);
		memberList.add(member2);

		MemberVO member3 = new MemberVO();
		member3.setMember_id("member3");
		member3.setMember_pw("1234");
		member3.setMember_name("김윤태");
		member3.setMember_phoneNum("010-5773-4091");
		member3.setMember_cash(10000000);
		member3.setMember_auth(false);
		memberList.add(member3);

		MemberVO member4 = new MemberVO();
		member4.setMember_id("nomoney");
		member4.setMember_pw("1234");
		member4.setMember_name("차수봉");
		member4.setMember_phoneNum("010-5777-4091");
		member4.setMember_cash(100);
		member4.setMember_auth(false);
		memberList.add(member4);

		MemberVO member5 = new MemberVO();
		member5.setMember_id("member5");
		member5.setMember_pw("1234");
		member5.setMember_name("정광희");
		member5.setMember_phoneNum("010-5779-4091");
		member5.setMember_cash(10000000);
		member5.setMember_auth(false);
		memberList.add(member5);

	}

	// 영화관(Cinema) 초기화
	{
		CinemaVO cinema1 = new CinemaVO();
		cinema1.setCinema_name("대흥CGV");
		cinema1.setCinema_area("대전");
		cinemaList.add(cinema1);

		CinemaVO cinema2 = new CinemaVO();
		cinema2.setCinema_name("역삼CGV");
		cinema2.setCinema_area("서울");
		cinemaList.add(cinema2);

		CinemaVO cinema3 = new CinemaVO();
		cinema3.setCinema_name("탄방CGV");
		cinema3.setCinema_area("대전");
		cinemaList.add(cinema3);
	}

	// 영화(Movie) 초기화
		{
			MovieVO movie1 = new MovieVO();
			movie1.setMovie_name("어벤져스");
			movie1.setMovie_ageGrade(12);
			movie1.setMovie_runT(120);
			movie1.setGenre_name("액션");
			movieList.add(movie1);

			MovieVO movie2 = new MovieVO();
			movie2.setMovie_name("노트북");
			movie2.setMovie_ageGrade(15);
			movie2.setMovie_runT(120);
			movie2.setGenre_name("로맨스");
			movieList.add(movie2);

			MovieVO movie3 = new MovieVO();
			movie3.setMovie_name("나홀로집에");
			movie3.setMovie_ageGrade(0);
			movie3.setMovie_runT(120);
			movie3.setGenre_name("코미디");
			movieList.add(movie3);

			MovieVO movie4 = new MovieVO();
			movie4.setMovie_name("신세계");
			movie4.setMovie_ageGrade(18);
			movie4.setMovie_runT(120);
			movie4.setGenre_name("범죄/드라마");
			movieList.add(movie4);

			MovieVO movie5 = new MovieVO();
			movie5.setMovie_name("드래곤볼");
			movie5.setMovie_ageGrade(7);
			movie5.setMovie_runT(120);
			movie5.setGenre_name("애니메이션");
			movieList.add(movie5);

		}

		// 한줄평(Comment) 초기화

		{
		      CommentVO comment1 = new CommentVO();
		      comment1.setComt_date(cal.getTime()); // 낼바꿔야됨
		      comment1.setComt_content("어벤져스 재밌어요");
		      comment1.setComt_grade(5); // 평점
		      comment1.setMovie_num(0); // 외래키 영화번호 - 어벤저스
		      comment1.setMember_id("admin"); // 외래키 관리자
		      commentList.add(comment1);
		      
		      CommentVO comment1_1 = new CommentVO();
		      comment1_1.setComt_date(cal.getTime()); // 낼바꿔야됨
		      comment1_1.setComt_content("어벤져스 감동입니다.");
		      comment1_1.setComt_grade(4); // 평점
		      comment1_1.setMovie_num(0); // 외래키 영화번호 - 어벤저스
		      comment1_1.setMember_id("admin"); // 외래키 관리자
		      commentList.add(comment1_1);
		      
		      CommentVO comment1_2 = new CommentVO();
		      comment1_2.setComt_date(cal.getTime()); // 낼바꿔야됨
		      comment1_2.setComt_content("타노스 멋있습니다.");
		      comment1_2.setComt_grade(0); // 평점
		      comment1_2.setMovie_num(0); // 외래키 영화번호 - 어벤저스
		      comment1_2.setMember_id("admin"); // 외래키 관리자
		      commentList.add(comment1_2);

		      CommentVO comment2 = new CommentVO();
		      comment2.setComt_date(cal.getTime());
		      comment2.setComt_content("노트북 그저 그래요");
		      comment2.setComt_grade(3); // 평점
		      comment2.setMovie_num(1);
		      comment2.setMember_id("Yoon");
		      commentList.add(comment2);
		      
		      CommentVO comment2_1 = new CommentVO();
		      comment2_1.setComt_date(cal.getTime());
		      comment2_1.setComt_content("노트북 노잼잼");
		      comment2_1.setComt_grade(3); // 평점
		      comment2_1.setMovie_num(1);
		      comment2_1.setMember_id("Yoon");
		      commentList.add(comment2_1);

		      CommentVO comment3 = new CommentVO();
		      comment3.setComt_date(cal.getTime());
		      comment3.setComt_content("나홀로집에 너무~ 재밌어요");
		      comment3.setComt_grade(4); // 평점
		      comment3.setMovie_num(2); // 외래키 영화번호
		      comment3.setMember_id("jung");// 외래키 회원번호
		      commentList.add(comment3);

		      CommentVO comment3_1 = new CommentVO();
		      comment3_1.setComt_date(cal.getTime());
		      comment3_1.setComt_content("나홀로집에 너무~ 재밌어요!!!");
		      comment3_1.setComt_grade(4); // 평점
		      comment3_1.setMovie_num(2); // 외래키 영화번호
		      comment3_1.setMember_id("jung");// 외래키 회원번호
		      commentList.add(comment3_1);
		      
		      CommentVO comment4 = new CommentVO();
		      comment4.setComt_date(cal.getTime());
		      comment4.setComt_content("신세계 잔인해요");
		      comment4.setComt_grade(2); // 평점
		      comment4.setMovie_num(3);
		      comment4.setMember_id("Lee");
		      commentList.add(comment4);

		      CommentVO comment5 = new CommentVO();
		      comment5.setComt_date(cal.getTime());
		      comment5.setComt_content("드래곤볼 잼잼입니다.");
		      comment5.setComt_grade(1); // 평점
		      comment5.setMovie_num(4);
		      comment5.setMember_id("Hwang");
		      commentList.add(comment5);
		      
		      CommentVO comment5_1 = new CommentVO();
		      comment5_1.setComt_date(cal.getTime());
		      comment5_1.setComt_content("드래곤볼 잼잼잼~입니다.");
		      comment5_1.setComt_grade(1); // 평점
		      comment5_1.setMovie_num(4);
		      comment5_1.setMember_id("Hwang");
		      commentList.add(comment5_1);
		   }
	
	
	
	
	

	// 상영(show) 초기화
	{

		
		
		
		//show time 나중에 추가
//		ShowVO show1 = new ShowVO();
//		show1.setMovie_num(0);
//		show1.setScreen_num(0);
//		show1.setShow_time(show_time);
//		showList.add(show1);
//
//		ShowVO show2 = new ShowVO();
//		show2.setMovie_num(1);
//		show2.setScreen_num(1);
//		showList.add(show2);
//
//		ShowVO show3 = new ShowVO();
//		show3.setMovie_num(2);
//		show3.setScreen_num(2);
//		showList.add(show3);
	}

	// 상영관(Screen),좌석(seat)초기화
	   {
		   
		  for(int cine = 0 ; cine < 3 ; cine++) {
			  for(int scre = 0 ; scre < (int)(Math.random()*5+1) ; scre++) {
			      ScreenVO screen = new ScreenVO();
			      screen.setScreen_name((scre+1)+"관");
			      screen.setCinema_num(cine);
			      screenList.add(screen);
	
			      int r = (int)(Math.random()*5+10);
			      int c = (int)(Math.random()*5+10);
				
			      List<SeatVO> sl = new ArrayList<SeatVO>();
			      for (int i = 0; i < r; i++) {
					  for (int j = 0; j < c; j++) {
					  SeatVO sv = new SeatVO();
					  sv.setScreen_num(scre);
					  sv.setSeat_row(i);
					  sv.setSeat_cols(j);
				      sl.add(sv);
					  }
			      }
				  for(int del = 0 ; del < (int)(Math.random()*6+3) ; del++) {
					sl.get((int)(Math.random()*15)).setDeleted();
				  }
				  
				  // 1~5 의 사람들이 랜덤으로 예매하게 할 수 없을까?
				  
				  
				  
				  
				  sl_List.add(sl);
			 }
			  
//			 Calendar ct = Calendar.getInstance();
//				ct.set(2019, 5, 2, 0, 0);
//				
//				for (ScreenVO svo : screenList) {
//					//시간관리 ( 러닝타임+20분을 하루로 나누어 showVO 객체를 생성한다.
//					int runtime = movieList.get(0).getMovie_runT();
//					int Totaltime = runtime + 20;
//					int limited_date = ct.get(Calendar.DATE);
//					
//					Calendar tmptime = Calendar.getInstance();
//					tmptime.set(2019, 5-1, 2+1 , 0 , 0);
//					tmptime.add(Calendar.MINUTE, -Totaltime);
//					int limit_hour = tmptime.get(Calendar.HOUR_OF_DAY);
//					
//					List<Calendar> timetable = new ArrayList<Calendar>();
//					System.out.println(limit_hour);
//					
//					do {
//						ct.add(Calendar.MINUTE, Totaltime);
//						timetable.add(ct);
//					} while (ct.get(Calendar.HOUR_OF_DAY)<limit_hour);
//					
//					timetable.remove(timetable.size()-1);
//					
//					Map<String, Object> params = new HashMap<String, Object>();
//			        params.put("movie_num", movieList.get(0).getMovie_num());
//			        params.put("cinema_num", cinemaList.get(0).getCinema_num());
//			        params.put("screen_name", svo.getScreen_num());
//			        params.put("timetable", timetable);
//					
//					showCreate(params);
//				} 
			  
		 }
		   
	   }

	// 에매(Reserve) 초기화
	{

		
		
		
	}

	// 자유게시판 초기화
	{
		FreeBoardVO free1 = new FreeBoardVO();
		free1.setFree_title("공지사항");
		free1.setFree_content("욕설X 비방X");
		free1.setFree_date("2019-04-24");
		free1.setFree_writeNum(1);
		free1.setFree_cnt(341);
		free1.setMember_id("admin"); // 회원번호로 작성자 이름 끌어옴
		freeList.add(free1);

		FreeBoardVO free2 = new FreeBoardVO();
		free2.setFree_title("안녕하세요");
		free2.setFree_content("잘부탁드립니다");
		free2.setFree_date("2019-04-26");
		free2.setFree_writeNum(2);
		free2.setFree_cnt(6);
		free2.setMember_id("member1");
		freeList.add(free2);

		FreeBoardVO free3 = new FreeBoardVO();
		free3.setFree_title("잘부탁드립니다.");
		free3.setFree_content("ㅎㅎ반갑습니다.");
		free3.setFree_date("2018-03-24");
		free3.setFree_writeNum(3);
		free3.setFree_cnt(34);
		free3.setMember_id("member2");
		freeList.add(free3);

		FreeBoardVO free4 = new FreeBoardVO();
		free4.setFree_title("어벤져스");
		free4.setFree_content("재밌습니다. 꼭 보세요~");
		free4.setFree_date("2019-07-23");
		free4.setFree_writeNum(4);
		free4.setFree_cnt(23);
		free4.setMember_id("member3");
		freeList.add(free4);

		FreeBoardVO free5 = new FreeBoardVO();
		free5.setFree_title("스파이더맨");
		free5.setFree_content("노잼입니다.");
		free5.setFree_date("2019-02-22");
		free5.setFree_writeNum(5);
		free5.setFree_cnt(324);
		free5.setMember_id("member4");
		freeList.add(free5);

	}

	public void signUp(MemberVO mvo) {
		System.out.println(mvo.getMember_id());
		memberList.add(mvo);
	}

	public boolean duplicate_id(String mem_id) {
		boolean result = true;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getMember_id().equals(mem_id)) {
				result = false;
				return result;
			}
		}
		return result;
	}

	public MemberVO logIn(Map<String, String> params) {
		MemberVO mvo = null;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getMember_id().equals(params.get("mem_id"))
					&& memberList.get(i).getMember_pw()
							.equals(params.get("mem_pw"))) {
				mvo = memberList.get(i);
			}
		}
		return mvo;
	}

	/*
	 * /*************************************************************************
	 * ********************************************************
	 * 
	 * 윤홍식 : 회원관리와 영화관리,한줄평을 맡고 있습니다.
	 * 
	 * **************************************************************************
	 * *******************************************************
	 */

	/**
	 * 회원정보 조회 메서드
	 * 
	 * @author 윤홍식
	 * @return mList
	 */
	public List<MemberVO> memberRead() {
		List<MemberVO> mList = new ArrayList<MemberVO>();
		for (int i = 0; i < memberList.size(); i++) {
			if (!memberList.get(i).getDeleted()) {
				mList.add(memberList.get(i));
			}
		}

		return mList;
	}

	/**
	 * 회원정보 삭제 메서드
	 * 
	 * @author 윤홍식
	 * @param mem_id
	 * @return
	 */

	public boolean memberDelete(String mem_id) {
		for (MemberVO mvo : memberList) {
			if (mvo.getMember_id().equals(mem_id)) {
				if (mvo.getDeleted() == true) {
					System.out.println("이미 삭제된 아이디입니다.");
					return false;
				} else {
					mvo.setDeleted();
					System.out.println(mvo.getMember_name() + " 님을 탈퇴시켰습니다.");
					return mvo.getDeleted();
				}
			}

		}
		return false;
	}
	/**
	 * 회원 수정 메서드
	 * @param member_id
	 * @return
	 */
	public List<MemberVO> memberReadSelect(String member_id) {
		List<MemberVO> mList = new ArrayList<MemberVO>(); // MemberVO를 받아올 객체
		for (int i = 0; i < memberList.size(); i++) {
			if(!memberList.get(i).getDeleted()){
				if(member_id==memberList.get(i).getMember_id()){
					mList.add(memberList.get(i));
				}
			}
		}
		return mList;
	}

	/**
	 * 영화 등록 메서드
	 * 
	 * @author 윤홍식
	 * @param mparams
	 * @return
	 */
	public void movieCreate(Map<String, Object> params) {
		MovieVO mov = new MovieVO();
		String movie = (String) params.get("movie");
		int agegrad = (int) params.get("agegrade");
		int runT = (int) params.get("runT");
		String genre = (String) params.get("genre");

		mov.setMovie_name(movie);
		mov.setMovie_ageGrade(agegrad);
		mov.setMovie_runT(runT);
		mov.setGenre_name(genre);

		movieList.add(mov);
		System.out.println(mov.getMovie_name() + "등록이 완료되었습니다.");
	}

	/**
	 * 영화 조회 메서드
	 * 
	 * @author 윤홍식
	 * 
	 *         !(isDeletd = false) NOT(삭제된게 아니다) => 삭제 안됨
	 */
	public List<MovieVO> movieRead() {
		List<MovieVO> mvo = new ArrayList<MovieVO>(); // 새롭게 List를 만듦
		for (MovieVO movie : movieList) { // movieList의 값들을 movie에 담음
			if (!movie.getDeleted()) { // 만약 삭제여부가 진짜면
				mvo.add(movie); // mvo에 movie(movie의 전체 값)를 넣어줌
			}
		}
		return mvo;
	}
	
	
	
	/**
	 * 영화 삭제 메서드
	 * 
	 * @author 윤홍식
	 * @param movie_num
	 * @return
	 */
	public boolean movieDelete(int movie_num) {
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getMovie_num() == movie_num) {
				if (movieList.get(i).getDeleted() == true) {
					System.out.println("이미 삭제된 영화입니다.");
					return false;
				} else {
					movieList.get(i).setDeleted();
					System.out.println(movieList.get(i).getMovie_name()
							+ "을(를) 삭제 시켰습니다.");
					return true;
				}
			}
		}
		return true;
	}

	/**
	 * 한줄평 등록 메서드
	 * @author 윤홍식
	 */
	public void commentCreate(Map<String, Object> params) {
		
		CommentVO com = new CommentVO();
		String content = (String) params.get("content");
		int grade = (int) params.get("grade");
		int movie_num = (int) params.get("movie_num");	//???????!!!!
		
		com.setComt_content(content);
		com.setComt_grade(grade);
		com.setMovie_num(movie_num);
		
			commentList.add(com);			
		/*for (int i = 0; i < commentList.size(); i++) {
			System.out.println(commentList.get(i).getComt_content());
			
		}*/
		System.out.println("한줄평 등록완료");
		
	}
	
	
	
	/**
	 * 한줄평 조회 메서드
	 * @author 윤홍식
	 */
	public List<CommentVO> commentReadSelect(int movie_num) {
		List<CommentVO> cList = new ArrayList<CommentVO>(); // commentVO를 받아올 객체
															// cList생성
		for (int i = 0; i < commentList.size(); i++) {
			if(!commentList.get(i).getDeleted()){
				if (movie_num == commentList.get(i).getMovie_num()) {
					cList.add(commentList.get(i));
				}
			}
		}
		return cList;
	};
	
	/**
	 * 한줄평 삭제 메서드
	 * @author 윤홍식
	 */
	public boolean commentDelete(int comment_num) {
		/*for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getMovie_num() == movie_num) {
				if (movieList.get(i).getDeleted() == true) {
					System.out.println("이미 삭제된 영화입니다.");
					return false;
				} else {
					movieList.get(i).setDeleted();
					System.out.println(movieList.get(i).getMovie_name()
							+ "을(를) 삭제 시켰습니다.");
					return true;
				}
			}
		}
		return true;*/
		for(int i=0; i< commentList.size(); i++){
			if(commentList.get(i).getComt_num() == comment_num){
				if(commentList.get(i).getDeleted()==true) {
					System.out.println("이미 삭제된 한줄평 입니다.");
					return false;
				} else {
					commentList.get(i).setDeleted();
					System.out.println("한줄평 삭제완료");
					
					return true;
				}
			}
		}
		
		return true;
	}	
	
	public List<CommentVO> commentSelectForMovie(int movie_num){
		List<CommentVO> tempList = new ArrayList<CommentVO>();
		for(int i = 0; i<commentList.size(); i++){
			CommentVO tempComment = commentList.get(i);
			if(tempComment.getMovie_num() == movie_num&&!tempComment.getDeleted()){
				tempList.add(tempComment);
			}
		}
		
		return tempList;
	}
	
	/*
	 * /*************************************************************************
	 * ********************************************************
	 * 
	 * 정소미 : 영화관 관리, 상영관 관리, 상영 관리를 맡고 있습니다.
	 * 
	 * **************************************************************************
	 * *******************************************************
	 */

	/**
	 * 영화관을 등록하기 위한 메서드
	 * 
	 * @author 정소미
	 */
	public boolean cinemaCreate(Map<String, String> params) {
		CinemaVO cvo = new CinemaVO();
		cvo.setCinema_area(params.get("area"));
		cvo.setCinema_name(params.get("name"));

		cinemaList.add(cvo);
		return true;
	}

	/**
	 * 영화관을 조회하기 위한 메서드
	 * 
	 * @author 정소미
	 */
	public List<CinemaVO> cinemaRead() {
		List<CinemaVO> cvo = new ArrayList<CinemaVO>();
		for (CinemaVO cinemaVO : cinemaList) {
			if (!cinemaVO.getDeleted()) {
				cvo.add(cinemaVO);
			}
		}
		return cvo;
	}

	/**
	 * 영화관을 삭제하기 위한 메서드
	 * 
	 * @author 정소미
	 * @param cinema_num
	 */
	public boolean cinemaDelete(int cinema_num) {
		cinemaList.get(cinema_num).setDeleted();
		return true;
	}

	/**
	 * 영화관을 수정하기 위한 메서드
	 * 
	 * @param CinemaVO타입의
	 *            객체 cvo
	 * @return boolean
	 * @author 정소미
	 */
	public boolean cinemaUpdate(Map<String, Object> params) {
		for (int i = 0; i < cinemaList.size(); i++) {
			if(cinemaList.get(i).getCinema_num() == (Integer) params.get("cinema_num")){
				cinemaList.get(i).setCinema_area((String)params.get("cinema_area"));
				cinemaList.get(i).setCinema_name((String)params.get("cinema_name"));
				return true;
			}
		}
		return false;
	}

	/**
	 * 상영관을 조회하기 위한 메서드
	 * 
	 * @author 정소미
	 */
	public List<ScreenVO> screenRead(int cinema_num) {
		List<ScreenVO> screen_list = new ArrayList<ScreenVO>();
		for (ScreenVO screenVO : screenList) {
			if (!screenVO.getDeleted()) {
				if (screenVO.getCinema_num() == cinema_num) {
					screen_list.add(screenVO);
				}
			}
		}
		return screen_list;
	}

	/**
	 * 상영관을 등록하기 위한 메서드
	 * 
	 * @author 정소미
	 */
	public boolean screenCreate(Map<String, Object> params) {
		ScreenVO svo = new ScreenVO();
		for (int i = 0; i < cinemaList.size(); i++) {
			if (cinemaList.get(i).getCinema_num() == (Integer)params.get("cinema_num")-1) {
				svo.setCinema_num((Integer)params.get("cinema_num")-1);
				svo.setScreen_name((String) params.get("screen_name"));
			}
		}
		screenList.add(svo);
		return true;
	}

	/**
	 * 상영관을 삭제할 메서드
	 * 
	 * @author 정소미
	 */
	public boolean screenDelete(int screen_num) {
		for (int i = 0; i < screenList.size(); i++) {
			if (screenList.get(i).getScreen_num() == screen_num) {
				screenList.get(i).setDeleted();
				return true;
			}
		}
		return false;
	}

	
	/***
	 * 상영등록을 위한 메서드
	 * @author 정소미
	 * @param params
	 * @return
	 */
	public boolean showCreate(Map<String, Object> params) {
		List<Calendar> timetable = (List<Calendar>) params.get("timetable");
		List<ShowVO> dateList = new ArrayList<ShowVO>();
		for (ShowVO svo : showList) {
			if(svo.getScreen_num()==(Integer)params.get("screen_num") && svo.getShow_time().get(Calendar.DATE)==timetable.get(0).get(Calendar.DATE)){
				System.out.println("현재 날짜에 이미 등록된 상영이 존재합니다.");
				return false;
			}
		}

		for (Calendar calendar : timetable) {
			ShowVO svo = new ShowVO();
			svo.setMovie_num((Integer)params.get("movie_num"));
			svo.setScreen_num((Integer)params.get("screen_num"));
			svo.setShow_time((Calendar)calendar);
			
			if(svo==null){
				return false;
			}
			dateList.add(svo);
		}
		sw_List.add(dateList);
		return true;
	}
	
	/**
	 * 
	 * 상영조회
	 * 
	 * @author 정소미
	 * @param screen_num
	 * @return
	 */
	
	public List<List<ShowVO>> showRead(int screen_num) {
		List<List<ShowVO>> readshow = new ArrayList<List<ShowVO>>();
		
		for (List<ShowVO> list : sw_List) {
			if(list.get(0).getScreen_num()==screen_num){
				readshow.add(list);
			}
		}
		 
		return readshow;
	}
	
	/*
	 * /*************************************************************************
	 * ********************************************************
	 * 
	 * 이진영 : 좌석관리와 예매관리를 맡고 있습니다.
	 * 
	 * **************************************************************************
	 * *******************************************************
	 */

	/**
	 * 좌석을 조회하기 위한 메서드
	 * 
	 * @author 이진영
	 * @return
	 */
	public List<SeatVO> seatRead(int screen_num) {
		for (List<SeatVO> sl : sl_List) {
			if (sl.get(0).getScreen_num() == screen_num) {
				return sl;
			}
		}
		return null;
	}

	/**
	 * 좌석을 만들기 위한 메서드
	 * 
	 * @param params
	 * @return
	 */
	public boolean seatCreate(Map<String, Integer> params) {
		List<SeatVO> sl = new ArrayList<SeatVO>();
		for (int i = 0; i < params.get("seat_row"); i++) {
			for (int j = 0; j < params.get("seat_cols"); j++) {
				SeatVO sv = new SeatVO();
				sv.setScreen_num(params.get("screen_num"));
				sv.setSeat_row(i);
				sv.setSeat_cols(j);
				sl.add(sv);
			}
		}
		sl_List.add(sl);
		return true;
	}

	/**
	 * 예매된 좌석을 판단하는 예매리스트
	 * 
	 * : 상영번호와 일치하는 예매 목록을 받습니다.
	 * @param showNum
	 * @return
	 */
	public List<ReserveVO> reserveRead(int showNum) {
		List<ReserveVO> rv_list = new ArrayList<ReserveVO>();
		
		for (ReserveVO reserveVO : reserveList) {
			if(reserveVO.getShow_num()==showNum){
				rv_list.add(reserveVO);
			}
		}
		
		return rv_list;
	}

	
	public boolean reserveCreate(Map<String, Object> params) {
		ReserveVO rvo = new ReserveVO();
		if(params.get("member_id")==null) {
			return false;
		}
		
		// 이미예약됬을때
		for (ReserveVO r : reserveList) {
			
			if(r.getMember_id().equals( (String)params.get("member_id") )){
				if(r.getSeat_num()==((Integer)params.get("seat_num"))){
					System.out.println("이미 예약된 좌석입니다.");
					return false;
				}
			}
			
			//같은 아이디를 가진 멤버리스트

			
		}
		
		// 돈없을때 false , 돈있을때 돈 차감함
		for (MemberVO mvo : memberList) {
			if(mvo.getMember_id().equals((String)params.get("member_id"))){
				if(mvo.getMember_cash()<rvo.getReserve_money()){
					System.out.println("예매할 돈이 부족합니다.");
					return false;
				}
			}else{
				System.out.println(mvo.getMember_cash());
				mvo.setMember_cash(mvo.getMember_cash()-rvo.getReserve_money());
				System.out.println("예매비용으로 '"+rvo.getReserve_money()+"'원이 차감되었고,");
				System.out.println("남은 금액은 '"+mvo.getMember_cash()+"'원 입니다.");
				break;
			}
		}
		
		rvo.setMember_id((String)params.get("member_id"));
		rvo.setShow_num((Integer)params.get("show_num"));
		rvo.setSeat_num((Integer)params.get("seat_num"));
		
		reserveList.add(rvo);
		
		return true;
	}
	
	
	public List<ReserveVO> memberReserveRead(Map<String, Object> params) {
		List<ReserveVO> mrList = new ArrayList<ReserveVO>();
		
		List<ReserveVO> nodelList = new ArrayList<ReserveVO>();
		for (ReserveVO reserveVO : reserveList) {
			if(!reserveVO.getDeleted()) {
				nodelList.add(reserveVO);
			}
		}
		
		if((boolean)params.get("member_auth")) {
			mrList=nodelList;
		}else {
			for (ReserveVO reserveVO : nodelList) {
				if(reserveVO.getMember_id().equals((String)params.get("member_id"))){
					mrList.add(reserveVO);
				}
			}
		}
		
		return mrList;
	}
	
	/**
	 * 상영번호를 통해 영화이름,영화관이름,상영관이름,상영시간을 불러오는 리스트
	 * @author 이진영
	 * @param show_num
	 * @return
	 */
	public Map<String, String> getReserveElement(int show_num) {
		Map<String,String> element = new HashMap<String, String>();
		
		ShowVO svo = new ShowVO();
		for (List<ShowVO> list : sw_List) {
			for (ShowVO showVO : list) {
				if(showVO.getShow_num()==show_num) {
					svo = showVO;
				}
			}
		}
		
		
		String mName = movieList.get(svo.getMovie_num()).getMovie_name(); //영화이름
		String ciName = cinemaList.get(screenList.get(svo.getScreen_num()).getCinema_num()).getCinema_name(); //영화관이름
		String scName = screenList.get(svo.getScreen_num()-1).getScreen_name(); //상영관이름
		
		Calendar tm = svo.getShow_time();
		String ymd = tm.get(Calendar.YEAR)+" / "+(tm.get(Calendar.MONTH)+1) +" / "+tm.get(Calendar.DATE);
		
		String shtime = tm.get(Calendar.HOUR_OF_DAY)+":"+tm.get(Calendar.MINUTE)+"~";
		tm.add(Calendar.MINUTE, movieList.get(svo.getMovie_num()).getMovie_runT());
		shtime +=tm.get(Calendar.HOUR_OF_DAY)+":"+tm.get(Calendar.MINUTE);
		
		element.put("movie_name", mName);
		element.put("cinema_name", ciName);
		element.put("screen_name",scName);
		element.put("ymd", ymd);
		element.put("show_time", shtime);
		
		return element;
	}
	
	
	/**
	 * 좌석이름을 불러오는 메서드
	 * @param seat_num
	 * @return
	 */
	public String getColRow(int seat_num) {
		String result = null;
		int sn = 0;
		
		List<SeatVO> list = reterSeatlist(seat_num);
		for(int i = 0 ; i < list.size() ; i++) {
			if(list.get(i).getSeat_num()==seat_num) {
				sn=i+1;
				break;
			}
		}
		
		int newLine = list.get(0).getSeat_row();
		char alph = 'A';
		int tr = sn/(list.get(0).getSeat_cols()+1);
		for(int i = 1 ; i < tr ; i ++) {
			alph++;
		}
		
		
		result = alph+""+(sn/(list.get(0).getSeat_cols()+1));
		return result;
	}
	
	public List<SeatVO> reterSeatlist(int seat_num){
		for (List<SeatVO> list : sl_List) {
			for(SeatVO svo : list) {
				if(svo.getSeat_num()==seat_num) {
					return list;
				}
			}
		}
		return null;
	}
	
	
	/*
	 * /*************************************************************************
	 * ********************************************************
	 * 
	 * 황효진 : 자유게시판을 맡고 있습니다.
	 * 
	 * **************************************************************************
	 * *******************************************************
	 */

	public boolean saved(FreeBoardVO board) {
		freeList.add(board);
		return true;
	}

	public List<FreeBoardVO> postRead() {
		List<FreeBoardVO> frList = new ArrayList<FreeBoardVO>();
		for (FreeBoardVO freeBoardVO : freeList) {
			if(!freeBoardVO.getDeleted()){
					frList.add(freeBoardVO);
			}
		}
		return frList;
	}

	
	public List<FreeBoardVO> m_postRead(String member_id) {
		List<FreeBoardVO> frList = new ArrayList<FreeBoardVO>();
		for (FreeBoardVO freeBoardVO : freeList) {
			if(!freeBoardVO.getDeleted()){
				if(freeBoardVO.getMember_id()==member_id){
					frList.add(freeBoardVO);
				}
			}
		}
		return frList;
	}

	public List<FreeBoardVO> FB_Read() {
		for(FreeBoardVO freeBoardVO:freeList){
//			if(!freeBoardVO.)
		}
		// TODO Auto-generated method stub
		return null;
	}




	
}
