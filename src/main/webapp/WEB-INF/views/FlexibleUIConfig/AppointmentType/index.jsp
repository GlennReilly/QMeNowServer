<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 15/11/15
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${pageMessage}</div>
<br/>

<c:forEach items="${appointmentTypes}" var="appointmentType">
    <div style="padding-left: 50px;">
        <span>
            <c:out value="${appointmentType.name}"></c:out>
        </span>
        <div style="background-color: <c:out  value="${appointmentType.backgroundColourHexCode}"></c:out>; width: 80px; height: 30px; "></div>

    </div>
    <br/>
</c:forEach>
<br/>
<ul>
    <li><a href="<spring:url value='/FlexibleUIConfig/appointmentType/add'/>">Add a new appointment type for your business</a></li>
</ul>
</body>
</html>
