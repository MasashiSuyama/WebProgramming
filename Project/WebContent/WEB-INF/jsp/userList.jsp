<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>ユーザ一覧</title>
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
	<h1>ユーザ一覧</h1>
	<div class="rightposition text-right" style="text-decoration: underline">
		<a href="NewUserServlet">新規登録</a>
	</div>
	<div>
		<form method="post" action="#">
			<div class="form-group row">
				<label for="loginId" class="size col-sm-2 text-center">ログインID</label>
		    	<input type="text" id="loginId" name= loginId class="form-control col-sm-5">
			</div>
			<div class="form-group row">
	    		<label for="userName" class="size col-sm-2 text-center">ユーザ名</label>
				<input type="text" id="userName" name="userName" class="form-control col-sm-5">
			</div>
			<div class="form-group row">
			 	<label for="birthday" class="size col-sm-2 text-center">生年月日</label>
				<input type="text" id="birthday" name="startBirthday" placeholder="年／月／日" class="form-control col-sm-2">
				<p class="col-sm-1 text-center">～</p>
				<input type="text" id="birthday" name="endBirthday" placeholder="年／月／日" class="form-control col-sm-2">
			</div>
			<div class="rightposition text-right">
					<input type="submit" value="　　検索　　">
			</div>
		</form>
	</div>
	<hr>
	<div class="table-area">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">ログインID</th>
				<th scope="col">ユーザ名</th>
				<th scope="col">生年月日</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<th scope="row">${user.loginId}</th>
					<td>${user.name}</td>
					<td>${user.birthDate}</td>
					<!-- TODO 未実装；ログインボタンの表示制御を行う -->
					<td>
						<a href="UserDetailServlet?id=${user.id}">
							<button type="button" class="btn btn-primary">詳細</button>
						</a>
						<c:if test="${userInfo.loginId.equals(user.loginId) || userInfo.loginId.equals(\"admin\")}">
							<a href="UserUpdateServlet?id=${user.id}">
								<button type="button" class="btn btn-success">更新</button>
							</a>
						</c:if>
						<c:if test="${userInfo.loginId.equals(\"admin\")}">
							<a  href="UserDeleteServlet?id=${user.id}">
								<button type="button" class="btn btn-danger">削除</button>
							</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>>
</body>
</html>