<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 19/10/15
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form:form modelAttribute="location" action="/FlexibleUIConfig/location/addOrEdit/" method="post" cssClass="formBackground">
    <div>
        <div style="clear: both;">
            <div class="formLabel">
                <label>location name:</label>
            </div>
            <div class="formControl">
                <form:input path="name"/>
                <form:errors path="name" cssClass="error"/>
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

        <div style="clear: both;">
            <div class="formLabel">
                &nbsp;
            </div>
            <div class="formControl">
                <input type="submit">
            </div>
        </div>
  </div>
</form:form>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
