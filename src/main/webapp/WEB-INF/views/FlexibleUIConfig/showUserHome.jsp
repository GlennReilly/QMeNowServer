<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 31/08/15
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<br/>
Business Name: ${Business.businessName}
<br/>
    <ul>
        <li><a href="<spring:url value='/FlexibleUIConfig/business/${Business.id}'/>">/FlexibleUIConfig/business/</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/barcode/'/>">/FlexibleUIConfig/barcode/</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/customer/add'/>"> Add a new customer</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/customer/find'/>">Find a customer</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointmentType/add'/>">Add a new appointment type for your business</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointmentStatus/add'/>">Add a new appointment status for your business</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/location/add'/>">Add a new location for your business</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/user/add'/>"> Add a new user</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointment/getAllForDateRange'/>"> Get all appointments for user with date range</a></li>
    </ul>
</body>
</html>
