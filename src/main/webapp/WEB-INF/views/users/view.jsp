<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/css/guitarshop.css" />"
	rel="stylesheet">
<title>${user.name}page</title>
</head>
<body>
	<div class="wrapper">
		<header class="header">
			<jsp:include page="../header.jsp" />
		</header>
		<main class="main">
		<h3>Hello, ${user.name}</h3>
		<div class="info_field">
			<img src="${path}/images/users/${user.login}.jpg" width="300"
				border="0" align="left"
				onError="this.src='<s:url value="/resources/images"/>/default_photo.jpg';" />
			<form>
				<fieldset>
				 	<legend>User info</legend>				
					<table>
						<tr>
							<th>Your login:</th>
							<td><c:out value="${user.login}" /></td>
						</tr>
						<tr>
							<th>Your name:</th>
							<td><c:out value="${user.name}" /></td>
						</tr>
						<tr>
							<th>Your phone:</th>
							<td><c:out value="${user.phone}" /></td>
						</tr>					
					</table>				
				</fieldset>
			</form>
			<div class="sidebar">
				
				<ul>
					<li><h3>Action menu</h3></li>
					<li><s:url value="{userlogin}/guitars" var="guitar_list">
							<s:param name="userlogin" value="${user.login}" />
						</s:url> <a href="${guitar_list}"> <c:out value="Show my guitars" /></a>
					</li>
					<li><s:url value="{userlogin}/edit" var="edit_link">
							<s:param name="userlogin" value="${user.login}" />
						</s:url> <a href="${edit_link}"> <c:out value="Edit account" /></a></li>
					<li><s:url value="/guitars/form" var="guitar_form" /> <a
						href="${guitar_form}"> <c:out value="Add new guitar" /></a></li>
					<li><s:url value="/users/{userlogin}/delete/{id}"
							var="user_delete">
							<s:param name="userlogin" value="${user.login}" />
							<s:param name="id" value="${user.id }" />
						</s:url> <a href="${user_delete}"> <c:out value="Delete account" /></a></li>
				</ul>
			</div>			
		</div>
		</main>
		<footer class="footer">© 2019</footer>
	</div>
</body>
</html>