<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
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
			<h2 class="tm-text-primary mb-5 text-center">Login</h2>
			<form id="login-form" action="/AssUI/login" method="POST"
				class="tm-contact-form mx-auto">
				<div class="form-group">
					<label class="form-label">Username:</label> <input type="text"
						class="form-control" name="username" >

				</div>
				<div class="form-group">
					<label class="form-label">Password: </label> <input type="password"
						class="form-control" name="password" >
				</div>
				<!--<div class="form-group">
					<select class="form-control" id="contact-select" name="inquiry">
						<option value="-">Subject</option>
						<option value="sales">Sales &amp; Marketing</option>
						<option value="creative">Creative Design</option>
						<option value="uiux">UI / UX</option>
					</select>
				</div> -->
				<!--<div class="form-group">
					<textarea rows="8" name="message" class="form-control rounded-0"
						placeholder="Message" required="required"></textarea>
				</div>-->
				<!--  <h2 style="color: green">${message}</h2>-->
				<br>
				<h4><span style="color: red">${errorMessage}</span></h4>
				<br>
				<div class="form-group tm-text-right">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</form>
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->

	<%@ include file="/common/footer.jsp"%>
</body>
</html>