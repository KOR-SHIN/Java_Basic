package package_VO;
	/**
	 * 상영관VO
	 * @author PC-13
	 * 
	 */
public class ScreenVO {
	private int screen_num; //기본키
	private String screen_name;
	private int cinema_num; // 왜래키 영화관번호
	private static int screen_sq = 0 ; // 시퀀스 번호
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
	
	//시퀀스 초기화
	{
		screen_num = screen_sq;
		screen_sq++;
	}
	
	// get영역
	public int getScreen_num() {
		return screen_num;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public int getCinema_num() {
		return cinema_num;
	}

	// set영역


	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public void setCinema_num(int cinema_num) {
		this.cinema_num = cinema_num;
	}
	
}
