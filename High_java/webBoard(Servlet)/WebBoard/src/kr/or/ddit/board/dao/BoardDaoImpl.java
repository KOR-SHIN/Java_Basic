package kr.or.ddit.board.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PagingVo;

public class BoardDaoImpl implements IBoardDao {
	private SqlMapClient smc;
	private static IBoardDao obj;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(obj == null)
			obj = new BoardDaoImpl();
		return obj;
	}
	///////////////////////////////////////////////////////////////////

	@Override
	public int insertBoard(BoardVo bv) throws Exception {
		int ret = (smc.insert("board.insertBoard", bv) == null) ? 1 : 0;
		return ret;
	}

	@Override
	public int updateBoard(BoardVo bv) throws Exception {
		int ret = smc.update("board.updateBoard", bv);
		return ret;
	}

	@Override
	public int deleteBoard(String boardNo) throws Exception {
		int ret = smc.delete("board.deleteBoard", boardNo);
		return ret;
	}

	@Override
	public BoardVo selectBoard(String boardNo) throws Exception {
		BoardVo bv = (BoardVo) smc.queryForObject("board.selectBoard", boardNo);
		return bv;
	}

	@Override
	public List<BoardVo> selectAllBoardByPaging(PagingVo pv) throws Exception {
		List<BoardVo> boardList = (List<BoardVo>) smc.queryForList("board.selectAllBoardByPaging", pv);
		return boardList;
	}

	@Override
	public int getTotalCount() throws Exception {
		int ret = (int)smc.queryForObject("board.getTotalCount");
		return ret;
	}
}
