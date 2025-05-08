<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h3>This page is SIGN_IN.</h3>
	<form action="/hello/signInConfirm">
		ID : <input type="text" name="m_id" /><br />
		PW : <input type="password" name="m_pw" /><br />
		<input type="submit" value="SIGN IN">
		<input type="reset" value="CANCEL">
	</form>

</body>
</html>