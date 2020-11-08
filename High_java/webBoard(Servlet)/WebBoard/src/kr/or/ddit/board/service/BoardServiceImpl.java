package kr.or.ddit.board.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PagingVo;

public class BoardServiceImpl implements IBoardService{
	private IBoardDao boardDao;
	private static IBoardService obj;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(obj == null) 
			obj = new BoardServiceImpl();
		return obj;
	}
	///////////////////////////////////////////////////////////////////

	@Override
	public int insertBoard(BoardVo bv) throws Exception {
		int ret = 0;
		try {
			ret = boardDao.insertBoard(bv);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int updateBoard(BoardVo bv) throws Exception {
		int ret = 0;
		try {
			ret = boardDao.updateBoard(bv);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int deleteBoard(String boardNo) throws Exception {
		int ret = 0;
		try {
			ret = boardDao.deleteBoard(boardNo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public BoardVo selectBoard(String boardNo) throws Exception {
		BoardVo bv = null;
		try {
			bv = boardDao.selectBoard(boardNo);
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return bv;
	}

	@Override
	public List<BoardVo> selectAllBoardByPaging(PagingVo pv) throws Exception {
		List<BoardVo> boardList = new ArrayList<>();
		try {
			boardList = boardDao.selectAllBoardByPaging(pv);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int getTotalCount() throws Exception {
		int ret = 0;
		try {
			ret = boardDao.getTotalCount();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
