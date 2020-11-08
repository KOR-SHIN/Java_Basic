package kr.or.ddit.common.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import org.apache.commons.fileupload.FileItem;
import kr.or.ddit.common.dao.AtchFileDaoImpl;
import kr.or.ddit.common.dao.IAtchFileDao;
import kr.or.ddit.util.FileUploadRequestWrapper;
import kr.or.ddit.vo.AtchFileVo;

public class AtchFileServiceImpl implements IAtchFileService {

	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}	
		return fileService;
	}
	
	@Override
	public AtchFileVo saveAtchFile(FileItem item) throws Exception {
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		//전체 경로를 제외한 파일명만 추출하기
		String orignFileName = new File(item.getName()).getName();
		long fileSize = item.getSize(); // 파일 사이즈 가져오기
		String storeFileName = "";
		String filePath ="";
		File storeFile = null;
		
		do {
			//저장 파일명 추출
			storeFileName = UUID.randomUUID().toString().replace("-", "");
			filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY
					+ File.separator + storeFileName;
			storeFile = new File(filePath);
		}while(storeFile.exists()); //파일명이 중복되지않을 때까지
		
		//확장자 이름 추출
		String fileExtension = orignFileName.lastIndexOf(".") < 0 ? 
				"" : orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
		item.write(storeFile); // 업로드 파일 저장
		
		// 파일정보 저장
		AtchFileVo atchFileVO = new AtchFileVo();
		atchFileVO.setStreFileNm(storeFileName);
		atchFileVO.setFileSize(fileSize);
		atchFileVO.setOrignlFileNm(orignFileName);
		atchFileVO.setFileExtsn(fileExtension);
		atchFileVO.setFileStreCours(filePath);
		
		fileDao.insertAtchFile(atchFileVO);
		
		item.delete();
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVo> getAtchFileList(long atchFileId) throws Exception {
		List<AtchFileVo> fileVOList = fileDao.getAtchFileList(atchFileId);
		return fileVOList;
	}

	@Override
	public AtchFileVo getAtchFile(AtchFileVo atchFileVO) throws Exception {
		AtchFileVo fileVO = fileDao.getAtchFile(atchFileVO);
		return fileVO;
	}


}
