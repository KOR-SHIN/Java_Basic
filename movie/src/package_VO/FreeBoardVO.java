package package_VO;
/**
 * 자유게시판VO
 * @author PC-13
 *
 */
public class FreeBoardVO {
	
	private int free_num; // 기본키
	private String free_date; // 글 날짜
	private String free_title; // 글 제목
	private String free_content; // 글 내용
	private int free_writeNum; // 글번호
	private int free_cnt; // 조회수
	private String member_id; // 외래키 회원 아이디
	private static int freeboard_sq = 0; //시퀀스 번호
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
		free_num = freeboard_sq;
		freeboard_sq++;
	}
	
	// get영역
	public int getFree_num() {
		return free_num;
	}

	public String getFree_date() {
		return free_date;
	}

	public String getFree_title() {
		return free_title;
	}

	public String getFree_content() {
		return free_content;
	}

	public int getFree_writeNum() {
		return free_writeNum;
	}

	public int getFree_cnt() {
		return free_cnt;
	}

	public String getMember_id() {
		return member_id;
	}
	
	// set영역
	public void setFree_date(String free_date) {
		this.free_date = free_date;
	}

	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}

	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}

	public void setFree_writeNum(int free_writeNum) {
		this.free_writeNum = free_writeNum;
	}

	public void setFree_cnt(int free_cnt) {
		this.free_cnt = free_cnt;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


}
