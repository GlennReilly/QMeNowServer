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
<form:form modelAttribute="newUser" action="/FlexibleUIConfig/user/add" method="post" cssClass="formBackground">
  <div>
      <div class="label2">
              <%--<form:errors path="*" cssClass="errorblock" />--%>

        <%--  <label>business:</label>
          <form:select path="selectedBusinessId">
              <form:option value="0" label="Please select" />
              <form:options items="${command.activeBusinesses}" itemValue="id" itemLabel="businessName" />
          </form:select>--%>

      </div>

        <div>
            <div class="formLabel">
              <label>first name:</label>
            </div>
            <div class="formControl">
                <input type="text" name="newUser.firstName">
                <form:errors path="newUser.firstName" cssClass="error" />
            </div>
        </div>

      <div style="clear: both;">
          <div class="formLabel">
              <label>last name:</label>
          </div>
          <div class="formControl">
              <input type="text" name="newUser.lastName">
              <form:errors path="newUser.lastName" cssClass="error" />
          </div>
      </div>

      <div style="clear: both;">
          <div class="formLabel">
              <label>username:</label>
          </div>
          <div class="formControl">
              <input type="text" name="newUser.username">
              <form:errors path="newUser.username" cssClass="error" />
          </div>
      </div>

      <div style="clear: both;">
          <div class="formLabel">
              <label>password:</label>
          </div>
          <div class="formControl">
              <input type="text" name="newUser.password">
              <form:errors path="newUser.password" cssClass="error" />
          </div>
      </div>

      <div style="clear: both;">
          <div class="formLabel">
              <label>phone Number:</label>
          </div>
          <div class="formControl">
              <input type="text" name="newUser.phoneNumber">
              <form:errors path="newUser.phoneNumber" cssClass="error" />
          </div>
      </div>

      <div style="clear: both;">
          <div class="formLabel">
              <label>email Address:</label>
          </div>
          <div class="formControl">
              <input type="text" name="newUser.emailAddress">
              <form:errors path="newUser.emailAddress" cssClass="error" />
          </div>
      </div>

      <div style="clear: both;">
          <div class="formLabel">
              <label>physical Address:</label>
          </div>
          <div class="formControl">
              <input type="text" name="newUser.physicalAddress">
              <form:errors path="newUser.physicalAddress" cssClass="error" />
          </div>
      </div>

      <div class="label2">
        <input type="submit">
      </div>
  </div>
</form:form>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
