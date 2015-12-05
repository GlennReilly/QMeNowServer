
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
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>

<tags:menu></tags:menu>
<tags:header
        logoPath="${logoName}"
        businessName="${businessName}">
</tags:header>


<br/>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>

<form:form action="/FlexibleUIConfig/business/addOrUpdate/" method="post">
    <%--<form:form action="../addOrUpdate/" method="post">--%>
    <div>
        <div>
            <div class="label2">
                <label>business name:</label><form:input path="businessName"/>
            </div>
            <div class="label2">
                <label>contact name:</label><form:input path="contactName"/>
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
            <div class="label2">
                <label>default location:</label>
                <form:select path="defaultLocationId">
                    <form:option value="0" label="Please select" />
                    <form:options items="${activeLocations}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
            <div style="font-size: 8pt; padding-left: 300px;">* new appointments will be created at this location if no existing appointment is found.</div>
            <div style="font-size: 8pt; padding-left: 300px;">Set this to 'Please select' if you don't want walk-in appointments created.</div>
        </div>
        <div class="label2" style="margin-top: 20px;">
            <c:choose>
                <c:when test="${empty command.id || command.id eq 0 }">
                    <c:set var="buttonText" value="Save New Business" />
                </c:when>
                <c:otherwise>
                    <c:set var="buttonText" value="Update Business" />
                </c:otherwise>
            </c:choose>
            <input type="submit" value="${buttonText}">
        </div>
    </div>
</form:form>
<form:form method="POST" action="../uploadLogo"
           enctype="multipart/form-data">

    <form:errors path="*" cssClass="errorblock" element="div" />

    Please select a logo to upload : <input type="file" name="logo" />
    <input type="submit" value="upload" />
		<span><form:errors path="logo" cssClass="error" />
		</span>

</form:form>
</body>
</html>
