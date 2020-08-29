package package_VO;

/**
 * 좌석VO
 * 
 * @author PC-13
 * 
 */
public class SeatVO {

	private int seat_num; // 기본키
	private int seat_row; // 좌석 행
	private int seat_cols; // 좌석 열
	private int screen_num;// 외래키 상영관 번호
	
	private static int seat_sq = 0; // 시퀀스 번호
	private boolean seat_use; // 사용여부
	private boolean isDeleted; // 삭제여부
	
	// 사용여부
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
		seat_num = seat_sq;
		seat_sq++;
	}

	public SeatVO() {

	};

	// get영역
	public int getSeat_num() {
		return seat_num;
	}

	public boolean isSeat_use() {
		return seat_use;
	}

	public int getSeat_row() {
		return seat_row;
	}

	public int getSeat_cols() {
		return seat_cols;
	}


	public int getScreen_num() {
		return screen_num;
	}

	// set영역
	public void setSeat_use(boolean seat_use) {
		this.seat_use = seat_use;
	}

	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}

	public void setSeat_cols(int seat_cols) {
		this.seat_cols = seat_cols;
	}


	public void setScreen_num(int screen_num) {
		this.screen_num = screen_num;
	}

}
