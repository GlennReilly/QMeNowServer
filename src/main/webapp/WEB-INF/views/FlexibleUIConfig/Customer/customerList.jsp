<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 22/10/15
  Time: 8:59 PM
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
        logoPath="${logoFileName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
    <table id="resultsTable">
    <thead>
    <tr class="resultsTable">
        <th>Customer Id</th>
        <th>Name</th>
        <th>Details</th>
        </tr>
    </thead>
    <c:forEach items="${customerList}" var="customer">
    <tr>
        <td>
            <c:out value="${customer.id}"/>
        </td>
        <td>
            <c:out value="${customer.firstName} ${customer.lastName}"/>
        </td>
        <td>
            <span class="listLink"><a href='<spring:url value="../details/${customer.id}"/>'>view</a></span>
        </td>
    </tr>
    </c:forEach>
    <tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
    <script type="text/javascript" src="<spring:url value='/resources/DataTables/datatables.min.js'/>"></script>
    <script>
        $(function(){
            $("#resultsTable").DataTable();
        });
    </script>
</body>
</html>
