<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertMember" method="post">
		
		아이디 : 
		<input type="text" name="memId" value="">
		<br>
		
		이름 : 
		<input type="text" name="memName" value="">
		<br>
		
		전화번호 : 
		<input type="text" name="memHp" value="">
		<br>
		
		주소 : 
		<textarea name="memAdd"></textarea>
		<br>
		
		<button type="submit">등록하기</button>
	</form>
</body>
</html>