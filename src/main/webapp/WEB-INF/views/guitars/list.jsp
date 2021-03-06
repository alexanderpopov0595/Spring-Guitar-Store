<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/guitarshop.css" />"rel="stylesheet">
<title>Login page</title>
</head>
<body>	
	<div class="wrapper">
		<header class="header">
			<jsp:include page="../header.jsp" />  
		</header>
		<main class="main">
		<h3>All guitars for ${user.name}</h3>
		<ol>				
				<c:out value="${beg} ${en}"/>
 				<c:forEach var="guitar" items="${guitarList}">
					<div class="list">
						<s:url value="/guitars/{guitarType}/{guitarName}-{guitarId}" var="guitar_url">
							<s:param name="guitarType" value="${guitar.type}" />
							<s:param name="guitarName" value="${guitar.name}" />
							<s:param name="guitarId" value="${guitar.id}" />
						</s:url>
						<li>					
							<img src="${path}/images/guitars/${guitar.id}.jpg"
								width="48" border="0" align="middle"
								onError="this.src='<s:url value="/resources/images"/>/default_guitar.jpg';" />						
							<a href="${guitar_url}">
								<c:out value="${guitar.name}"/>
							</a> 
							<c:out value="${guitar.price}"/>
							<c:out value="${guitar.date}"/>
						</li>
					</div>
				</c:forEach>
			</ol>	
		</main>
	<footer class="footer">
			<footer class="footer">© 2019</footer>
	</footer>
</div>
</body>
</html>