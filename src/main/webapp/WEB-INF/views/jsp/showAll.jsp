<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="fragments/header.jsp" />

<c:forEach items="${model.entries}" var="entry">
	<p>
		<c:out value="${entry.name}" /><br>
		<c:out value="${entry.entryHtml}" escapeXml="false" />
		<hl/>
	</p>
	<hr>
</c:forEach>

</body>
</html>