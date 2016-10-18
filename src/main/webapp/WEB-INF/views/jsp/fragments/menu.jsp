<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

<p>
    <spring:url value="/list" var="list"/>
    <a href="${list}">(list)</a>
    <spring:url value="/changepw" var="password"/>
    <a href="${password}">(password)</a>
    <spring:url value="/save" var="save"/>
    <a href="${save}">(save)</a>
    <spring:url value="/logout" var="logout"/>
    <a href="${logout}">(logout)</a>
</p>