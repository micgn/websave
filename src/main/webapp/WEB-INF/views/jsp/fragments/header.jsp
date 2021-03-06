<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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

<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"></meta>
  <title>Save</title>

  <spring:url value="/resources/core/css/save.css" var="saveCss" />
  <link href="${saveCss}" rel="stylesheet" />

  <spring:url value="/resources/core/js/jquery-3.1.1.min.js" var="jquery"/>
  <script src="${jquery}"></script>

  <spring:url value="/resources/core/js/aes.js" var="crypt1"/>
  <script src="${crypt1}"></script>

  <spring:url value="/resources/core/js/md5.js" var="crypt2"/>
  <script src="${crypt2}"></script>

</head>
<body>