<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="fragments/header.jsp"/>

<jsp:include page="fragments/menu.jsp"/>

<spring:url value="/add" var="userActionUrl"/>

<form:form action="${userActionUrl}" modelAttribute="entryForm" method="post">
    <table>
        <spring:bind path="entryName">
            <tr>
                <td>Name</td>
                <td>
                    <form:input path="entryName" size="40"/>
                    <form:errors path="entryName" cssClass="errors"/>
                </td>
            </tr>
        </spring:bind>
        <spring:bind path="entryName">
            <tr>
                <td>Entry</td>
                <td>
                    <form:textarea path="entry" rows="10" cols="50"/>
                    <form:errors path="entry" cssClass="errors"/>
                </td>
            </tr>
        </spring:bind>
    </table>
    <input type="submit" value="Add"/>
</form:form>

<jsp:include page="fragments/footer.jsp"/>