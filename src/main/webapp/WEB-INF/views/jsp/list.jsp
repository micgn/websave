<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="fragments/header.jsp" />

<p>
	<a href="showAll">(show all)</a>
	<a href="changePw">(password)</a>
	<a href="save">(save)</a>
	<a href="logout">(logout)</a>
</p>

<ul>
	<c:forEach items="${list.entries}" var="entry">
		<li>
			<spring:url value="/show/${entry.name}" var="showActionUrl"/>
			<spring:url value="/edit/${entry.name}" var="editActionUrl"/>
			<spring:url value="/delete/${entry.name}" var="deleteActionUrl"/>

			<a href="${showActionUrl}"><c:out value="${entry.name}"/></a>
			<a href="${editActionUrl}">(edit)</a>
			<a href="${deleteActionUrl}">(delete)</a>
		</li>
	</c:forEach>
</ul>

<p>
	<a href="add">(new)</a>
</p>

<jsp:include page="fragments/footer.jsp" />