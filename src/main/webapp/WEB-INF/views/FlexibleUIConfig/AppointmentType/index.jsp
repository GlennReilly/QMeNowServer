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
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${pageMessage}</div>
<br/>
<c:if test="${empty appointmentTypes}">
    <div>No appointment types found</div>
</c:if>

<table>
    <thead>
    <tr class="resultsTable">
            <th>Name</th>
            <th>Prefix</th>
            <th>Colour</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <c:forEach items="${appointmentTypes}" var="appointmentType">
        <tr>
            <td>
            <span>
                <c:out value="${appointmentType.name}"></c:out>
            </span>
            </td>
            <td>
            <span>
                <c:out value="${appointmentType.prefix}"></c:out>
            </span>
            </td>
            <td>
                <div style="background-color: <c:out  value="${appointmentType.backgroundColourHexCode}"></c:out>; width: 80px; height: 30px; "></div>
            </td>
            <td>
                <a href="<spring:url value='/FlexibleUIConfig/appointmentType/update/${appointmentType.id}' />" >update</a>
            </td>
            <td>
                <a href="<spring:url value='/FlexibleUIConfig/appointmentType/delete/${appointmentType.id}' />" >delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<ul>
    <li><a href="<spring:url value='/FlexibleUIConfig/appointmentType/add'/>">Add a new appointment type for your business</a></li>
</ul>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
