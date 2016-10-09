<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/menu.jsp"/>

<h1><c:out value="${entryModel.name}"/></h1>
<table>
	<tr>
		<td>Entry:</td>
		<td><c:out value="${entryModel.entryHtml}" escapeXml="false"/></td>
	</tr>
</table>

<p>
	<spring:url value="/edit/${entryModel.name}" var="editActionUrl"/>
	<spring:url value="/delete/${entryModel.name}" var="deleteActionUrl"/>
	<a href="${editActionUrl}">(edit)</a>
	<a href="${deleteActionUrl}">(delete)</a>
</p>


<jsp:include page="fragments/footer.jsp" />