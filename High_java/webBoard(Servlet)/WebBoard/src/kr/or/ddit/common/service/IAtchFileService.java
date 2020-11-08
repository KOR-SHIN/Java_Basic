package kr.or.ddit.common.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import kr.or.ddit.vo.AtchFileVo;

public interface IAtchFileService {
	
	/**
	 * 첨부파일을 저장하기 위한 메서드
	 * @param fileItem 저장할 객체
	 * @return 저장한 첨부파일 정보
	 * @throws Exception
	 */
	public AtchFileVo saveAtchFile(FileItem fileItem) throws Exception;
	
	/**
	 * 첨부파일을 정보 조회
	 * @param AtchFileVo 객체
	 * @return 첨부파일 정보
	 * @throws Exception
	 */
	public AtchFileVo getAtchFile(AtchFileVo atchFileVo) throws Exception;
	
	/**
	 * 첨부파일 아이디로 첨부파일 목록 정보 조회
	 * @param atchFileId 첨부파일 ID
	 * @return 첨부파일 목록 정보
	 * @throws Exception
	 */
	public List<AtchFileVo> getAtchFileList(long atchFileId) throws Exception;
}
