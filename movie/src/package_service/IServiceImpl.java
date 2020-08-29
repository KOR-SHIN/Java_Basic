package package_service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import package_Database.Database;
import package_VO.CinemaVO;
import package_VO.CommentVO;
import package_VO.FreeBoardVO;
import package_VO.MemberVO;
import package_VO.MovieVO;
import package_VO.ReserveVO;
import package_VO.ScreenVO;
import package_VO.SeatVO;
import package_VO.ShowVO;

public class IServiceImpl implements IService {
	private Database db = Database.getDatabase();
	

	@Override
	public void signUp(MemberVO mvo) {
		db.signUp(mvo);
	}

	@Override
	public boolean duplicate_id(String mem_id) {
		return db.duplicate_id(mem_id);
	}

	@Override
	public MemberVO logIn(Map<String, String> params) {
		return db.logIn(params);
	}

	/*/*********************************************************************************************************************************
	 * 
	 * 윤홍식
	 * : 회원관리와 영화관리,한줄평을 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	@Override
	public List<MemberVO> memberRead() {
		return db.memberRead();
	}

	@Override
	public boolean memberUpdate(String mem_id) {
		return false;
	}

	@Override
	public boolean memberDelete(String mem_id) {
		return db.memberDelete(mem_id);
	}

	@Override
	public void movieCreate(Map<String, Object> params) {
		db.movieCreate(params);
	}

	@Override
	public List<MovieVO> movieRead() {
		// TODO Auto-generated method stub
		return db.movieRead();
	}

	@Override
	public boolean movieUpdate(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean movieDelete(int movie_num) {
		return db.movieDelete(movie_num);
	}

	@Override
	public void commentCreate(Map<String, Object> params) {
		db.commentCreate(params);
	}

	@Override
	public List<CommentVO> commentRead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean commentUpdate(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commentDelete(int comment_num) {
		return db.commentDelete(comment_num);
	}

	@Override
	public List<CommentVO> commentReadSelect(int movie_num) {
		return db.commentReadSelect(movie_num);
	}
	
	@Override
	public List<MemberVO> memberReadSelect(String member_id) {
		return db.memberReadSelect(member_id);
	}
	
	@Override
	public List<CommentVO> commentSelectForMovie(int movie_num) {
		return db.commentSelectForMovie(movie_num);
	}
	


	

	
	/*/*********************************************************************************************************************************
	 * 
	 * 정소미
	 * : 영화관 관리, 상영관 관리, 상영 관리를 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	
	@Override
	public boolean cinemaCreate(Map<String, String> params) {
		return db.cinemaCreate(params);
	}

	@Override
	public List<CinemaVO> cinemaRead() {
		return db.cinemaRead();
	}

	@Override
	public boolean cinemaDelete(int cinema_num) {
		return db.cinemaDelete(cinema_num);
	}
	
	@Override
	public boolean cinemaUpdate(Map<String, Object> params) {
		return db.cinemaUpdate(params);
	}

	@Override
	public boolean screenCreate(Map<String, Object> params) {
		return db.screenCreate(params);
	}

	@Override
	public List<ScreenVO> screenRead(int cinema_num) {
		return db.screenRead(cinema_num);
	}

	

	@Override
	public boolean showCreate(Map<String, Object> params) {
		return db.showCreate(params);
	}


	
	
	
	
	
	@Override
	public boolean showDelete(int cinema_num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean screenDelete(int screen_num) {
		return db.screenDelete(screen_num);
	}

	/**
	 * @author 정소미
	 */
	@Override
	public List<List<ShowVO>> showRead(int screen_num) {
		// TODO Auto-generated method stub
		return db.showRead(screen_num);
	}


	
	/*/*********************************************************************************************************************************
	 * 
	 * 이진영
	 * : 좌석관리와 예매관리를 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	
	
	
	
	@Override
	public List<SeatVO> seatRead(int screen_num) {
		return db.seatRead(screen_num);
	}


	@Override
	public boolean seatCreate(Map<String, Integer> params) {
		return db.seatCreate(params);
	}

	@Override
	public boolean seatUpdate(Map<String, Integer> params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean seatDelete() {
		// TODO Auto-generated method stub
		return false;
	}



	
	@Override
	public List<ReserveVO> reserveRead(int showNum) {
		return db.reserveRead(showNum);
	}

	
	@Override
	public boolean reserveCreate(Map<String, Object> params) {
		return db.reserveCreate(params);
	}
	
	@Override
	public List<ReserveVO> memberReserveRead(Map<String, Object> params) {
		return db.memberReserveRead(params);
	}
	
	
	@Override
	public Map<String, String> getReserveElement(int show_num) {
		return db.getReserveElement(show_num);
	}

	
	@Override
	public String getColRow(int seat_num) {
		return db.getColRow(seat_num); 
	}
	/*/*********************************************************************************************************************************
	 * 
	 * 황효진
	 * : 자유게시판을 맡고 있습니다.
	 * 
	 * *********************************************************************************************************************************
	 */
	/**
	 * @author 황효진
	 * 게시판 저장하기
	 * */
	@Override
	public boolean saved(FreeBoardVO board) {
		return db.saved(board);
	}
	/**
	 * @author 황효진
	 * 게시판 조회
	 * */
	@Override
	public List<FreeBoardVO> postRead() {
		return db.postRead();
	}
	 /**
	    * @author 황효진
	    * 멤버리스트 조회
	    * */
	   @Override
	   public List<FreeBoardVO> m_postRead(String member_id) {
	      // TODO Auto-generated method stub
	      return db.m_postRead(member_id);
	   }

	   @Override
	   public boolean checkPostAutho(Map<String, Object> params) {
	      // TODO Auto-generated method stub
	      return false;
	   }

	   @Override
	   public List<FreeBoardVO> FB_Read(String member_id) {
	      // TODO Auto-generated method stub
	      return null;
	   }





	
	
}
