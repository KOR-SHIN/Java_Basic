<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form id="insertForm" enctype="multipart/form-data" action="insert.do" method="post">
			<div class="form-group">
				<label for="usr">게시글 제목</label> <input type="text" class="form-control" id="boardTitle" name="boardTitle">
			</div>
			
			<div class="form-group">
				<label for="pwd">게시글 작성자</label> <input type="text" class="form-control" id="boardWriter" name="boardWriter">
			</div>
			
			<div class="form-group">
				<label for="comment">게시글 내용</label>
				<textarea class="form-control" rows="5" id="boardContent" name="boardContent"></textarea>
			</div>
			
			<div class="form-group">
				첨부파일 <input type="file"  id="atchFile" name="atchFile">
			</div>
			
			<div align="center">
	  			<button type="submit" class="btn btn-success">게시물 등록</button>
	  			<button type="button" class="btn btn-warning" onclick="location.href = '<%=request.getContextPath()%>/board/list.do'">돌아가기</button>
			</div>
		</form>
	</div>
</body>
</html>