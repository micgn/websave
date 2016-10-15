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

<jsp:include page="fragments/menu.jsp"/>

<h1 class="entry"><c:out value="${entryModel.name}"/></h1>
<table>
	<tr>
		<td>Entry:</td>
		<td class="entry" id="entryText">
			<pre><c:out value="${entryModel.entry}" escapeXml="false"/></pre>
		</td>
		<div id="hash" style="display: none">${entryModel.hash}</div>
	</tr>
</table>

<p>
	<spring:url value="/edit/${entryModel.name}" var="editActionUrl"/>
	<spring:url value="/delete/${entryModel.name}" var="deleteActionUrl"/>
	<a href="${editActionUrl}">(edit)</a>
	<a href="${deleteActionUrl}">(delete)</a>
</p>

<script>
	$(document).ready(function () {

		var hash = $("#hash").html();
		var pw = $("#js_password", parent.document).val();
		if (hash != "" && pw != "") {
			var txt = $("#entryText").children().first().html();
			if (hash != CryptoJS.MD5(txt).toString()) {
				var dec = CryptoJS.AES.decrypt(txt, pw);
				dec = dec.toString(CryptoJS.enc.Utf8);
				if (hash == CryptoJS.MD5(dec).toString())
					$("#entryText").children().first().html(dec);
				else
					$("#entryText").html("password WRONG");
			}
		}

	});
</script>


<jsp:include page="fragments/footer.jsp" />