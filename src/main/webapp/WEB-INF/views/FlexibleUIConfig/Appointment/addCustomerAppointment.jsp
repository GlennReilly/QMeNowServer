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
Add a new appointment.
<br/>
Select customer:
<form:form action="./add/" method="post">
  <div>
    <div class="label2">
    <form:select path="selectedUserId">
      <form:option value="0" label="Please select" />
      <form:options items="${command.activeCustomers}" itemValue="id" itemLabel="name" />
    </form:select>
    </div>
    <div class="label2">
      <label>location</label><form:input path="appointment.location" />
    </div>
    <div class="label2">
      <label>messageToUser</label><form:input path="appointment.messageToUser" />
    </div>

    <input type="submit">
  </div>
  </form:form>
</body>
</html>
