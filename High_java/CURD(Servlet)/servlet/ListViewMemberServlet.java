package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;

@WebServlet("/listViewMember")
public class ListViewMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String msg = req.getParameter("msg");
			msg = (msg == null) ? "" : req.getParameter("msg");
			
			MemberService service = MemberService.getInstance();
			List<MemberVo> memberList = service.getAllMember();
			
			req.setAttribute("memberList", memberList);
			req.setAttribute("msg", msg);
			
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/member/listViewMember.jsp");
			disp.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}

