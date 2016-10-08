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
	<c:forEach items="${model.entries}" var="entry">
		<li>
			<a href="show?e=<c:out value="${entry.nameEncoded}" />"><c:out value="${entry.name}" /></a>
			<a href="edit?e=<c:out value="${entry.nameEncoded}" />">(edit)</a>
			<a href="delete?e=<c:out value="${entry.nameEncoded}" />">(delete)</a>
		</li>
	</c:forEach>
</ul>

<p>
	<a href="add">(new)</a>
</p>

<jsp:include page="fragments/footer.jsp" />