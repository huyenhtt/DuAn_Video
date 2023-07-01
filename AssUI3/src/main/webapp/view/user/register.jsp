<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" href="/AssUI/template/user/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/AssUI/template/user/fontawesome/css/all.min.css">
<link rel="stylesheet"
	href="/AssUI/template/user/css/templatemo-style.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid tm-container-content tm-mt-60">
		<div class="col-lg-12 col-12 mb-5">
			<h2 class="tm-text-primary mb-5 text-center">Register</h2>
			<form id="login-form" action="/AssUI/register" method="POST"
				class="tm-contact-form mx-auto">
				<div class="form-group">
					<label class="form-label">Username:</label> <input type="text"
						class="form-control" name="username" value="">

				</div>
				<div class="form-group">
					<label class="form-label">Password: </label> <input type="password"
						class="form-control" name="password" >
				</div>
				<div class="form-group">
					<label class="form-label">Email: </label> <input type="email"
						class="form-control" name="email" >
				</div>
				<div class="form-group">
					<input class="form-check-input" type="hidden" name="admin"
						value="false">Admin <input class="form-check-input"
						type="radio" name="admin" type="hidden" value="false">User

				</div>
				<br>
				<h4>
					<span style="color: red">${Amessage}</span>
					<span style="color: red">${errorMessageA}</span>
				</h4>


				<br>
				<div class="form-group tm-text-right">
					<button type="submit" class="btn btn-primary">Sign up</button>
				</div>
			</form>
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->

	<%@ include file="/common/footer.jsp"%>
</body>
</html>