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
    <style>
        #resultsTable_wrapper{
            width: 75%;
        }
    </style>
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
                <th style="width: 100px;">
                    Appointment Date
                </th>
                <th style="width: 100px;">
                    Ref#
                </th>
                <th style="width: 100px;">
                    Status
                </th>
                <th style="width: 100px;">
                    Location
                </th>
                <th>
                    Type
                </th>
                <th>
                    Customer Id
                </th>
                <th>
                    Customer name
                </th>
                <th>
                    Message to customer
                </th>
                <th>
                    Complete?
                </th>
                <th style="width: 100px;">
                    Check-in Date
                </th>
                <th>
                    View
                </th>
            </tr>
            </thead>
            <c:forEach items="${command}" var="appointmentResult"  >
                <tr>
                    <td style="text-align: right;">
                        <fmt:formatDate pattern="E d MMM h:mma" value="${appointmentResult.appointment.appointmentDate}" />
                    </td>
                    <td style="text-align: right;">
                        <c:out value="${appointmentResult.appointment.appointmentTypePrefix}${appointmentResult.appointment.id}" />
                        <c:if test="${not empty appointmentResult.appointment.appTypeHexCode}">
                            <img style="width:22px; height:22px; background-color: ${appointmentResult.appointment.appTypeHexCode}"></img>
                        </c:if>
                    </td>
                    <td style="text-align: right;">
                        <c:out value="${appointmentResult.appointment.statusName}" />
                        <c:if test="${not empty appointmentResult.appointment.statusHexCode}">
                            <img style="width:22px; height:22px; background-color: ${appointmentResult.appointment.statusHexCode}"></img>
                        </c:if>
                    </td>
                    <td style="text-align: right;">
                        <c:out value="${appointmentResult.appointment.locationName}" />
                        <c:if test="${not empty appointmentResult.appointment.locationHexCode}">
                            <img style="width:22px; height:22px; background-color: ${appointmentResult.appointment.locationHexCode}"></img>
                        </c:if>
                    </td>
                    <td style="text-align: right;">
                        <c:out value="${appointmentResult.appointment.appointmentTypeName}" />
                    </td>
                    <td style="text-align: center;">
                        <c:out value="${appointmentResult.customer.id}" />
                    </td>
                    <td style="text-align: right;">
                        <c:out value="${appointmentResult.customer.name}" />
                    </td>
                    <td style="text-align: right;">
                        <c:out value="${appointmentResult.appointment.messageToCustomer}" />
                    </td>
                    <td style="text-align: center;">
                        <c:if test="${appointmentResult.appointment.isComplete == true}"> Yes </c:if>
                        <c:if test="${appointmentResult.appointment.isComplete == false}"> No </c:if>
                    </td>
                    <td style="text-align: right;">
                        <fmt:formatDate pattern="E d MMM h:mma" value="${appointmentResult.appointment.checkInDate}" />
                    </td>
                    <td style="text-align: center;">
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
