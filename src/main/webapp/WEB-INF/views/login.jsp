<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/css/guitarshop.css" />"rel="stylesheet">
<title>Login page</title>
</head>
<body>	
	<div class="wrapper">
		<header class="header">
			<jsp:include page="header.jsp" />  
		</header>
		<main class="main">
		<div class="form">
			<h2>Sign in to GuitarShop</h2>
			<s:url var="authUrl" value="/static/j_spring_security_check" />
			<form method="post" class="signin" action="${authUrl}">
				<fieldset>
					<table cellspacing="0">
						<tr>
							<th><label for="login">Login</label></th>
							<td><input id="login" name="j_username" type="text" /></td>
						</tr>
						<tr>
							<th><label for="password">Password</label></th>
							<td><input id="password" name="j_password" type="password" />

							</td>
						</tr>
						<tr>
							<th></th>
							<td><input id="remember_me"
								name="_spring_security_remember_me" type="checkbox" /> <label
								for="remember_me" class="inline">Remember me</label></td>
						</tr>						
						<tr>
							<th></th>
							<td><input name="commit" type="submit" value="Sign In" /></td>
						</tr>
						<tr>
							<th></th>							
							<td>
								Don't have an account?<br/>
								<a href="<s:url value="/users/form"/>">Create new</a>
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		</main>
		<footer class="footer">
			<footer class="footer">© 2019</footer>
		</footer>
	</div>
</body>
</html>