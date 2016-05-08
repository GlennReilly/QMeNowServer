
<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 31/08/15
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <%--<configName>Add a customer</configName>--%>
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
<form:form modelAttribute="customer" action="/FlexibleUIConfig/customer/add" method="post" cssClass="formBackground">
    <div>
        <div>
            <div style="clear: both;">
                <div class="formLabel">
                    <label>first name:</label>
                 </div>
                <div class="formControl">
                    <form:input path="firstName"/>
                    <form:errors path="firstName" cssClass="error"/>
                </div>
            </div>

            <div style="clear: both;">
                <div class="formLabel">
                    <label>last name:</label>
                </div>
                <div class="formControl">
                    <form:input path="lastName"/>
                    <form:errors path="lastName" cssClass="error"/>
                </div>
            </div>

            <div style="clear: both;">
                <div class="formLabel">
                    <label>phone number:</label>
                </div>
                <div class="formControl">
                    <form:input path="phoneNumber"/>
                </div>
            </div>

            <div style="clear: both;">
                <div class="formLabel">
                    <label>email address:</label>
                </div>
                <div class="formControl">
                    <form:input path="emailAddress"/>
                </div>
            </div>

            <div style="clear: both;">
                <div class="formLabel">
                    <label>street address:</label>
                </div>
                <div class="formControl">
                    <form:input path="physicalAddress"/>
                </div>
            </div>
<%--            <div class="label2">
                <label>DOB:</label><form:input path="DOB"/>
            </div>
            <div class="label2">
                <label>gender:</label><form:input path="gender"/>
            </div>--%>
        </div>
        <div class="label2">
            <input type="submit"/>
        </div>
    </div>
</form:form>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
</body>
</html>
