package package_VO;
/**
 * 영화관VO
 * @author PC-13
 *
 */
public class CinemaVO {
	private int cinema_num; //기본키
	private String cinema_area; //지역명
	private String cinema_name; //지점명
	private static int cinema_sq=0; // 시퀀스 번호
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
		cinema_num = cinema_sq;
		cinema_sq++;
	}
	
	// get영역
	public int getCinema_num() {
		return cinema_num;
	}

	public String getCinema_area() {
		return cinema_area;
	}

	public String getCinema_name() {
		return cinema_name;
	}
	
	// set영역
	public void setCinema_area(String cinema_area) {
		this.cinema_area = cinema_area;
	}

	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	
	
}
