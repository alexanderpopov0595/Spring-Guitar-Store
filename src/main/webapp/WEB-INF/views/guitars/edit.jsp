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
<title>Edit guitar</title>
</head>
<body>	
	<div class="wrapper">
		<header class="header">
			<jsp:include page="../header.jsp" />  
		</header>
		<main class="main">							
				<h2>Edit  guitar</h2>				
				<div class="info_field">
				<sf:form method="POST" modelAttribute="guitar" enctype="multipart/form-data">
					<fieldset>
						<legend>Guitar info</legend>
						<table cellspacing="5" font-size="24">
							<tr>
								
								<sf:hidden path="id" value="${guitar.id}"/>
								
							</tr>
							<tr>
								<th><label for="guitar_name">Guitar name:</label></th>
								<td>
									<sf:input path="name" size="50" maxlength="50" id="guitar_name" value="${guitar.name}"/>
									<sf:errors path="name" cssClass="errors"/>
								</td>
							</tr>
							<tr>
								<th><label for="guitar_type">Guitar type: </label></th>
								<td>
									<sf:input path="type" size="50" maxlength="10" id="guitar_type" value="${guitar.type}"/>
									<sf:errors path="type" cssClass="errors"/>
								</td>
							</tr>
							<tr>
								<th><label for="guitar_price">Guitar price: </label></th>
								<td>
									<sf:input path="price" size="50" maxlength="10" id="guitar_price" value="${guitar.price}"/>
									<sf:errors path="price" cssClass="errors"/>
								</td>
							</tr>
							<tr>
								<th><label for="guitar_description">Guitar description: </label></th>
								<td>
									<sf:input path="description" size="50" maxlength="255" id="guitar_description" value="${guitar.description}"/>
									<sf:errors path="description" cssClass="errors"/>
								</td>
							</tr>							
 							<tr>
 								<th><label for="image">Guitar photo</label></th>
 								<td><input name="image" type="file"/>	
 								<img id="img" src="${path}/images/guitars/${guitar.id}.jpg"
								width="48" border="0" align="middle"
								onError="this.src='<s:url value="/resources/images"/>/default_guitar.jpg';" />					
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