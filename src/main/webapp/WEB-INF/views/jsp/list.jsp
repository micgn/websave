<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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

<p>
	<spring:url value="/showall" var="showall"/>
	<a href="${showall}">(show all)</a>
	<spring:url value="/changepw" var="password"/>
	<a href="${password}">(password)</a>
	<spring:url value="/save" var="save"/>
	<a href="${save}">(save)</a>
	<spring:url value="/logout" var="logout"/>
	<a href="${logout}">(logout)</a>
</p>

<ul class="list">
	<c:forEach items="${list.entries}" var="entry">
		<li>
			<spring:url value="/show/${entry.name}" var="showActionUrl"/>
			<spring:url value="/edit/${entry.name}" var="editActionUrl"/>
			<spring:url value="/delete/${entry.name}" var="deleteActionUrl"/>

			<a href="${showActionUrl}"><span class="entry"><c:out value="${entry.name}"/></span></a>
			<a href="${editActionUrl}">(edit)</a>
			<a href="${deleteActionUrl}">(delete)</a>
		</li>
	</c:forEach>
</ul>

<p>
	<spring:url value="/add" var="add"/>
	<a href="${add}">(new)</a>
</p>

<jsp:include page="fragments/footer.jsp" />