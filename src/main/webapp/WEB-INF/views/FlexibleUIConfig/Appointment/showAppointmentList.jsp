<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery-ui.min.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/DataTables/datatables.min.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="/QMeNow/appointment/get" method="post">

    <div class="pageSubHeading" style="display: table;">
            Search Results:
        </div>
        <table id="resultsTable" data-page-length='5'>
            <thead>
            <tr class="resultsTable">
                <th>
                    Appointment Date
                </th>
                <th colspan="2">
                    Appointment #
                </th>
                <th>
                    Status
                </th>
                <th>
                    Location
                </th>
                <th>
                    Type
                </th>
                <th>
                    Customer name
                </th>
                <th>
                    Message to customer
                </th>
                <th>
                    is Complete?
                </th>
                <th>
                    View
                </th>
            </tr>
            </thead>
            <c:forEach items="${command}" var="appointmentResult"  >
                <tr>
                    <td>
                        <c:out value="${appointmentResult.appointment.appointmentDate}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.appointmentTypePrefix}${appointmentResult.appointment.id}" />
                    </td>
                    <td>
                        <c:if test="${not empty appointmentResult.appointment.appTypeHexCode}">
                            <div style="width:22px; height:22px; background-color: ${appointmentResult.appointment.appTypeHexCode}"></div>
                        </c:if>
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.statusName}" />
                    </td>
                    <td>
                        <c:if test="${not empty appointmentResult.appointment.statusHexCode}">
                            <div style="width:22px; height:22px; background-color: ${appointmentResult.appointment.statusHexCode}"></div>
                        </c:if>
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.locationName}" />
                    </td>
                    <td>
                        <c:if test="${not empty appointmentResult.appointment.locationHexCode}">
                            <div style="width:22px; height:22px; background-color: ${appointmentResult.appointment.locationHexCode}"></div>
                        </c:if>
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.appointmentTypeName}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.customer.name}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.messageToCustomer}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.isComplete}" />
                    </td>
                    <td style="padding-left: 10px;">
                        <a href="<spring:url value='/appointment/get/${appointmentResult.appointment.id}' />" >view</a>
                    </td>
                </tr>
            </c:forEach>
        </table>



</form:form>

<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
<script type="text/javascript" src="<spring:url value='/resources/DataTables/datatables.min.js'/>"></script>
<script>
    $(function(){
        $("#resultsTable").DataTable();
    });
</script>
</body>
</html>
