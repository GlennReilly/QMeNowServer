<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 8/03/15
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <configName></configName>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body>
    <form:form action="/phrase/newPhraseForm" method="POST">
        <fieldset>
            <div class="form-row">
                <label for="phraseText">phraseText</label>
                <form:input path="phraseText"></form:input>
            </div>
            <div class="form-row">
                <label for="phraseAuthor">phraseAuthor</label>
                <form:input path="phraseAuthor"></form:input>
            </div>
            <input type="submit" value="Submit">
        </fieldset>
    </form:form>
</body>
</html>
