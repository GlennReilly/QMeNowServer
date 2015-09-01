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
<html>
<head>
  <title>Add a user</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
Add a User
<br/>
<form:form action="./add/" method="post">
  <div>
    <div class="label2">
      <label>firstName</label><form:input path="firstName" />
    </div>
    <div class="label2">
      <label>lastName</label><form:input path="lastName" />
    </div>
      <div class="label2">
          <label>phone Number</label><form:input path="phoneNumber" />
      </div>
      <div class="label2">
          <label>email Address</label><form:input path="emailAddress" />
      </div>
      <div class="label2">
          <label>physical Address</label><form:input path="physicalAddress" />
      </div>
      <input type="submit">
  </div>
</form:form>
</body>
</html>
