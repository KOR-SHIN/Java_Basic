package kr.or.ddit.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.AtchFileVo;

public class AtchFileDaoImpl implements IAtchFileDao {
	
	private static IAtchFileDao dao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(dao == null) {
			dao = new AtchFileDaoImpl();
		}
		return dao;
	}

	@Override
	public List<AtchFileVo> getAtchFileList(long atchFilId) throws SQLException {
		
		List<AtchFileVo> fileList = smc.queryForList("atchFile.getAtchFileList", atchFilId);
		
		return fileList;
	}

	@Override
	public int insertAtchFile(AtchFileVo atchFileVo) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("atchFile.insertAtchFile", atchFileVo);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public AtchFileVo getAtchFile(AtchFileVo atchFileVo) throws SQLException {
		AtchFileVo fileVo = (AtchFileVo) smc.queryForObject("atchFile.getAtchFile", atchFileVo);
		
		return fileVo;
	}

	
}
