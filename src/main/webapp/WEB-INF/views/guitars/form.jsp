<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UFT-8">
<link href="<c:url value="/resources/css/guitarshop.css" />"rel="stylesheet">
<title>Login page</title>
</head>
<body>	
	<div class="wrapper">
		<header class="header">
			<jsp:include page="../header.jsp" />  
		</header>
		<main class="main">
			
				<h2>Add new guitar</h2>
				<div class="info_field">				
				<sf:form method="POST" modelAttribute="guitar" enctype="multipart/form-data">
					<fieldset>
						<legend>Guitar info</legend>
						<table cellspacing="5" font-size="24">					
							<tr>
								<th><label for="guitar_name">Guitar name:</label></th>
								<td>
									<sf:input path="name" size="50" maxlength="50" id="guitar_name"/>
									<sf:errors path="name" cssClass="errors"/>										
								</td>								
							</tr>
							<tr>
								<th><label for="guitar_type">Guitar type: </label></th>
								<td>
									<sf:input path="type" size="50" maxlength="10" id="guitar_login"/>
									<sf:errors path="type" cssClass="errors"/>
								</td>
							</tr>
							<tr>
								<th><label for="guitar_price">Guitar price: </label></th>
								<td>
									<sf:password path="price" size="50" maxlength="10"  showPassword="true" id="guitar_price" />
									<sf:errors path="price" cssClass="errors"/>
								</td>
							</tr>
							<tr>
 								<th><label for="guitar_description">Guitar description:</label></th>
 								<td>
 									<sf:input path="description" size="50" maxlength="255" id="guitar_description"/>
 									<sf:errors path="description" cssClass="errors"/>
 								</td>
 							</tr>
 							<tr>
 								<th><label for="image">Guitar photo</label></th>
 								<td><input name="image" type="file"/>						
 							</tr>
 							<tr>
 								<th></th>
 								<td><input name="commit" type="submit" value="Add guitar." /></td>
 							</tr>
						</table>
					</fieldset>
				</sf:form>				
			</div>
		</main>
	<footer class="footer">
			<footer class="footer">© 2019</footer>
	</footer>
</div>
</body>
</html>