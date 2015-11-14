<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<tags:menu></tags:menu>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="./add" method="post">
  <div>
      <div>
        <div class="label2">
          <label>New Appointment Type name:</label><form:input path="name" />
        </div>
        <div class="label2">
          <label>Background colour hex code:</label><form:input type="color" path="backgroundColourHexCode" />
        </div>
      </div>
      <div class="label2">
          <input type="submit">
      </div>
  </div>
</form:form>
</body>
</html>
