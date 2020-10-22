package kr.or.ddit.service;

import java.util.List;
import kr.or.ddit.dao.IMemberDao;
import kr.or.ddit.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{

	IMemberDao dao;
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl(); 
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		return dao.insertMember(mv);
	}

	@Override
	public int deleteMember(String mem_id) {
		return dao.deleteMember(mem_id);
	}

	@Override
	public int updateMember(MemberVO mv) {
		return dao.updateMember(mv);
	}

	@Override
	public List<MemberVO> displayAllMember() {
		return dao.displayAllMember();
	}

	@Override
	public boolean getMember(String mem_id) {
		return dao.getMember(mem_id);
	}
	
	public void reservation(MemberVO mv) {
		// 좌석예약을 위한 비즈니스 로직 예시 (transection 단위??)
		
		// 좌석선택 (좌선선택 DAO 호출)
		
		// 계좌이체 기능 (이체관련 DAO 호출)
		
		// 사용자에게 메일 발송
		
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		return dao.getSearchMember(mv);
	}


}
