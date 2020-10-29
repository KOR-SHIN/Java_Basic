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
	<form action="updateMember" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<%=mv.getMemId() %>
					<input type="hidden" name="memId" value="<%=mv.getMemId()%>">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="memName" value="<%=mv.getMemName()%>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="memHp" value="<%=mv.getMemHp() %>"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="memAdd" value="<%=mv.getMemAdd()%>"></td>
			</tr>
			<tr>
				<td><button type="submit">수정하기</button></td>
			</tr>
		</table>
	</form>	
</body>
</html>