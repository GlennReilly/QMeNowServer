<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 9/08/15
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
  <%--
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style1.css">
--%>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>

<form:form method="post" action="/FlexibleUIConfig/save/${command.currentAppConfig.id}">
EditConfigForms<br/>
${command.currentAppConfig.id}<br/>
Select picture:
  <br/>
Select pageTitle:
  <br/>
Select number of buttons:
  <form:select path="numberOfButtonsRequired">
     <form:option value="0" label="Please select" />
    <c:forEach begin="1" end="10" var="i">
      <form:option value="${i}" />
    </c:forEach>
  </form:select>
  <br/>
for each button
  <br/>
[
  <br/>
Select button style:
  <br/>
Select button link:
  <br/>
]
  <br/>
  <input type="submit" value="Submit"/>
</form:form>

<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script language="javascript" type="text/javascript">

  $(document).ready(function(){
    alert("hello");
  });

</script>
</body>
</html>

