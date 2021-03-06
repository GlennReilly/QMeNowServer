<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form modelAttribute="appointmentType" action="/QMeNow/appointmentType/addOrUpdate" method="post" cssClass="formBackground">
    <%--<form:errors path="*" cssClass="errorblock" element="div" />--%>
  <div>
      <div>
          <div style="clear: both;">
              <div class="formLabel">
                  <label>New Appointment Type name:</label>
              </div>
              <div class="formControl">
                  <form:input path="name"/>
                  <form:errors path="name" cssClass="error"/>
              </div>
          </div>

          <div style="clear: both;">
              <div class="formLabel">
                  <label>prefix:</label>
              </div>
              <div class="formControl">
                  <form:input path="prefix"/>
                  <form:errors path="prefix" cssClass="error"/>
              </div>
          </div>

          <div style="clear: both;">
              <div class="formLabel">
                  <label>Background colour hex code:</label>
              </div>
              <div class="formControl">
                  <form:input type="color" path="backgroundColourHexCode"/>
              </div>
          </div>
          <div style="clear: both;">
              <div class="formLabel">
                  <label>is Default?</label>
              </div>
              <div class="formControl">
                  <form:checkbox path="isDefault" id="isDefaultCheck"></form:checkbox>
              </div>
          </div>

      </div>
      <c:choose>
          <c:when test="${empty command.id || command.id eq 0 }">
              <c:set var="buttonText" value="Save New Appointment Type" />
          </c:when>
          <c:otherwise>
              <c:set var="buttonText" value="Update Appointment Type" />
          </c:otherwise>
      </c:choose>
      <div class="label2">
          <input type="submit" value="${buttonText}">
      </div>
  </div>
</form:form>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
