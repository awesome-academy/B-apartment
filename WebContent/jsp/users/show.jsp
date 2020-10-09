<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<aside class="col-md-4">
		<section class="user_info">
			<h1>
				<img src="${user.gravatarURL}" class="gravatar"/>
				<c:out value="${user.name}" />
			</h1>
		</section>
		<form:form method="DELETE" action="${pageContext.request.contextPath}/users/1">
	<input type="submit" value="<spring:message code="user.delete" text="Delete" />" />
</form:form>
	</aside>
</div>