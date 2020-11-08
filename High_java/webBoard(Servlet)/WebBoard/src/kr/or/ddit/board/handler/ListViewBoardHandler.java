package kr.or.ddit.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.handler.CommonHandler;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PagingVo;

public class ListViewBoardHandler implements CommonHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/listView.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");

		// 1. 서비스 객체 생성하기
		IBoardService boardSv = BoardServiceImpl.getInstance();
		
		//2. 페이징 객체 생성
		PagingVo pv = new PagingVo();
		int totalCount = boardSv.getTotalCount();
		pv.setTotalCount(totalCount);
		pv.setCurrentPageNo(pageNo);
		pv.setCountPerPage(5);
		pv.setPageCount(5);
		
		//3. 회원정보 조회
		List<BoardVo> boardList = boardSv.selectAllBoardByPaging(pv);
		
		req.setAttribute("msg", msg);
		req.setAttribute("boardList", boardList);
		req.setAttribute("pv", pv);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

}
