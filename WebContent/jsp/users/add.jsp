<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<c:if test="${not empty flash}">
<div class="alert alert-${css} alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<strong>${flash}</strong>
</div>
</c:if>
		
<h1><spring:message code="user.new.title" text="Sign up" /></h1>
    
<div class="row">
  <div class="col-md-6 col-md-offset-3">
	<form:form method="POST" action="${pageContext.request.contextPath}/users" modelAttribute="user" >
		<form:label path="name"><spring:message code="user.new.name" text="Name" /></form:label>
		<form:input path="name" cssClass="form-control" /><form:errors path="name" cssClass="error" />
		
		<form:label path="email"><spring:message code="user.new.email" text="Email" /></form:label>
		<form:input type="email" path="email" cssClass="form-control" /><form:errors path="email" cssClass="error" />

		<form:label path="password"><spring:message code="user.new.password" text="Password" /></form:label>
		<form:input type="password" path="password" cssClass="form-control" /><form:errors path="password" cssClass="error" />

		<form:label path="confirmation"><spring:message code="user.new.confirmation" text="Confirmation" /></form:label>
		<form:input type="password" path="confirmation" cssClass="form-control" /><form:errors path="confirmation" cssClass="error" />
		
		<input type="submit" class="btn btn-primary" value="<spring:message code="user.new.submit" text="Submit" />">
	</form:form>
  </div>
</div>