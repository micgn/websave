<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%--
  ~  Copyright 2016 Michael Gnatz.
  ~
  ~  Licensed under the Apache License, Version 2.0 (the "License");
  ~  you may not use this file except in compliance with the License.
  ~  You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~  Unless required by applicable law or agreed to in writing, software
  ~  distributed under the License is distributed on an "AS IS" BASIS,
  ~  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~  See the License for the specific language governing permissions and
  ~  limitations under the License.
  ~
  ~
  --%>

<jsp:include page="fragments/header.jsp"/>

<jsp:include page="fragments/menu.jsp"/>

<spring:url value="/add" var="userActionUrl"/>

<form:form action="${userActionUrl}" modelAttribute="entryForm" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td>
                <form:input path="name" size="40"/>
                <form:errors path="name" cssClass="errors"/>
            </td>
        </tr>
        <tr>
            <td>Entry</td>
            <td>
                <form:textarea path="entry" rows="10" cols="50"/>
                <form:errors path="entry" cssClass="errors"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add"/>
</form:form>

<jsp:include page="fragments/footer.jsp"/>