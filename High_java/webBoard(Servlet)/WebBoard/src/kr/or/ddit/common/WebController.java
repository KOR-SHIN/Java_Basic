package kr.or.ddit.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.sun.istack.internal.logging.Logger;

import kr.or.ddit.common.handler.CommonHandler;
import kr.or.ddit.board.handler.NullHandler;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class WebController extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(WebController.class);
	
	// 매핑정보를 저장(핸들러 객체 저장용 맵)
	private Map<String, CommonHandler> commandHandlerMap = new HashMap<String, CommonHandler>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// handler-config라는 이름의 init parameter를 읽어온다
		String configFilePath = config.getInitParameter("handler-config");
		
		// propertise객체 생성
		Properties handlerProp = new Properties();
		
		// 설정파일을 읽어서 대응되는 핸들러 객체를 생성하여 맵에 등록하기
		// FileReader로 읽어오기 위해 realPath를 사용하여 물리주소를 얻는다
		String ConfigFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		try {
			fr = new FileReader(ConfigFileRealPath);
			handlerProp.load(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		 for(Object key : handlerProp.keySet()) {
			 // key => 사용자의 요청 url패턴
			 String command = (String) key;
			 
			try {
				 // 사용자의 url패턴에 대응되는 클래스 
				 Class<?> klass = Class.forName(handlerProp.getProperty(command));
				
				 // 사용자의 url패턴에 대응되는 클래스의 객체 생성
				 CommonHandler handlerInstance = (CommonHandler) klass.newInstance();
				 
				 // key => url패턴, value => 클래스객체
				 commandHandlerMap.put(command, handlerInstance);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		 }
		 logger.info("CommandHandler 매핑정보\n => " + commandHandlerMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 파일업로드를 위한 요청일 수 있기때문에 enctype을 검사하여 Wrapper클래스를 사용할지 검사한다.
			FileUploadRequestWrapper requestWrapper = new FileUploadRequestWrapper(req);
			process(requestWrapper, resp);  //doget, dopost 다 process로
	      
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String command = req.getRequestURI();
		System.out.println(" 전 command =>" + command);
		
		// ContextPath()는 변경될 수 있기때문에 ContextPath에 해당하는 주소는
		// 제외하고 url 패턴을 추출
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		System.out.println(" 후 command =>" + command);
		
		CommonHandler handler = commandHandlerMap.get(command);
		
		// 사용자의 url패턴이 미리 정의해놓은 url패턴에 없다면 
		// NullHandler로 처리한다.
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = ""; //뷰 화면 정보
		try {
			viewPage = handler.process(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		logger.info("이동하는 페이지 =>" + viewPage);
		
		// VIEW 화면 처리
		if(viewPage != null) { //view 정보가 존재하면
			if(handler.isRedirect(req)) { //리다이렉트 처리가 필요하면
				resp.sendRedirect(viewPage);
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
	}
}
