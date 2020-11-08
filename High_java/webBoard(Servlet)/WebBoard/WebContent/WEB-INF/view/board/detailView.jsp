<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.vo.AtchFileVo"%>
<%@page import="kr.or.ddit.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	BoardVo bv = (BoardVo) request.getAttribute("bv");
	AtchFileVo fileVo = (AtchFileVo) request.getAttribute("fileVo");
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
<title>게시글 상세보기</title>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container">
	<h2>[ 게시글 상세보기 ]</h2>
		<form id="updateForm" enctype="multipart/form-data" action="update.do" method="post">
			<div class="form-group">
				<label for="boardTitle">게시글 제목</label> 
				<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="<%=bv.getBoardTitle()%>" readonly>
			</div>
			
			<div class="form-group">
				<label for="boardWriter">게시글 작성자</label> 
				<input type="text" class="form-control" id="boardWriter" name="boardWriter"  value="<%=bv.getBoardWriter()%>"readonly>
			</div>

			<div class="form-group">
				<label for="boardDate">게시글 생성날짜</label> 
				<input type="text" class="form-control" id="boardDate" name="boardDate" value="<%=sdf.format(bv.getBoardDate()) %>" readonly>
			</div>

			<div class="form-group">
				<label for="boardContent">게시글 내용</label>
				<textarea class="form-control" rows="5" id="boardContent" name="boardContent" readonly><%=bv.getBoardContent() %></textarea>
			</div>
			
			<div class="form-group">
				첨부파일
				<%if(fileVo != null) { %>
					<a href="<%=request.getContextPath()%>/filedownload.do?fileId=<%=fileVo.getAtchFileId()%>&fileSn=<%=fileVo.getFileSn()%>">[ <%=fileVo.getOrignlFileNm() %> ]</a>
				<%} %>
			</div>
			
			<div align="center">
	  			<a href="<%=request.getContextPath()%>/board/update.do?boardNo=<%=bv.getBoardNo()%>"><button type="button" class="btn btn-success">게시물 수정</button></a>
	  			<button type="button" class="btn btn-warning" onclick="location.href = '<%=request.getContextPath()%>/board/list.do'">돌아가기</button>
	  			<a href="<%=request.getContextPath()%>/board/delete.do?boardNo=<%=bv.getBoardNo()%>"><button type="button" class="btn btn-danger">삭제하기</button></a>
			</div>
		</form>
	</div>
</body>
</html>