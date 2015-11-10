
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
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="./add/" method="post">
    <div>
        <div>
            <div class="label2">
                <label>business name:</label><c:out value="${command.businessName}"/>
            </div>
            <div class="label2">
                <label>contact name:</label><c:out value="${command.contactName}"/>
            </div>
            <div class="label2">
                <label>phone number:</label><c:out value="${command.phoneNumber}"/>
            </div>
            <div class="label2">
                <label>email address:</label><c:out value="${command.emailAddress}"/>
            </div>
            <div class="label2">
                <label>street address:</label><c:out value="${command.physicalAddress}"/>
            </div>
        </div>
        <div class="label2">
            <ul>
                <li><a href="<spring:url value='/FlexibleUIConfig/business/barcode/'/>">barcode</a></li>
            </ul>
        </div>
    </div>
</form:form>
</body>
</html>
