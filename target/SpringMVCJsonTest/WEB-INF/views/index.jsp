<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 8/02/15
  Time: 12:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <configName></configName>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
Welcome to SpringMVCJsonTest
<br/>
<ul>
    <li><a href="./FlexibleUIConfig/">/FlexibleUIConfig</a></li>
    <li><a href="./FlexibleUIConfig/barcode/">/FlexibleUIConfig/barcode/</a></li>
    <br/>
    <li><a href="./greeting/getGreeting">/greeting/getGreeting</a></li>
    <li><a href="./greeting/testDB">/greeting/testDB</a></li>
    <li><a href="./greeting/testReconfigurableApp">/greeting/testReconfigurableApp</a></li>
    <li><a href="./greeting/getGreetingJson">/greeting/getGreetingJson</a></li>
    <li><a href="./greeting/test">/greeting/test</a></li>
    <li><a href="./greeting/bob">/greeting/{bob}</a> - you can change this contactName in the url.</li>
    <li><a href="./phrase/createPhrase?phraseString=insert_phraseString_here">/phrase/createPhrase?phraseString=insert_phraseString_here</a></li>
    <li><a href="./phrase/newPhraseForm">/phrase/newPhraseForm</a></li>
</ul>
<tags:jsIncludes logoPath="${command.logoFileName}"></tags:jsIncludes>
</body>
</html>
