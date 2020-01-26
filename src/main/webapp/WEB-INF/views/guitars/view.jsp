<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/guitarshop.css" />"rel="stylesheet">
<title>${user.name} page</title>
</head>
<body>	
	
	<div class="wrapper">
		<header class="header">
			<jsp:include page="../header.jsp" />  
		</header>
		<main class="main">
		<h3>${guitar.name}</h3>
		<div class="info_field">		
							
					<img src="${path}/images/guitars/${guitar.id}.jpg"
						width="300" border="0" align="left"
						onError="this.src='<s:url value="/resources/images"/>/default_guitar.jpg';" />	
						<form>
				<fieldset>
				 	<legend>Guitar info</legend>				
					<table>
						<tr>
							<th>Guitar name:</th>
							<td><c:out value="${guitar.name}" /></td>
						</tr>
						<tr>
							<th>Guitar type:</th>
							<td><c:out value="${guitar.type}" /></td>
						</tr>
						<tr>
							<th>Guitar price:</th>
							<td><c:out value="${guitar.price}" /></td>
						</tr>	
						<tr>
							<th>Guitar owner:</th>
							<td>
								<s:url value="/users/{userlogin}" var="user_login">
									<s:param name="userlogin" value="${guitar.user.login}"/>	
								</s:url>									
								<a href="${user_login}"> 
									<c:out value="${guitar.user.name}" />
								</a>	
							</td>
						</tr>
						<tr>
							<th>Guitar owner telephone</th>
							<td><c:out value="${guitar.user.phone}"/></td>
						</tr>
						<tr>
							<th>Guitar description</th>
							 <td><c:out value="${guitar.description}"/></td>
						</tr>				
					</table>				
				</fieldset>
			</form>
			<sec:authentication property="name" var="login"/>						
				<sec:authorize access="isAuthenticated()">
				<c:set var="curlogin" value="${login}"/>
				<c:set var="ownlogin" value="${guitar.user.login}"/>
				
  					<c:if test="${curlogin==ownlogin}">
  						<div class="sidebar">
				
							<s:url value="/guitars/{type}/{name}-{id}/edit" var="guitar_edit">
							<s:param name="type" value="${guitar.type}"/>
							<s:param name="name" value="${guitar.name}"/>
							<s:param name="id" value="${guitar.id}"/>				
						</s:url>
						
					    
					    <s:url value="/guitars/{type}/{name}-{id}/delete" var="guitar_delete">
							<s:param name="type" value="${guitar.type}"/>
							<s:param name="name" value="${guitar.name}"/>
							<s:param name="id" value="${guitar.id}"/>				
						</s:url>
						<s:url value="/guitars/{type}/{name}-{id}/print" var="guitar_print">
							<s:param name="type" value="${guitar.type}"/>
							<s:param name="name" value="${guitar.name}"/>
							<s:param name="id" value="${guitar.id}"/>	
						</s:url>
						<ul>
							<li>
								<h3>Action menu</h3>
							</li>
							<li>
								<a href="${guitar_edit}"> <c:out value="Edit  ${guitar.name} info" /></a>	
							</li>
							<li>
								<a href="${guitar_delete}"> <c:out value="Delete  ${guitar.name}" /></a>	
							</li>
							<li>
								<a href="${guitar_print}"><c:out value="Print PDF info"/></a>
							</li>
						</ul>
						</div>
  						
							
  					</c:if>								
			</sec:authorize>	
			
																
				
		</div>
			
		</main>
	<footer class="footer">© 2019</footer>
</div>
</body>
</html>