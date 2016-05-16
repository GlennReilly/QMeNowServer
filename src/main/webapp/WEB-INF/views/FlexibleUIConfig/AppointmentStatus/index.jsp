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
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/DataTables/datatables.min.css'/>" />
    <style>
        #resultsTable_wrapper{
            width: 35%;
        }
    </style>
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
<c:if test="${empty appointmentStatuses}">
    <div>No appointment statuses found</div>
</c:if>

<table id="resultsTable">
    <thead>
    <tr class="resultsTable">
        <th>Name</th>
        <th>Colour</th>
        <th>Default?</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <c:forEach items="${appointmentStatuses}" var="appointmentType">
        <tr>
            <td>
                <span>
                    <c:out value="${appointmentType.name}"></c:out>
                </span>
            </td>
            <td>
                <div style="background-color: <c:out  value="${appointmentType.backgroundColourHexCode}"></c:out>; width: 80px; height: 30px; "></div>
            </td>
            <td>
                <span>
                    <c:if test="${appointmentType.isDefault == true}"> Yes </c:if>
                    <c:if test="${appointmentType.isDefault == false}"> No </c:if>
                </span>
            </td>
            <td>
                <a href="<spring:url value='/appointmentStatus/update/${appointmentType.id}' />" >update</a>
            </td>
            <td>
                <a href="<spring:url value='/appointmentStatus/delete/${appointmentType.id}' />" >delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<ul>
    <li><a href="<spring:url value='/appointmentStatus/add'/>">Add a new appointment status for your business</a></li>
</ul>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
<script type="text/javascript" src="<spring:url value='/resources/DataTables/datatables.min.js'/>"></script>
<script>
    $(function() {
        $("#resultsTable").DataTable();
    });
</script>
</body>
</html>
