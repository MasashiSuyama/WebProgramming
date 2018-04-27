<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/origin/common.css" rel="stylesheet">
</head>
<body>
	<h1>ログイン画面</h1>
	<div class="container">
		<c:if test="${errMsg != null}" >
		    <div class="alert alert-danger">${errMsg}</div>
		</c:if>
		<div class="login-area">
			<form class="form-signin" action="LoginServlet" method="post">
				<div class="form-group row">
					<label for="ID" class="size col-sm-3">ログインID</label>
					<input type="text" name="loginId" id="ID" class="form-control col-sm-9" required autofocus>
				</div>
				<div class="form-group row">
					<label for="passwprd" class="size col-sm-3">パスワード</label>
					<input type="password" name="password" id="password" class="form-control col-sm-9" required>
				</div>
				<div class="login-position">
					<input type="submit" value="ログイン">
				</div>
			</form>
		</div>
	</div>
</body>
</html>