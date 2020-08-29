package package_VO;
import java.util.Calendar;
import java.util.Date;

/**
 * 상영VO
 * @author PC-13
 *
 */
public class ShowVO {
	private int show_num;  
	private Calendar show_time;
	private int movie_num; // 외래키 영화번호
	private int screen_num; // 외래키 상영관번호
	private static int show_sq = 0 ; // 시퀀스 번호
	private boolean isDeleted; // 삭제여부
	
	
	
	/**
	 * 삭제설정 메서드
	*/
	public void setDeleted() {
		this.isDeleted = !isDeleted;
	}
	
	public boolean getDeleted() {
		return isDeleted;
	}
	
	// 시퀀스 초기화
	{
		show_num = show_sq; 
		show_sq++;
	}
	
	// get 영역
	public int getShow_num() {
		return show_num;
	}


	public Calendar getShow_time() {
		return show_time;
	}

	public int getMovie_num() {
		return movie_num;
	}

	public int getScreen_num() {
		return screen_num;
	}
	
	// set 영역

	public void setShow_time(Calendar show_time) {
		this.show_time = show_time;
	}

	public void setScreen_num(int screen_num) {
		this.screen_num = screen_num;
	}

	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}
	
	
	
	
}
