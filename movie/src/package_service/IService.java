package package_service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import package_VO.CinemaVO;
import package_VO.CommentVO;
import package_VO.FreeBoardVO;
import package_VO.MemberVO;
import package_VO.MovieVO;
import package_VO.ReserveVO;
import package_VO.ScreenVO;
import package_VO.SeatVO;
import package_VO.ShowVO;

public interface IService {

	/**
	 * 회원가입을 위한 메서드
	 * 
	 * @param mvo
	 */
	void signUp(MemberVO mvo);

	/**
	 * 아이디 중복체크를 위한 메서드
	 * 
	 * @param mem_id
	 */
	boolean duplicate_id(String mem_id);

	/**
	 * 로그인을 위한 메서드
	 * 
	 * @param mem_id
	 *            ,mem_pw 가 담긴 map
	 * @return 회원 중 id와 pw가 일치하는 memberVO
	 * @author
	 */
	MemberVO logIn(Map<String, String> params);

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
	 * <b>회원 조회</b>
	 * @return MemberVO타입 List
	 * @author 윤홍식
	 * @param mvo
	 */
	List<MemberVO> memberRead();
	/**
	 * <b>회원 수정 SQL</b>
	 * @author 윤홍식
	 * 
	 */
	
	/**
	 * <b>회원 수정</b>
	 * 
	 * @author 윤홍식
	 * @return boolean(true/false)
	 * @param mem_id
	 */
	boolean memberUpdate(String mem_id);
	
	/**
	 * <b>회원 삭제</b>
	 * @author 윤홍식
	 * @return boolean(true/false)
	 * @param mem_id
	 */
	boolean memberDelete(String mem_id);
	
	/**
	 * <b>영화 등록</b>
	 * @author 윤홍식
	 * @return Map<String, String><br>
	 * 			"movie_num", movie_num //영화번호
	 * @param params
	 */
	void movieCreate(Map<String,Object> params);
	
	/**
	 * <b>영화 조회</b>
	 * @author 윤홍식
	 * @return MovieVO타입 List
	 */
	List<MovieVO> movieRead();
	
	/**
	 * <b>영화 등록</b>
	 * @author 윤홍식
	 * @return Map<String, String><br>
	 * 			"movie_num", movie_num //영화번호
	 * @param params
	 */
	boolean movieUpdate(Map<String, Object> params);
	
	/**
	 * <b>영화 삭제</b>
	 * @author 윤홍식
	 * @return boolean(true/false)
	 * @param movie_num
	 */
	boolean movieDelete(int movie_num);
	
	/**
	 * <b>한줄평 등록</b>
	 * @author 윤홍식
	 * @return boolean(true/false)
	 * @param Map<String, String><br>
	 * 			"movie_num", movie_num //영화번호
	 * 			"member_id", member_id //회원아이디
	 */
	void commentCreate(Map<String,Object> params);
	
	/**
	 * 한줄평 조회를 위한 메서드
	 * @author 윤홍식
	 */
	List<CommentVO> commentRead();
	
	
	/**
	 * <b>한줄평 수정</b>
	 * @author 윤홍식
	 * @return boolean(true/false)
	 * @param Map<String, String><br>
	 * 			"movie_num", movie_num //영화번호
	 * 			"member_id", member_id //회원아이디
	 */
	boolean commentUpdate(Map<String, Object> params);
	
	/**
	 * 한줄평 삭제를 위한 메서드
	 * @param comment_num
	 */
	boolean commentDelete(int comment_num);
	
	/**
	 * 영화(movie_num)에 대한 한줄평 조회하는 메서드
	 * @param movie_num
	 * @return
	 */
	List<MemberVO> memberReadSelect(String member_id);
	List<CommentVO> commentReadSelect(int movie_num);
	List<CommentVO> commentSelectForMovie(int movie_num);


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
	 * <b>영화관 등록</b><br>
	 * @param 지역(area)과 지점명(name)을 가진 map
	 * @return boolean(true/false)
	 * @author 정소미
	 */
	boolean cinemaCreate(Map<String, String> params);
	
