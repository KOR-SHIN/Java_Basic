package package_service;

import java.util.List;

import package_vo.NoticeVO;

public interface INoticeService {
	
	/**
	 * 새로운 게시글을 등록하는 메서드
	 * @param notice (등록하려는 게시글의 정보를 가진 noticeVO 객체)
	 * @return Object
	 */
	public Object insertNotice(NoticeVO notice);
	
	/**
	 * 기존의 게시글을 수정하는 메서드
	 * @param notice (수정할 게시글의 정보가 담긴 NoticeVO 객체)
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateNotice(NoticeVO notice);
	
	/**
	 * 기존의 게시글을 삭제하는 메서드
	 * @param notice_no (삭제의 대상이 되는 게시글의 번호)
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deletcNotice(String board_no);
	
	/**
	 * DB에 저장되어 있는 모든 게시글의 정보를 List객체로 반환해주는 메서드
	 * @return List<NoticeVO>
	 */
	public List<NoticeVO> displayNoticeAll();
	
	/**
	 * 회원이 찾고자 하는 게시글을 검색하는 메서드
	 * @param notice (검색의 대상이 되는 게시글에 대한 정보를 담은 NoticeVO 객체)
	 * @return List<NoticeVO>
	 */
	public List<NoticeVO> searchNotice(NoticeVO notice);
	
	public Object getNotice(String board_no);

}
