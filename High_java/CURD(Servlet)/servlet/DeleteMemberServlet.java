package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String memId = req.getParameter("memId");
			
			MemberService service = MemberService.getInstance();
			
			String msg = (service.deleteMember(memId) > 0) ? "탈퇴성공" : "탈퇴실패";
			String url = req.getContextPath() + "/listViewMember?msg=" + msg;
			
			resp.sendRedirect(url);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