	/**
	 * <b>영화관 조회</b><br>
	 * @return CinemaVO타입 List
	 * @author 정소미
	 */
	List<CinemaVO> cinemaRead();

	/**
	 * <b>영화관 삭제</b><br>
	 * @param cinema_num //영화관 번호
	 * @return boolean(true/false)
	 * @author 정소미
	 */
	boolean cinemaDelete(int cinema_num);
	
	 /**
	  * <b>영화관 수정</b><br>
	  * @param CinemaVO타입의 객체 cvo
	  * @return boolean(true/false)
	  * @author 정소미
	  */
	boolean cinemaUpdate(Map<String, Object> params);

	/**
	 * <b>상영관 등록</b><br>
	 * @param Map<String, Object><br>
	 *        "cinema_num", cinema_num //영화관번호<br>
	 *        "screen_name", screen_name //상영관이름<br>
	 * @return boolean(true/false)
	 * @author 정소미
	 */
	boolean screenCreate(Map<String, Object> params);
	
	/**
	 * <b>상영관 조회</b><br>
	 * @param cinema_num //영화관 번호
	 * @return ScreenVO타입 List
	 * @author 정소미
	 */
	List<ScreenVO> screenRead(int cinema_num);
	
	/**
	 * <b>상영관 삭제</b><br>
	 * @param cinema_num //영화관번호<br>
	 *        screen_num //상영관번호
	 * @return boolean(true/false)
	 * @author 정소미
	 */
	boolean screenDelete(int screen_num);
	
	/**
	 * <b>상영 등록</b><br>
	 * @param Map<String, String><br>
	 *        "cinema_num", cinema_num //영화관번호<br>
	 *        "screen_num", screen_num //상영관번호<br>
	 *        "show_num", show_num //상영번호
	 *        "movie_num", movie_num //영화번호
	 * @return boolean(true/false)
	 * @author 정소미
	 */
	boolean showCreate(Map<String, Object> params);

	
	
	/**
	 * 상영조회
	 * @author 정소미
	 * 
	 * 
	 * @param screen_num
	 * @return
	 */
	List<List<ShowVO>> showRead(int screen_num);
	
	
	
	
	
	
	
	/**
	 * <b>상영 삭제</b><br>
	 * @param cinema_num //영화관번호<br>
	 *        screen_num //상영관번호<br>
	 *        show_num //상영번호
	 * @return boolean(true/false)
	 * @author 정소미
	 */
	boolean showDelete(int show_num);
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
	 * <b>좌석 생성</b><br>
	 * : 상영관 번호를 외래키로 갖는 seatVO의 리스트인 List<seatVO> sl를 만듭니다.<br>
	 * List<seatVO> sl은 seat_row*seat_cols 만큼의 요소를 갖습니다.<br>
	 * 
	 * 성공적으로 리스트가 만들어졌다면 'true'를 만들어지지 않았다면 'false'를 반환합니다.
	 * 
	 * @param hashmap
	 *            <String, Integer> <br>
	 *            "screen_num" , screen_num <br>
	 *            "seat_row", seat_row <br>
	 *            "seat_cols", seat_cols
	 * 
	 * @author 이진영
	 */
	boolean seatCreate(Map<String, Integer> params);

	/**
	 * <b>좌석 조회</b><br>
	 * : 같은 상영관 번호(screen_num)를 갖는 List<SeatVO>를 반환합니다.
	 * 
	 * @return List<SeatVO>
	 * @author 이진영
	 */
	List<SeatVO> seatRead(int screen_num);

	/**
	 * <b>좌석 수정</b><br>
	 * :
	 * 
	 * @return
	 * @author 이진영
	 */
	boolean seatUpdate(Map<String, Integer> params);

