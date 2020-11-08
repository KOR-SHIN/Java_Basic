package kr.or.ddit.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.handler.CommonHandler;
import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.vo.AtchFileVo;
import kr.or.ddit.vo.BoardVo;

public class DetailViewBoardHandler implements CommonHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/detailView.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// get, post 상관없음
		
		String boardNo = req.getParameter("boardNo");
		
		IBoardService boardSv = BoardServiceImpl.getInstance();
		BoardVo bv = boardSv.selectBoard(boardNo);

		req.setAttribute("bv", bv);
		
		IAtchFileService fileSv = AtchFileServiceImpl.getInstance();
		List<AtchFileVo> fileList = fileSv.getAtchFileList(bv.getAtchFileId());
		
		if(fileList.size() > 0 ) {
			req.setAttribute("fileVo", fileList.get(0));
		}
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

}
