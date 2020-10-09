<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${not empty request.flashAttributes}">
    <div class="alert alert-${css} alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<strong><spring:message code="${msg}" /></strong>
    </div>
</c:if>
<ul>
  <li><spring:message code="user.show.name" />: ${user.name}</li>
  <li><spring:message code="user.show.email" />: ${user.email}</li>
</ul>
<form:form method="DELETE" action="${pageContext.request.contextPath}/users/1"><input type="submit" value="<spring:message code="user.delete" text="Delete" />" /></form:form>