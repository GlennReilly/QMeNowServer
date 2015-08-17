<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 16/08/15
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a button style</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
Add a button style
<br/>
<form:form action="./add/" method="post">
    <div>
        <div>
            <div class="label2">
                <label>name:</label><form:input path="name"/>
            </div>
        </div>
<%--        <div>
            <div class="label2">
                <label>text:</label><form:input path="text"/>
            </div>
        </div>--%>
        <div>
            <div class="label2">
                <label>textColour:</label><form:input path="textColour"/>
            </div>
        </div>
        <div>
            <div class="label2">
                <label>backgroundColorHex:</label><form:input path="backgroundColorHex"/>
            </div>
        </div>
        <div>
            <div class="label2">
                <label>padding:</label><form:input path="padding"/>
            </div>
        </div>
        <input type="submit"/>
    </div>
</form:form>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/addButtonStyleForm.js'/>" ></script>
</body>
</html>
