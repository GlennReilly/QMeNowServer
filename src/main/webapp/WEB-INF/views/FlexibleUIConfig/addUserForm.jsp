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
  <%--<configName>Add a newUser</configName>--%>
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
<form:form modelAttribute="newUser" action="/FlexibleUIConfig/user/add" method="post">
  <div>
      <div class="label2">
<%--          <label>business:</label>
          <form:select path="selectedBusinessId">
              <form:option value="0" label="Please select" />
              <form:options items="${command.activeBusinesses}" itemValue="id" itemLabel="businessName" />
          </form:select>--%>
      </div>
    <div class="label2">
      <label>first name:</label>
        <input type="text" name="newUser.firstName">
        <form:errors path="firstName" cssClass="error" />
        <form:errors path="*" cssClass="errorblock" />

<%--        <spring:hasBindErrors name="command">
            <c:if test="${errors.hasFieldErrors('firstName')}">
                newUser.firstName is in error!
            </c:if>
        </spring:hasBindErrors>--%>

    </div>
    <div class="label2">
      <label>last name:</label>
        <input type="text" name="newUser.lastName">
    </div>
      <div class="label2">
          <label>username:</label>
          <input type="text" name="newUser.username">
      </div>
      <div class="label2">
          <label>password:</label>
          <input type="password" name="newUser.password">
      </div>
      <div class="label2">
          <label>phone Number:</label>
          <input type="text" name="newUser.phoneNumber">
      </div>
      <div class="label2">
          <label>email address:</label>
          <input type="text" name="newUser.emailAddress">
      </div>
      <div class="label2">
          <label>physical address:</label>
          <input type="text" name="newUser.physicalAddress">
      </div>
      <div class="label2">
        <input type="submit">
      </div>
  </div>
</form:form>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