	/**
	 * <b>좌석 삭제</b><br>
	 * :
	 * 
	 * HashMap<String, Integer> <br>
	 * "screen_num" , screen_num <br>
	 * "seat_row", seat_row <br>
	 * "seat_cols", seat_cols
	 * 
	 * 
	 * @param seat_num
	 * @return
	 * @author 이진영
	 */
	boolean seatDelete();

	/**
	 * <b>영화예매</b><br>
	 * : 좌석 번호와, 아이디를 받아 예매를 만듭니다. <br>
	 * 
	 * <hr>
	 * 내가 볼려고 보충 설명<br>
	 * - 좌석 번호를 가져오는 순서<br>
	 * 1) 영화관 조회 -> 변수에 영화관 번호를 받는다 -> 상영관 조회 -> 변수에 상영관 번호를 받는다.<br>
	 * 2) 영화조회 -> 변수에 영화 번호를 받는다.<br>
	 * 3) 1)2)의 결과를 통해 상영 조회를 한다.<br>
	 * 4) 상영을 선택한다.<br>
	 * 5) 상영에 포함된 좌석리스트의 좌석들을 출력해서 보여준다 ( '□' => 빈자리 '■' => 예매된 자리 )<br>
	 * 6) 상영의 좌석리스트의 seatVO.seat_use가 true면<br>
	 * "이미 예매된 좌석입니다. 다른 좌석을 선택해주세요"를 출력하고 좌석을 다시 선택<br>
	 * false면 "예매가 성공하였습니다" 출력후 종료
	 * 
	 * @param HashMap
	 *            <String, String><br>
	 *            "show_num",show_num "member_id",member_id
	 * 
	 * @return 영화예매가 성공했다면 true, 실패했다면 false를 반환합니다.
	 */
	boolean reserveCreate(Map<String, Object> params);
	
	/**
	 * 같은 상영관 번호를 공유하는 예약VO의 리스트입니다.
	 * @param showNum
	 * @return
	 */
	List<ReserveVO> reserveRead(int showNum);

	/**
	 * 예약리스트를 관리자는 모두 받아오고, 회원은 자기것만 받아오는 메서드입니다
	 * @author 이진영
	 * @return
	 */
	List<ReserveVO> memberReserveRead(Map<String, Object> params);
	
	/**
	 * memberReserveRead에서 받은 예약리스트의 영화이름,영화관,상영관,좌석번호를 받아오는리스트
	 * @param mmss
	 * @return
	 */
	Map<String, String> getReserveElement(int show_num);
	
	/**
	 * seat_num을 입력받아 정제된 좌석이름으로 바꿔주는 메소드
	 * @param seat_num
	 * @return
	 */
	String getColRow(int seat_num);
	/*
	 * /*************************************************************************
	 * ********************************************************
	 * 
	 * 황효진 : 자유게시판을 맡고 있습니다.
	 * 
	 * **************************************************************************
	 * *******************************************************
	 */
	
	   /*
	    * /*************************************************************************
	    * ********************************************************
	    * 
	    * 황효진 : 자유게시판을 맡고 있습니다.
	    * 
	    * **************************************************************************
	    * *******************************************************
	    */
	   
	   /**
	    * 저장하기
	    * */
	   boolean saved(FreeBoardVO board);
	   /**
	    * 게시글 가져오기
	    * */
	   List<FreeBoardVO> postRead();
	   /**
	    * 수정할 아이디 찾기
	    * */
	   List<FreeBoardVO> m_postRead(String member_id);
	   /**
	    * 수정할 멤버 문자열 아이디 찾기
	    * */
	   boolean checkPostAutho(Map<String, Object> params);
	   /**
	    * 현재 로그인 중인 멤버의 글만 모아서 리스트에 저장
	    * */
	   List<FreeBoardVO> FB_Read(String member_id);
	   




	


	



	

}
