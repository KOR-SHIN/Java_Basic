package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;

@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String memId = req.getParameter("memId");
			
			MemberService service = MemberService.getInstance();
			MemberVo mv = (MemberVo)service.getMember(memId);
			
			req.setAttribute("mv", mv);
			
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/member/updateMember.jsp");
			disp.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MemberVo mv = new MemberVo();
			mv.setMemAdd(req.getParameter("memAdd"));
			mv.setMemHp(req.getParameter("memHp"));
			mv.setMemId(req.getParameter("memId"));
			mv.setMemName(req.getParameter("memName"));
			
			MemberService service = MemberService.getInstance();

			String msg = (service.updateMember(mv) > 0) ? "수정성공" : "수정실패";
			
			String url = req.getContextPath() + "/listViewMember?msg=" + msg;
			resp.sendRedirect(url);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
