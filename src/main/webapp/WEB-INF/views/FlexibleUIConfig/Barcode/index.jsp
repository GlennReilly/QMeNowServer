<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 1/10/15
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
    <body>
    <tags:header
            logoPath="${Business.logoName}"
            businessName="${Business.businessName}">
    </tags:header>
    <div class="pageTitle">${pageTitle}</div>
    <div class="pageMessage">${message}</div>
        <br/>
        <a href="./">refresh</a>
        <br/>
        <img src="<spring:url value='${QRCodePath}'/>" />
    <tags:jsIncludes logoPath="${Business.logoName}"></tags:jsIncludes>
    </body>
</html>
