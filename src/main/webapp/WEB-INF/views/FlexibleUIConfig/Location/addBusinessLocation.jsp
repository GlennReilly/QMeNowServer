<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
Add a Location for your business
<br/>
<form:form action="../add/${businessId}" method="post">
  <div>
    <div>
      <div class="label2">
        <label>location name:</label><form:input path="locationName"/>
      </div>
    </div>
    <input type="submit"/>
  </div>
</form:form>
</body>
</html>
