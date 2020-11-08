package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PagingVo;

public interface IBoardService {

	// insert
	int insertBoard(BoardVo bv) throws Exception;
	
	// update
	int updateBoard(BoardVo bv) throws Exception;
	
	// delete
	int deleteBoard(String boardNo) throws Exception;
	
	// select
	BoardVo selectBoard(String boardNo) throws Exception;
	
	// selectAll(paging)
	List<BoardVo> selectAllBoardByPaging(PagingVo pv) throws Exception;
	
	// count Board Number
	int getTotalCount() throws Exception;

}
