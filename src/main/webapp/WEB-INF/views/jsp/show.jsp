<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/menu.jsp"/>

<h1><c:out value="${model.name}" /></h1>
<table>
	<tr>
		<td>Entry:</td>
		<td><c:out value="${model.entryHtml}" escapeXml="false" /></td>
	</tr>
</table>

<p>
	<a href="edit?e=<c:out value="${model.nameEncoded}" />">(edit)</a>
	<a href="delete?e=<c:out value="${model.nameEncoded}" />">(delete)</a>
</p>


<jsp:include page="fragments/footer.jsp" />