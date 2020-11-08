package kr.or.ddit.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.handler.CommonHandler;
import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.util.FileUploadRequestWrapper;
import kr.or.ddit.vo.AtchFileVo;
import kr.or.ddit.vo.BoardVo;

public class InsertBoardHandler implements CommonHandler {

	private static final String GET_VIEW_PAGE = "/WEB-INF/view/board/insertForm.jsp";
	private IAtchFileService fileSv = AtchFileServiceImpl.getInstance();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return GET_VIEW_PAGE;
		} else {
			// 화면에서 전송한 파일 파라미터 (name=atchFile)
			FileItem item = ((FileUploadRequestWrapper) req).getFileItem("atchFile");

			// 화면에서 전송한 atchFile이 있는지 검사한다
			AtchFileVo atchFileVo = new AtchFileVo();
			if (item != null && !item.getName().equals("")) {
				atchFileVo = fileSv.saveAtchFile(item); // 첨부파일 저장
			}

			// 1. 요청 파라미터 정보 가져오기
			String boardTitle = req.getParameter("boardTitle");
			String boardWriter = req.getParameter("boardWriter");
			String boardContent = req.getParameter("boardContent");
			
			// 2. 서비스 객체 생성하기
			IBoardService boardSv = BoardServiceImpl.getInstance();

			// 3. 회원정보 등록
			BoardVo bv = new BoardVo();
			bv.setBoardTitle(boardTitle);
			bv.setBoardWriter(boardWriter);
			bv.setBoardContent(boardContent);
			bv.setAtchFileId(atchFileVo.getAtchFileId());

			int res = boardSv.insertBoard(bv);
			
			String msg = "게시물 등록이 완료되었습니다. 목록화면으로 이동합니다.";
			if(res == 0) {
				msg = "게시물 등록을 실패하였습니다. 다시 시도해주세요.";
			}
			
			req.setAttribute("msg", msg);
			
			// 4. 목록 조회화면으로 이동
			String redirectUrl = req.getContextPath() + "/board/list.do";
			return redirectUrl;
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return false;
		} else {
			return true;
		}
	}
}
