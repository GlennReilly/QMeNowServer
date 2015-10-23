<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 11/10/15
  Time: 7:58 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
BusinessId: ${businessId}
<br/>

<form:form action="./add/" method="post">
  <div>
    <div class="label2">
      <label>Select customer:</label>
    <form:select path="selectedUserId">
      <form:option value="0" label="Please select" />
      <form:options items="${command.customersList}" itemValue="id" itemLabel="name" />
    </form:select>
    </div>
    <div class="label2">
      <label>location:</label>
      <form:select path="appointment.locationId">
        <form:option value="0" label="Please select" />
        <form:options items="${command.activeLocations}" itemValue="id" itemLabel="locationName" />
      </form:select>
    </div>
    <div class="label2">
      <label>appointment type:</label>
      <form:select path="appointment.appointmentTypeId">
        <form:option value="0" label="Please select" />
        <form:options items="${command.appointmentTypeList}" itemValue="id" itemLabel="name" />
      </form:select>
    </div>
    <div class="label2">
      <label>message to Customer:</label><form:input path="appointment.messageToCustomer" />
    </div>

    <div class="label2">
      <input type="submit">
    </div>
  </div>
  </form:form>
</body>
</html>
