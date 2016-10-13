<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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

<jsp:include page="fragments/header.jsp" />

<spring:url value="/login" var="userActionUrl"/>

<form:form action="${userActionUrl}" modelAttribute="loginForm" method="post">
    <p>
        <form:password path="pw"/><br/>
        <form:errors path="pw" cssClass="errors"/>
    </p>
    <p>
        <input type="submit" value="OK"/><br/>
    </p>
    <p>
        <c:if test="${loginForm.hint != null}">Hint: ${loginForm.hint}</c:if>
    </p>
</form:form>

<jsp:include page="fragments/footer.jsp" />