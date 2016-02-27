
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
<body>
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="/FlexibleUIConfig/customer/add" method="post">
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
<tags:jsIncludes logoPath="${logoName}"></tags:jsIncludes>
</body>
</html>
