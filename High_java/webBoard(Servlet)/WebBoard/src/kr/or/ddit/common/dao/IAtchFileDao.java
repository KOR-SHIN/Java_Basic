package kr.or.ddit.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapException;

import kr.or.ddit.vo.AtchFileVo;

public interface IAtchFileDao {
	/**
	 * 파일ID에 해당하는 첨부파일목록 조회
	 * @param atchFileId 파일ID
	 * @return 첨부파일목록
	 * @author PC-15
	 * @throws SQLExcepion
	 */
	public List<AtchFileVo> getAtchFileList(long atchFilId) throws SQLException;
	
	/**
	 * 파일ID에 해당하는 첨부파일 조회
	 * @param atchFileId 객체
	 * @return 첨부파일
	 * @author PC-15
	 * @throws SQLExcepion
	 */
	public AtchFileVo getAtchFile(AtchFileVo atchFileVo) throws SQLException;
	
	/**
	 * 
	 * @param atchFileVo
	 * @return
	 * @throws SqlMapException
	 */
	public int insertAtchFile(AtchFileVo atchFileVo) throws SQLException;
}
