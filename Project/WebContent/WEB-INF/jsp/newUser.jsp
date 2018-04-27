<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ新規登録</title>
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
	<h1>ユーザ新規登録</h1>
	<div>
		<c:if test="${errMsg != null}" >
		    <div class="alert alert-danger">${errMsg}</div>
		</c:if>
		<form action="NewUserServlet" method="post">
			<div class="form-group row">
		    	<div class="label-position col-sm-5">
		    		<label for="ID" class="size col-md-7 text-center">ログインID</label>
		    	</div>
		    	<div class="col-sm-7">
		    		<input type="text" id="ID" name="loginId" class="form-control col-md-8"autofocus>
		    	</div>
			</div>
			<div class="form-group row">
				<div class="label-position col-sm-5">
		    		<label for="passwprd" class="size col-md-7 text-center">パスワード</label>
		    	</div>
		    	<div class="col-sm-7">
		    		<input type="text" id="password" name="password" class="form-control col-sm-8">
		    	</div>
			</div>
			<div class="form-group row">
				<div class="label-position col-sm-5">
		    		<label for="passwordCheck" class="size col-md-7 text-center">パスワード(確認)</label>
		    	</div>
		    	<div class="col-sm-7">
		    		<input type="text" id="passwordCheck" name="passwordCheck" class="form-control col-md-8">
		    	</div>
			</div>
			<div class="form-group row">
				<div class="label-position col-sm-5">
		    		<label for="userName" class="size col-md-7 text-center">ユーザ名</label>
		    	</div>
		    	<div class="col-sm-7">
					<input type="text" id="userName" name="userName" class="form-control col-sm-8">
				</div>
			</div>
			<div class="form-group row">
				<div class="label-position col-sm-5">
		    		<label for="birthday" class="size col-md-7 text-center">生年月日</label>
		    	</div>
		    	<div class="col-sm-7">
					<input type="text" id="birthday" placeholder="年-月-日" name="birthday" class="form-control col-sm-8">
				</div>
			</div>
			<div class="botton-position">
				<input type="submit" value="　　登録　　">
			</div>
		</form>
	</div>
	<a href="UserListServlet">
		<button type="button" class="btn btn-link" style="text-decoration: underline">戻る</button>
	</a>
</body>
</html>