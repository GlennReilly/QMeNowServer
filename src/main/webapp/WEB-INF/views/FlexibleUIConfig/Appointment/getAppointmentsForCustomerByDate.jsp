<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery-ui.min.css'/>" />
</head>
<body>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="./get/" method="get">
    <div>
        <div>
<%--            <div class="label2">
                <label>user id:</label><form:input path="customerId" />
            </div>
            <div class="label2">
                <label>first name:</label><form:input path="firstName" />
            </div>
            <div class="label2">
                <label>last name:</label><form:input path="lastName" />
            </div>--%>
            <div class="label2">
                <label>from date:</label><form:input path="strFromDate" />
            </div>
            <div class="label2">
                <label>to date:</label><form:input path="strToDate" />
            </div>
        </div>
        <div class="label2">
            <input type="submit">
        </div>
        <c:forEach items="${command.appointmentResultList}" var="appointmentResult" >
            <c:out value="${appointmentResult.appointment.messageToCustomer}" />
        </c:forEach>
    </div>
</form:form>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/jquery-ui.min.js'/>" ></script>
<script>
    $(function() {
        $( "#strFromDate" ).datepicker({dateFormat: "dd-mm-yy"});
        $( "#strToDate" ).datepicker({dateFormat: "dd-mm-yy"});
    });
</script>

</body>
</html>
