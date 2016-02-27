<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 19/10/15
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
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
<form:form action="/FlexibleUIConfig/location/addOrEdit/" method="post">
  <div>
    <div>
      <div class="label2">
        <label>location name:</label><form:input path="name"/>
      </div>
        <div class="label2">
            <label>Background colour hex code:</label><form:input type="color" path="backgroundColourHexCode" />
        </div>
    </div>
    <div class="label2">
      <input type="submit">
    </div>
  </div>
</form:form>
<tags:jsIncludes logoPath="${logoName}"></tags:jsIncludes>
</body>
</html>
