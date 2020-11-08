<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.vo.PagingVo"%>
<%@page import="kr.or.ddit.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVo> boardList = (List<BoardVo>) request.getAttribute("boardList");
	PagingVo pv = (PagingVo) request.getAttribute("pv");
	int size = boardList.size();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>게시판 목록</title>
</head>
<body>

<div class="container">
	<h2>[ 게시판 목록 ]</h2>
	<div align="right">
	  <a href="<%=request.getContextPath()%>/board/insert.do"><button type="button" class="btn btn-success">게시물 등록</button></a>
	</div>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>작성날짜</th>
        <th>작성자</th>
        <th>게시글 제목</th>
      </tr>
    </thead>
    
    <tbody>
<%
	if(size > 0) {
		for(int index = 0; index < size; index++) {
%>
		<tr>
			<td style="width:20%"><%=sdf.format(boardList.get(index).getBoardDate())%></td>
			<td style="width:20%"><%=boardList.get(index).getBoardWriter() %></td>
			<td style="width:60%"><a href="<%=request.getContextPath()%>/board/select.do?boardNo=<%=boardList.get(index).getBoardNo()%>"><%=boardList.get(index).getBoardTitle() %></a></td>
		</tr>
<%		
		}
%>
		<%if(pv.getTotalCount() > 0) { %>
		<tr>
			<td colspan='4' align='center'>
				<%if(pv.getFirstPageNo() > pv.getPageCount()) { %>
					<a href="<%=request.getContextPath() %>/board/list.do?pageNo=<%=pv.getFirstPageNo() - pv.getPageCount() %>">[이전]</a>
				<%} %>
				<%for(int pNo = pv.getFirstPageNo(); pNo <= pv.getLastPageNo(); pNo++) { %>
					<a href="<%=request.getContextPath() %>/board/list.do?pageNo=<%=pNo %>">[<%=pNo %>]</a>
				<%} %>
				<%if(pv.getLastPageNo() < pv.getTotalCount()) { %>
					<a href="<%=request.getContextPath() %>/board/list.do?pageNo=<%=pv.getFirstPageNo() + pv.getPageCount() %>">[다음]</a>
				<%} %>
			</td>
		</tr>
	<%} %>

<%
	}else {
%>
		<tr>
			<td colspan="3" align="center">회원 정보가 없습니다.</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
			<%if(pv.getFirstPageNo() > pv.getPageCount()) { %>
					<a href="<%=request.getContextPath() %>/board/list.do?pageNo=<%=pv.getFirstPageNo() - pv.getPageCount() %>">[이전]</a>
				<%} %>
			</td>
		<tr>
<% 
	}
%>
    </tbody>
  </table>
</div>
</body>
</html>