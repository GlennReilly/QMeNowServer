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
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}"
        businessName="${businessName}"
        >
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>

<br/>
<fieldset class="userHomeFieldSet">
    <legend>Your Business</legend>
    <ul>
        <li><a href="<spring:url value='/FlexibleUIConfig/business/${Business.id}'/>">Show your business details</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/business/barcode/'/>">Show the QRCode for your business</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/location/'/>">View or change locations for your business</a></li>
    </ul>
</fieldset>
<fieldset class="userHomeFieldSet">
    <legend>Appointment Search</legend>
    <ul>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointment/getAllForDateRange'/>"> Get all appointments for date range</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointment/lookupAppointmentByRefNum'/>"> look up Appointment by RefNum</a></li>
    </ul>
</fieldset>
<fieldset class="userHomeFieldSet">
    <legend>Customers</legend>
    <ul>
        <li><a href="<spring:url value='/FlexibleUIConfig/customer/add'/>"> Add a new customer</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/customer/find'/>">Find a customer</a></li>
    </ul>
</fieldset>
<fieldset class="userHomeFieldSet">
    <legend>Appointment Status and Type</legend>
    <ul>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointmentType/'/>">View or change appointment types for your business</a></li>
        <li><a href="<spring:url value='/FlexibleUIConfig/appointmentStatus/'/>">View or change appointment statuses for your business</a></li>
    </ul>
</fieldset>
<fieldset class="userHomeFieldSet">
    <legend>User Management</legend>
    <ul>
        <li><a href="<spring:url value='/FlexibleUIConfig/user/add'/>"> Add a new user</a></li>
    </ul>
</fieldset>


<tags:jsIncludes logoPath="${Business.logoFileName}"></tags:jsIncludes>
</body>
</html>
