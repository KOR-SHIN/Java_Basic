package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;

@WebServlet("/insertMember")
public class InsertMemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/member/insertMember.jsp");
		disp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MemberVo mv = new MemberVo();
			mv.setMemId(req.getParameter("memId"));
			mv.setMemName(req.getParameter("memName"));
			mv.setMemHp(req.getParameter("memHp"));
			mv.setMemAdd(req.getParameter("memAdd"));
			
			MemberService service = MemberService.getInstance();
			String msg = (service.insertMember(mv) == null) ? "등록성공" : "등록실패";
			
			String url = req.getContextPath() + "/listViewMember?msg=" + URLEncoder.encode(msg, "UTF-8");
			resp.sendRedirect(url);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
