<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/guitarshop.css" />"rel="stylesheet">
<title>Edit account</title>
</head>
<body>	
	<div class="wrapper">
		<header class="header">
			<jsp:include page="../header.jsp" />  
		</header>
		<main class="main">
			<sec:authentication property="name" var="login"/>				
				<h2>Edit  account</h2>				
				<div class="info_field">
				<sf:form method="POST" modelAttribute="user" enctype="multipart/form-data">
					<fieldset>
						<legend>User info</legend>
						<table cellspacing="5" font-size="24">
							<tr>
								<sf:hidden path="id" value="${user.id}"/>
							</tr>
							<tr>
								<th><label for="user_name">Full name:</label></th>
								<td>
									<sf:input path="name" size="50" maxlength="50" id="user_name" value="${user.name}"/>
									<sf:errors path="name" cssClass="errors"/>
								</td>
							</tr>
							<tr>
								<th><label for="user_login">Login: </label></th>
								<td>
									<sf:input path="login" size="50" maxlength="10" id="user_login" value="${user.login}"/>
									<sf:errors path="login" cssClass="errors"/>
								</td>
							</tr>
							<tr>
								<th><label for="user_password">Password: </label></th>
								<td>
									<sf:password path="password" size="50" maxlength="10"  showPassword="true" id="user_password" value="${user.password}"/>
									<sf:errors path="password" cssClass="errors"/>
								</td>
							</tr>
							<tr>
 								<th><label for="user_phone">Phone number:</label></th>
 								<td>
 									<sf:input path="phone" size="50" maxlength="20" id="user_phone" placeholder="(XXX)-XXX-XXXX" value="${user.phone}"/>
 									<sf:errors path="login" cssClass="errors"/>
 								</td>
 							</tr>
 							<tr>
 								<th><label for="image">Account photo</label></th>
 								<td><input name="image" type="file"/>	
 								<img id="img" src="${path}/images/users/${user.login}.jpg"
								width="48" border="0" align="middle"
								onError="this.src='<s:url value="/resources/images"/>/default_photo.jpg';" />					
 							</tr>
 							<tr>
 								<th></th>
 								<td><input name="commit" type="submit" value="Save changes" /></td>
 							</tr>
						</table>
					</fieldset>
				</sf:form>				
			</div>
		</main>
		<footer class="footer">© 2019</footer>
	</div>
</body>
</html>