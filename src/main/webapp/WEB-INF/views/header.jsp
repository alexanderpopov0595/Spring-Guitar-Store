<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%	
	String path = System.getProperty("catalina.home");
	application.setAttribute("path", path);	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<nav>
		<ul>
			<li>
				<form name="search" method="GET"  action="/SpringGuitarShop/search/1">
					<input type="text" name="name" placeholder="Input guitar's name">	
			 			<span class="title">Press</span>
							<div id="menu" class="menu">								
								<input list="typeList"  name="type" placeholder="Choose guitar's type"/>									
								<input name="maxprice" placeholder="Input max price"/>									
								<input name="minprice" placeholder="Input min price"/>	
								<input type="radio" name="order" checked="true" value="higher">From higher price to lower	
								<input type="radio" name="order"  value="lower">From lower price to higher								
							</div>
					<input type="submit" value="Search"/>
				</form>				
				<datalist id="typeList">
					<option value="acoustic"/>
					<option value="bass"/>
					<option value="electric"/>		
				</datalist>
			</li>		
			<li>
				<sec:authorize access="!isAuthenticated()">
  					<a href="<s:url value="/login"/>">Log in</a> 
				</sec:authorize>
				<sec:authentication property="name" var="login"/>						
				<sec:authorize access="isAuthenticated()">
  					<a href="<s:url value="/users/${login}"/>">Your profile</a>   								
				</sec:authorize>
			</li>
			<li>
				<a href="<s:url value="/home/1"/>">Home</a>
			</li>
			<li>
				<a href="<s:url value="/electric/1"/>">Electric guitars</a>
			</li>
			<li>
				<a href="<s:url value="/acoustic/1"/>">Acoustic guitars</a>
			</li>
			<li>
				<a href="<s:url value="/bass/1"/>">Bass guitars</a></li>
			<li>
			<sec:authorize access="isAuthenticated()">
  				<a href="<s:url value="/static/j_spring_security_logout"/>">Log out</a>
			</sec:authorize>
			</li>
		</ul>
	</nav>
	<script>
	let menuElem = document.getElementById('menu');
	let titleElem = document.querySelector('.title');
	titleElem.onclick = function() {
		menuElem.classList.toggle('open');
	    titleElem.classList.toggle('down');    
	};
	</script>
</body>
</html>