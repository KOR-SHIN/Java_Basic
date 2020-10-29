<%@page import="kr.or.ddit.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberVo mv = (MemberVo)request.getAttribute("mv");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>아이디</td>
			<td><%=mv.getMemId() %>
		</tr>
		<tr>
			<td>이  름</td>
			<td><%=mv.getMemName() %>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=mv.getMemHp() %>
		</tr>
		<tr>
			<td>주  소</td>
			<td><%=mv.getMemAdd() %>
		</tr>
		<tr>
			<td colspan="2">
				<a href="<%=request.getContextPath()%>/listViewMember">[회원목록]</a>
				<a href="<%=request.getContextPath()%>/updateMember?memId=<%=mv.getMemId()%>">[정보수정]</a>
				<a href="<%=request.getContextPath()%>/deleteMember?memId=<%=mv.getMemId()%>">[회원탈퇴]</a>
			</td>
		</tr>
	</table>
</body>
</html>