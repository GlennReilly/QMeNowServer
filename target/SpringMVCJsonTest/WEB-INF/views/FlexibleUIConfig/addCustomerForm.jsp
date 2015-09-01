
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
<html>
<head>
  <title>Add a customer</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
Add customer
<form:form action="./add/" method="post">
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
        </div>
        <input type="submit"/>
    </div>
</form:form>
</body>
</html>
