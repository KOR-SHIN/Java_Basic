package kr.or.ddit.common.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.vo.AtchFileVo;

public class FileDownloadHandler implements CommonHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		long atchFileId = req.getParameter("fileId") != null 
				? Long.parseLong(req.getParameter("fileId")) : 0;
		int fileSn = req.getParameter("fileSn") != null 
				? Integer.parseInt(req.getParameter("fileSn")) : 0;
				
		// 파일 정보 조회
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVo paramVo = new AtchFileVo();
		paramVo.setAtchFileId(atchFileId);
		paramVo.setFileSn(fileSn);
		
		AtchFileVo atchFileVo = fileService.getAtchFile(paramVo);
		
		if(atchFileVo != null) {
			// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(atchFileVo.getOrignlFileNm(), "UTF-8") + "\"");
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(atchFileVo.getFileStreCours()));
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			int readBytes = 0;
			while((readBytes = bis.read()) != -1 ) {
				bos.write(readBytes);
			}
			bis.close();
			bos.close();
		}
		return null;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {

		return false;
	}

}
