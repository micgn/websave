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

<c:forEach items="${model.entries}" var="entry">
	<p>
	<h2><c:out value="${entry.name}"/></h2>
	<div class="entryText"><c:out value="${entry.entryHtml}" escapeXml="false"/></div>
		<hl/>
	</p>
	<hr>
</c:forEach>

<script>
	$(document).ready(function () {

		var pw = $("#js_password", parent.document).val();
		if (pw != "") {
			$(".entryText").each(function (index, value) {
				var dec = CryptoJS.AES.decrypt(value.innerHTML, pw);
				dec = dec.toString(CryptoJS.enc.Utf8);
				value.innerHTML = "<pre>" + dec + "</pre>";
			});
		}

	});
</script>

</body>
</html>