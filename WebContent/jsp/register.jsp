<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Register</title>
</head>
<body>
	<h1>Register</h1>

	<form:form action="register" method="post" modelAttribute="user">
		Name: <form:input path="name"/><br><form:errors path="name" cssClass="error" /><br>
		Email: <form:input type="email" path="email"/><br><form:errors path="email" cssClass="error" /><br>
		Password: <form:input type="password" path="password"/><br><form:errors path="password" cssClass="error" /><br>
		Confirmation: <form:input type="password" path="confirmation"/><br>
		<input type="submit" value="Register">
	</form:form>
</body>
</html>