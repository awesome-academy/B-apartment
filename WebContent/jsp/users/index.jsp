<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<h2><spring:message code="user.index.all"/></h2>

<ul class="users">
    <c:forEach items="${users}" var="user" varStatus="status">
    <li>
        <img src="${user.gravatarURL}" class="gravatar"/>
        <a href="<spring:url value="users/${user.id}"/>">${user.name}</a>
        | <a href="<spring:url value="users/${user.id}/edit"/>"><spring:message code="user.edit" text="Edit" /></a>
    </li>
    </c:forEach>
</ul>