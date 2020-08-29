package package_VO;
import java.util.Calendar;
import java.util.Date;

/**
 * 회원VO
 * @author PC-13
 *
 */
public class MemberVO {

	private String member_id; //기본키
	private String member_pw; //회원비밀번호
	private String member_name; //회원이름
	private String member_phoneNum; //회원핸드폰번호
	private Calendar member_birth; //회원생일(연령제한)
	private int member_cash; //보유금액
	private boolean member_auth; //관리자여부(관리자는 true 회원은false)
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
	
	//멤버는 id로 기본키를 정했기때문에 따로 시퀀스 필요없음
	
	
	// get영역
	public String getMember_id() {
		return member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public String getMember_phoneNum() {
		return member_phoneNum;
	}
	public Calendar getMember_birth() {
		return member_birth;
	}
	public int getMember_cash() {
		return member_cash;
	}
	public boolean getMember_auth() {
		return member_auth;
	}
	
	//set영역
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public void setMember_phoneNum(String member_phoneNum) {
		this.member_phoneNum = member_phoneNum;
	}
	public void setMember_birth(Calendar member_birth) {
		this.member_birth = member_birth;
	}
	public void setMember_cash(int member_cash) {
		this.member_cash = member_cash;
	}
	public void setMember_auth(boolean member_auth) {
		this.member_auth = member_auth;
	}
	
	

	
	
	
	
	
	
	
}
