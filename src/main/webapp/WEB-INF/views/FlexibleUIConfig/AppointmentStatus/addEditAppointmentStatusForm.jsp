<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="/FlexibleUIConfig/appointmentStatus/addOrUpdate" method="post">
  <div>
    <div>
      <div class="label2">
        <label>New Appointment Status name:</label><form:input path="name" />
      </div>
     <div class="label2">
        <label>Background colour hex code:</label><form:input type="color" path="backgroundColourHexCode" />
      </div>

    </div>
      <c:choose>
          <c:when test="${empty command.id || command.id eq 0 }">
              <c:set var="buttonText" value="Save New Appointment Status" />
          </c:when>
          <c:otherwise>
              <c:set var="buttonText" value="Update Appointment Status" />
          </c:otherwise>
      </c:choose>
      <div class="label2">
          <input type="submit" value="${buttonText}">
      </div>
  </div>
</form:form>
<tags:jsIncludes logoPath="${logoName}"></tags:jsIncludes>
</body>
</html>
