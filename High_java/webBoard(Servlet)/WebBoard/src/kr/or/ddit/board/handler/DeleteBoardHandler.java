package kr.or.ddit.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.handler.CommonHandler;

public class DeleteBoardHandler implements CommonHandler{

	Logger logger = Logger.getLogger(DeleteBoardHandler.class);
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// get, post 상관없음
		String boardNo = req.getParameter("boardNo");
		
		IBoardService boardSv = BoardServiceImpl.getInstance();
		int res = boardSv.deleteBoard(boardNo);
		
		logger.info("삭제결과 -> " + res);
		if(res == 0) {
			// 실패
		}
		
		String redirectUrl = req.getContextPath() + "/board/list.do";
		return redirectUrl;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

}
