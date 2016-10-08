<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="fragments/header.jsp" />

<spring:url value="/login" var="userActionUrl"/>

<form:form action="${userActionUrl}" modelAttribute="loginForm" method="post">
	<form:password path="pw"/><br/>
	<form:errors path="pw" cssClass="errors" />
	<input type="submit" value="OK"/><br/>
	Hint: <c:out value="${LoginCommand.hint}"/>
</form:form>

<jsp:include page="fragments/footer.jsp" />