<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 1/09/15
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <%--<configName>Add a user</configName>--%>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<tags:menu></tags:menu>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="./add/" method="post">
  <div>
      <div class="label2">
          <form:select path="selectedBusinessId">
              <form:option value="0" label="Please select" />
              <form:options items="${command.activeBusinesses}" itemValue="id" itemLabel="businessName" />
          </form:select>
      </div>
    <div class="label2">
      <label>first name:</label><form:input path="user.firstName" />
    </div>
    <div class="label2">
      <label>last name:</label><form:input path="user.lastName" />
    </div>
      <div class="label2">
          <label>username:</label><form:input path="user.username" />
      </div>
      <div class="label2">
          <label>password:</label><form:input path="user.password" />
      </div>
      <div class="label2">
          <label>phone Number:</label><form:input path="user.phoneNumber" />
      </div>
      <div class="label2">
          <label>email address:</label><form:input path="user.emailAddress" />
      </div>
      <div class="label2">
          <label>physical address:</label><form:input path="user.physicalAddress" />
      </div>
      <div class="label2">
        <input type="submit">
      </div>
  </div>
</form:form>
</body>
</html>
