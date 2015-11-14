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
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery-ui.min.css'/>" />
</head>
<body>
<tags:menu></tags:menu>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<br/>

<form:form action="../addOrUpdate/" method="post">
  <div>
      <div class="label2">
          <label>appointment date:</label><form:input path="appointment.strAppointmentDate" id="strAppointmentDate" />
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

      <c:choose>
          <c:when test="${empty command.appointment.id || command.appointment.id eq 0 }">
            <c:set var="buttonText" value="Save New Appointment" />
          </c:when>
          <c:otherwise>
              <c:set var="buttonText" value="Update Appointment" />
          </c:otherwise>
      </c:choose>

    <div class="label2">
      <input type="submit" value="${buttonText}">
    </div>
  </div>
  </form:form>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/jquery-ui.min.js'/>" ></script>
<script>
    $(function() {
        $( "#strAppointmentDate" ).datepicker({dateFormat: "dd-mm-yy"});
    });
</script>
</body>
</html>
