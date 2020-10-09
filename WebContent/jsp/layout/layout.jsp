<%@ page contentType="text/html;charset=UTF-8" %>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>    
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="meta.title" text="Spring Sample App" /></title>
    <meta name="description" content="<spring:message code="meta.description" text="Spring Sample App" />"/>
	<meta name="keywords" content="<spring:message code="meta.keywords" text="Spring,Sample" />"/>
	
	<tilesx:useAttribute id="list" name="default-css" classname="java.util.List" />
	<c:forEach var="item" items="${list}">
		<link rel="stylesheet" href="<c:url value="${item}"/>" type="text/css" media="screen" />
	</c:forEach>
	<tilesx:useAttribute id="list" name="css" classname="java.util.List" />
	<c:forEach var="item" items="${list}">
		<link rel="stylesheet" href="<c:url value="${item}"/>" type="text/css" media="screen" />
	</c:forEach>
	<tilesx:useAttribute id="list" name="default-js" classname="java.util.List" />
	<c:forEach var="item" items="${list}">
  		<script src="<c:url value="${item}"/>" type="text/javascript"></script>
	</c:forEach>
	<tilesx:useAttribute id="list" name="js" classname="java.util.List" />
	<c:forEach var="item" items="${list}">
  		<script src="<c:url value="${item}"/>" type="text/javascript"></script>
	</c:forEach>
</head>
<body>
	<tiles:insertAttribute name="header" />     
    <div class="container">
    	<c:if test="${not empty request.flashAttributes}">
	    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong><spring:message code="${msg}" /></strong>
	    </div>
		</c:if>
        <tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="footer" />   
</body>
</html>