package package_VO;
/**
 * 영화VO
 * @author 윤홍식
 *
 */
public class MovieVO {
	
	private int movie_num; //기본키
	private String movie_name;
	private int movie_ageGrade;//관람등급
	private int movie_runT;
	private String genre_name;
	private static int movie_sq = 0; //시퀀스 번호
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
		movie_num = movie_sq;
		movie_sq++;
	}
	
	// get영역
	public int getMovie_num() {
		return movie_num;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public int getMovie_ageGrade() {
		return movie_ageGrade;
	}

	public int getMovie_runT() {
		return movie_runT;
	}
	
	public String getGenre_name() {
		return genre_name;
	}
	

	// set영역
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	
	public void setMovie_runT(int movie_runT) {
		this.movie_runT = movie_runT;
	}
	
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	
	public void setMovie_ageGrade(int movie_ageGrade) {
		this.movie_ageGrade = movie_ageGrade;
	}
	
}
	