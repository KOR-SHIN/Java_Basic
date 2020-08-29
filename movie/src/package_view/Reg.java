package package_view;

import java.util.regex.Pattern;


/**
 * 정규식 체크 클래스
 *
 */
public class Reg {

	// MemberVO 정규식 체크
	
	/**
	 * 아이디 체크 : 첫 글자 영문자로 시작 영어,숫자 5~12자리
	 * @param mem_id
	 * @return boolean
	 */
	public boolean id(String mem_id) {
		String reg_id = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
		return Pattern.matches(reg_id, mem_id);
	}

	/**
	 * 비밀번호 체크 : 영문(대소문자 구분), 숫자, 특수문자 조합
	 * @param mem_pw
	 * @return boolean
	 */
	public boolean password(String mem_pw) {
		String reg_pw = "[a-zA-Z0-9!@#$%^*+=-]{4,11}$";

		return Pattern.matches(reg_pw, mem_pw);
	}

	/**
	 * 이름 체크 : 한글로만
	 * @param mem_name
	 * @return boolean
	 */
	public boolean name(String mem_name) {
		String reg_name =  "^[가-힣]*$";
		return Pattern.matches(reg_name, mem_name);
	}
	
	/**
	 * 휴대폰 번호 체크 : 000-0000-0000 ('-'생략가능)
	 * @param mem_ph
	 * @return boolean
	 */
	public boolean phone(String mem_ph) {
		String reg_ph = "^01(?:0|1|[6-9])-?\\d{3,4}-?\\d{4}";
		return Pattern.matches(reg_ph, mem_ph);
	}

	/**
	 * 생일 체크
	 * @param mem_birth
	 * @return
	 */
	public boolean birth(String mem_birth) {
		String reg_birth = "^((19|20)\\d\\d)[/](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])";
		return Pattern.matches(reg_birth, mem_birth);
	}

	public boolean seatcode(String seat_code) {
		String reg_seatcode = "[A-Z][0-9]{1,3}";
		return Pattern.matches(reg_seatcode, seat_code);
	}

	


}
