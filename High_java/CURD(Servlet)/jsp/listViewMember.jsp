<%@page import="kr.or.ddit.member.vo.MemberVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<MemberVo> memberList = (List<MemberVo>)request.getAttribute("memberList");
	String msg = (String)request.getAttribute("msg");
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
			<td colspan="4"><a href="<%=request.getContextPath()%>/insertMember">[회원등록]</a></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>이  름</td>
			<td>전화번호</td>
			<td>주소</td>
		</tr>
<%
	if(memberList.size() > 0) {
		
		for(int i = 0; i < memberList.size(); i++ ) {
%>
		<tr>
			<td><%=memberList.get(i).getMemId() %></td>
			<td><a href="detailViewMember?memId=<%=memberList.get(i).getMemId()%>"><%=memberList.get(i).getMemName() %></a></td>
			<td><%=memberList.get(i).getMemHp() %></td>
			<td><%=memberList.get(i).getMemAdd() %></td>
		</tr>
<%
		}
	} else {
%>
		<tr>
			<td colspan="4">회원목록이 존재하지 않습니다.</td>
		</tr>
<%		
	}
%>		
	</table>
</body>
</html>