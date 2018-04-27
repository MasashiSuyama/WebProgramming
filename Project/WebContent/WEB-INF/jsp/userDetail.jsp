<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ情報詳細参照</title>
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
	<h1>ユーザ情報詳細参照</h1>
	<div class="row">
		<div class="col-sm-5">
			<p class="size col-md-7 text-center">ログインID</p>
		</div>
		<div class="col-sm-7">
			<p>${userDetail.loginId}</p>
		</div>
	</div>
	<div class="row">
		<div class="size col-sm-5">
			<p class="col-md-7 text-center">ユーザ名</p>
		</div>
		<div class="col-sm-7">
			<p>${userDetail.name}</p>
		</div>
	</div>
	<div class="row">
		<div class="size col-sm-5">
			<p class="col-md-7 text-center">生年月日</p>
		</div>
		<div class="col-sm-7">
			<p>${userDetail.birthDate}</p>
		</div>
	</div>
	<div class="row">
		<div class="size col-sm-5">
			<p class="col-md-7 text-center">登録日時</p>
		</div>
		<div class="col-sm-7">
			<p>${userDetail.createDate}</p>
		</div>
	</div>
	<div class="row">
		<div class="size col-sm-5">
			<p class="col-md-7 text-center">更新日時</p>
		</div>
		<div class="col-sm-7">
			<p>${userDetail.updateDate}</p>
		</div>
	</div>
	<a href="UserListServlet">
		<button type="button" class="btn btn-link" style="text-decoration: underline">戻る</button>
	</a>
</body>
</html>