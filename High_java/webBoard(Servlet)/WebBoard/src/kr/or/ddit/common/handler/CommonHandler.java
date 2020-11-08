package kr.or.ddit.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonHandler {

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public boolean isRedirect(HttpServletRequest req);
	
}
