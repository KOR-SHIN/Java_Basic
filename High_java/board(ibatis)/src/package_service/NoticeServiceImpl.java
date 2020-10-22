package package_service;

import java.util.List;

import package_dao.INoticeDao;
import package_dao.NoticeDaoImpl;
import package_vo.NoticeVO;

public class NoticeServiceImpl implements INoticeService {
	
	//////////////////////////////////////////////////////////////////
	private static INoticeService obj;
	
	private NoticeServiceImpl() { }
	
	public static INoticeService getInstance() {
		if(obj == null) { obj = new NoticeServiceImpl(); }
		return obj;
	}
	//////////////////////////////////////////////////////////////////
	
	private INoticeDao dao = NoticeDaoImpl.getInstance();
	
	@Override
	public Object insertNotice(NoticeVO notice) {
		return dao.insertNotice(notice);
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		return dao.updateNotice(notice);
	}

	@Override
	public int deletcNotice(String board_no) {
		return dao.deletcNotice(board_no);
	}

	@Override
	public List<NoticeVO> displayNoticeAll() {
		return dao.displayNoticeAll();
	}

	@Override
	public List<NoticeVO> searchNotice(NoticeVO notice) {
		return dao.searchNotice(notice);
	}

	@Override
	public Object getNotice(String board_no) {
		return dao.getNotice(board_no);
	}

}
