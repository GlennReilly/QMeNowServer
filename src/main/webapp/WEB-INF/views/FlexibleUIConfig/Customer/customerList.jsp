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
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>

    <c:forEach items="${customerList}" var="customer">
        <c:out value="${customer.firstName} ${customer.lastName}"/>
        <span class="listLink"><a href='<spring:url value="FlexibleUIConfig/Customer/customerDetails/${customer.id}"/>'>view</a></span>
        <span class="listLink"><a href='<spring:url value="/FlexibleUIConfig/appointment/add/${customer.id}"/>'>add appointment</a></span>
        <br/>
    </c:forEach>
</body>
</html>
