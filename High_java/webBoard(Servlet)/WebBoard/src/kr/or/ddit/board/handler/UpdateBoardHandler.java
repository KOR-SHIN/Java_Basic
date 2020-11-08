package kr.or.ddit.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.sun.istack.internal.logging.Logger;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.handler.CommonHandler;
import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.util.FileUploadRequestWrapper;
import kr.or.ddit.vo.AtchFileVo;
import kr.or.ddit.vo.BoardVo;

public class UpdateBoardHandler implements CommonHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/updateForm.jsp";
	private IAtchFileService fileSv = AtchFileServiceImpl.getInstance();
	private Logger logger = Logger.getLogger(UpdateBoardHandler.class);
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			String boardNo = req.getParameter("boardNo");
			
			IBoardService boardSv = BoardServiceImpl.getInstance();
			BoardVo bv = boardSv.selectBoard(boardNo);
			
			IAtchFileService fileSv = AtchFileServiceImpl.getInstance();
			List<AtchFileVo> fileList = fileSv.getAtchFileList(bv.getAtchFileId());
			
			req.setAttribute("fileVo", fileList.get(0));
			req.setAttribute("bv", bv);
			
			logger.info("업데이터 요청 -> GET");
			return VIEW_PAGE;
			
		} else {
			logger.info("업데이터 요청 -> POST");
			
			FileItem item = ((FileUploadRequestWrapper) req).getFileItem("atchFile");

			AtchFileVo atchFileVo = new AtchFileVo();
			if (item != null && !item.getName().equals("")) {
					// 전송된 파일이 있으면 저장한다.
					atchFileVo = fileSv.saveAtchFile(item);
			}
			
			BoardVo bv = new BoardVo();
			bv.setBoardNo(req.getParameter("boardNo"));
			bv.setBoardTitle(req.getParameter("boardTitle"));
			bv.setBoardContent(req.getParameter("boardContent"));
			bv.setAtchFileId(atchFileVo.getAtchFileId());
			
			IBoardService boardSv = BoardServiceImpl.getInstance();
			int res = boardSv.updateBoard(bv);
			
			if(res == 0) {
				//실패
			}
			
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
