package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 받아 
 * Controller에 전달하는 Service의 inferface
 * @author 신광진
 */
public interface IMemberService {
	
	/**
	 * MemberVO에 담긴 자료를 DB에 insert하는 메서드
	 * @param mv (DB에 insert할 자료를 저장한 MemberVO 객체)
	 * @return DB작업이 성공하면 1 이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param mem_id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String mem_id);
	
	/**
	 * 하나의 MemberVO객체를 이용하여 DB를 update하는 메서드
	 * @param mv (update할 회원 정보가 들어있는 MemberVO 객체)
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * DB의 MYMEMBER 테이블 전체 레코드를 List로 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> displayAllMember();
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param mem_id (select의 대상이 되는 회원ID)
	 * @return 회원존재 : true, 회원이 존재하지않음 : false
	 */
	public boolean getMember(String mem_id);
	
	/**
	 * MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv (검색할 자료가 담긴 MemberVO 객체)
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<MemberVO> getSearchMember(MemberVO mv);
	
	
	
	
	
	
}
