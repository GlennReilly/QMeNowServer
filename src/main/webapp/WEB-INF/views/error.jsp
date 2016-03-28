<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 9/08/15
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <configName></configName>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<tags:jsIncludes logoPath="${command.logoFileName}"></tags:jsIncludes>
</body>
</html>
