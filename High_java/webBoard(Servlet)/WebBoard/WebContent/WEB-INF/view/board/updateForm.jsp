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
<title>게시글 작성</title>
<script type="text/javascript">
	function goListView() {
		location.href = "<%=request.getContextPath()%>/board/list.do";
	}
</script>
</head>
<body>
	<div class="container">
	<h2>[ 게시글 작성 ]</h2>
		<form id="updateForm" enctype="multipart/form-data" action="update.do" method="post">
			<div class="form-group">
				<label for="boardTitle">게시글 제목</label> 
				<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="<%=bv.getBoardTitle()%>" >
			</div>
			
			<div class="form-group">
				<label for="boardWriter">게시글 작성자</label> 
				<input type="text" class="form-control" id="boardWriter" name="boardWriter"  value="<%=bv.getBoardWriter()%>" readonly>
			</div>

			<div class="form-group">
				<label for="boardDate">게시글 생성날짜</label> 
				<input type="text" class="form-control" id="boardDate" name="boardDate" value="<%=sdf.format(bv.getBoardDate()) %>" readonly>
			</div>

			<div class="form-group">
				<label for="boardContent">게시글 내용</label>
				<textarea class="form-control" rows="5" id="boardContent" name="boardContent" ><%=bv.getBoardContent() %></textarea>
			</div>
			
			<div class="form-group">
				첨부파일
				<input type="file" name="atchFile">
				<br>
				기존 첨부파일
				<%if(fileVo != null) { %>
					<a href="<%=request.getContextPath()%>/filedownload.do?fileId=<%=fileVo.getAtchFileId()%>&fileSn=<%=fileVo.getFileSn()%>">[ <%=fileVo.getOrignlFileNm() %> ]</a>
				<%} %>
			</div>
			
			<!-- 게시판의 테이블의 기본키를 hidden 타입으로 저장해놓기 -->
			<input type="hidden" name="boardNo" value="<%=bv.getBoardNo()%>">
			
			<div align="center">
	  			<button type="submit" class="btn btn-success">수정완료</button>
	  			<button type="button" class="btn btn-warning" onclick="location.href = '<%=request.getContextPath()%>/board/list.do'">돌아가기</button>
			</div>
		</form>
	</div>
</body>
</html>