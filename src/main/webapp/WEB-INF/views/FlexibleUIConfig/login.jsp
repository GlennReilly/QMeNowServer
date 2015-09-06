<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 31/08/15
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <title>Login or create account</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
    <form:form action="./login/" method="post">
        <div>
            <div class="label2">
                <label>username:</label><form:input path="username" id="username"/>
            </div>
            <div class="label2">
                <label>password:</label><form:password path="password" id="password" />
            </div>
            <input type="submit">
        </div>
    </form:form>

</body>
</html>