<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="./find/" method="post">
    <div>
        <div>
            <div class="label2">
                <label>user id:</label><form:input path="customerIdStr" />
            </div>
            <div class="label2">
                <label>first name:</label><form:input path="firstName" />
            </div>
            <div class="label2">
                <label>last name:</label><form:input path="lastName" />
            </div>
        </div>
        <div class="label2">
            <input type="submit" value="find customer">
        </div>
    </div>
</form:form>
</body>
</html>
