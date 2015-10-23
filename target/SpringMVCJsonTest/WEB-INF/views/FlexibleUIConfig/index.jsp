<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 16/08/15
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<br/>
<ul>
    <li><a href="./login">Login</a></li>
    <li><a href="./customer/add">Add a new customer</a></li>
    <li><a href="./customer/find">Find a customer</a></li>
    <li><a href="./appointment/add">Add a new appointment for a customer</a></li>
    <li><a href="./user/add">Add a new user</a></li>
    <li><a href="./business/add">Add a new business</a></li>
    <li><a href="./user/getAppointments/1234">/user/getAppointments/1234</a></li>
    <li><a href="./buttonStyle/add">Add a new button style</a></li>
    <li><a href="./edit/42">/FlexibleUIConfig/edit/42</a></li>
</ul>
</body>
</html>
