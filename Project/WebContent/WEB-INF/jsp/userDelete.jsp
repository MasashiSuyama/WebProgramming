<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ削除確認</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/origin/common.css" rel="stylesheet">
</head>
<body>
	<div class="menu bg-dark">
		<div class="menu2 size col-sm-11 text-white text-right">${userInfo.name} さん
		</div>
		<div class="menu2 col-sm-1 text-right">
			<a href="LogoutServlet" class="red-char">ログアウト</a>
		</div>
	</div>
	<h1>ユーザ削除確認</h1>
	<p class="text-center">
		ログインID：${userDelete.loginId}
		<br>
		を本当に削除してもよろしいでしょうか。
	</p>
	<div class="login-area row">
		<div>
			<a href="UserListServlet">
				<input type="button" style="margin-left:70px;" value="　キャンセル　">
			</a>
		</div>
		<div>
			<form action="UserDeleteServlet?id=${userDelete.id}" method="post">
				<input type="submit" style="margin-left:90px;" value="&nbsp;　　ＯＫ　　&nbsp;">
			</form>
		</div>
	</div>
</body>
</html>