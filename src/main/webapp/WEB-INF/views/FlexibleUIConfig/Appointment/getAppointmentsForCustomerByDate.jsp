<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery-ui.min.css'/>" />
</head>
<body>
<tags:menu></tags:menu>
<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="/FlexibleUIConfig/appointment/get" method="post">
    <input type="hidden" id="customerId" value="${customerId}">
    <form:hidden path="customerId" />
        <div>
            <div class="label2">
                <label>from date:</label><form:input path="strFromDate" />
            </div>
            <div class="label2">
                <label>to date:</label><form:input path="strToDate" />
            </div>
        </div>
        <div class="label2">
            <input type="submit" value="Search">
        </div>

    <c:if test="${empty command.appointmentResultList}">
        <div style="display: table; padding-top: 1vh;">
            Choose from the above options and click 'Search' to continue.
        </div>
    </c:if>
    <c:if test="${!empty command.appointmentResultList}">
        <div class="pageSubHeading" style="display: table;">
            Search Results:
        </div>
        <table>
            <thead>
            <tr>
                <th>
                    Appointment Date
                </th>
                <th>
                    Appointment #
                </th>
                <th>
                    Customer name
                </th>
                <th>
                    Message to customer
                </th>
                <th>
                    View
                </th>
            </tr>
            </thead>
            <c:forEach items="${command.appointmentResultList}" var="appointmentResult"  >
                <tr>
                    <td>
                        <c:out value="${appointmentResult.appointment.appointmentDate}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.id}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.customer.name}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.messageToCustomer}" />
                    </td>
                    <td>
                        <a href="<spring:url value='/FlexibleUIConfig/appointment/get/${appointmentResult.appointment.id}' />" >view</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </c:if>


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
