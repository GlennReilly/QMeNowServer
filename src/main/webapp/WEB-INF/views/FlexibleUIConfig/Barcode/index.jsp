<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 1/10/15
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title></title>
</head>
    <body>
        Barcode index
        <br/>
        <a href="./makeQRCode">/makeQRCode</a>
        <br/>
        <img src="<spring:url value='/resources/images/qrCode.gif'/>"/>
    </body>
</html>
