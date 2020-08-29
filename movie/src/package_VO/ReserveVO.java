package package_VO;

/**
 * 예매VO
 * 
 * @author PC-13
 *
 */
public class ReserveVO {
	private int reserve_num; // 기본키
	private boolean reserve_use;
	private int reserve_money = 10000;
	private int show_num;// 외래키 상영번호
	private String member_id;// 외래키 회원아이디
	private int seat_num; // 외래키 좌석번호

	public int getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}

	private static int reserve_sq = 0; // 시퀀스 번호
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
		reserve_num = reserve_sq;
		reserve_sq++;
	}

	// get영역
	public int getReserve_num() {
		return reserve_num;
	}

	public boolean isReserve_use() {
		return reserve_use;
	}

	public int getReserve_money() {
		return reserve_money;
	}

	public int getShow_num() {
		return show_num;
	}

	public String getMember_id() {
		return member_id;
	}

	// set영역
	public void setReserve_use(boolean reserve_use) {
		this.reserve_use = reserve_use;
	}

	public void setReserve_money(int reserve_money) {
		this.reserve_money = reserve_money;
	}

	public void setShow_num(int show_num) {
		this.show_num = show_num;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}