package package_dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import package_util.IbatisUtil;
import package_vo.NoticeVO;

public class NoticeDaoImpl implements INoticeDao{
	
	//////////////////////////////////////////////////////////////////
	private static INoticeDao obj;
	private SqlMapClient smc;
	
	private NoticeDaoImpl() { 
		smc = IbatisUtil.getInstance();
	}
	
	public static INoticeDao getInstance() {
		if(obj == null) { obj = new NoticeDaoImpl(); }
		return obj;
	}
	//////////////////////////////////////////////////////////////////
	
	@Override
	public Object insertNotice(NoticeVO notice) {
		Object res = new Object();
		try {
			res = smc.insert("noticeTest.insertNotice", notice);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return res;
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		int res = 0;
		try {
			res = smc.update("noticeTest.updateNotice", notice);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return res;
	}

	@Override
	public int deletcNotice(String boardNo) {
		int res = 0;
		try {
			res = smc.delete("noticeTest.deleteNotice", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<NoticeVO> displayNoticeAll() {
		List<NoticeVO> noticeList = null;
		try {
			noticeList = smc.queryForList("noticeTest.displayNoticeAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	@Override
	public Object getNotice(String boardNo) {
		Object obj = null;
		try {
			obj = smc.queryForObject("noticeTest.getNotice", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return obj;
	}
	
	@Override
	public List<NoticeVO> searchNotice(NoticeVO notice) {
		List<NoticeVO> noticeList = new ArrayList<>();
		try {
			noticeList = smc.queryForList("noticeTest.searchNotice", notice);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}

}
