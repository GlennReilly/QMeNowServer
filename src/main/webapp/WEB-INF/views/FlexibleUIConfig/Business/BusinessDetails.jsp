
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
        logoPath="${command.logoFileName}"
        businessName="${command.businessName}"
        headerColour="${headerColour}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="./add/" method="post">
    <div>
        <div>
            <div class="label2">
                <label>business name:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.businessName}" /></span>
            </div>
            <div class="label2">
                <label>contact name:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.contactName}"/></span>
            </div>
            <div class="label2">
                <label>phone number:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.phoneNumber}"/></span>
            </div>
            <div class="label2">
                <label>email address:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.emailAddress}"/></span>
            </div>
            <div class="label2">
                <label>street address:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.physicalAddress}"/></span>
            </div>
            <div class="label2">
                <label>BaseURL:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.serverBaseURL}"/></span>
            </div>
            <div class="label2">
                <label>Application URL:</label><span style="margin-left:10px; font-weight:bold;"><c:out value="${command.applicationAPIURL}"/></span>
            </div>

        </div>
        <div class="label2">
                <a href="<spring:url value='/FlexibleUIConfig/business/barcode/'/>">show barcode</a>
            <br/>
                <a href="<spring:url value='/FlexibleUIConfig/business/edit/${command.id}'/>">edit business details</a>

        </div>
    </div>
</form:form>
<tags:jsIncludes logoPath="${command.logoFileName}"></tags:jsIncludes>
</body>
</html>
