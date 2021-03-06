<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 22/10/15
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="/QMeNow/customer/update" method="post">
    <div>
        <div>
            <div class="label2">
                <label>first name:</label><form:input path="firstName"/>
            </div>
            <div class="label2">
                <label>last name:</label><form:input path="lastName"/>
            </div>
            <div class="label2">
                <label>phone number:</label><form:input path="phoneNumber"/>
            </div>
            <div class="label2">
                <label>email address:</label><form:input path="emailAddress"/>
            </div>
            <div class="label2">
                <label>street address:</label><form:input path="physicalAddress"/>
            </div>
        </div>
        <div class="label2">
            <input type="submit" value="update"/>
        </div>
        <div>
            <img src="<c:out value="${command.barcodeImageString}"></c:out>" />
        </div>
    </div>
</form:form>
<span class="listLink"><a href='<spring:url value="/appointment/add/${command.id}"/>'>add appointment</a></span>
<span class="listLink"><a href='<spring:url value="/appointment/getAllForDateRange/${command.id}"/>'>show all appointments</a></span>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
