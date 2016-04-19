<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery-ui.min.css'/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/DataTables/datatables.min.css'/>" />
    <meta http-equiv="refresh" content="30"/>
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}"
        businessName="${businessName}">
</tags:header>

<div class="pageTitle">${pageTitle}</div>
<div class="pageMessage">${message}</div>
<form:form action="/FlexibleUIConfig/appointment/get" method="get">
    <input type="hidden" id="customerId" value="${customerId}">
    <form:hidden path="customerId" />
        <div>
            <div class="pageMessage" style="margin-left: 150px;">(leave these fields blank to search the current day)</div>
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
        <table id="resultsTable">
            <thead>
            <tr class="resultsTable">
                <th>
                    Appointment Date
                </th>
                <th>
                    Ref#
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
            <c:forEach items="${command.appointmentResultList}" var="appointmentResult"  >
                <tr>
                    <td>
                        <fmt:formatDate pattern="E d MMM h:mma" value="${appointmentResult.appointment.appointmentDate}" />
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.appointmentTypePrefix}${appointmentResult.appointment.id}" />
                        <c:if test="${not empty appointmentResult.appointment.appTypeHexCode}">
                            <img style="width:22px; height:22px; background-color: ${appointmentResult.appointment.appTypeHexCode}"></img>
                        </c:if>
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.statusName}" />
                        <c:if test="${not empty appointmentResult.appointment.statusHexCode}">
                            <img style="width:22px; height:22px; background-color: ${appointmentResult.appointment.statusHexCode}"></img>
                        </c:if>
                    </td>
                    <td>
                        <c:out value="${appointmentResult.appointment.locationName}" />
                        <c:if test="${not empty appointmentResult.appointment.locationHexCode}">
                            <img style="width:22px; height:22px; background-color: ${appointmentResult.appointment.locationHexCode}"></img>
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
                        <c:if test="${appointmentResult.appointment.isComplete == true}"> Yes </c:if>
                        <c:if test="${appointmentResult.appointment.isComplete == false}"> No </c:if>
                    </td>
                    <td style="padding-left: 10px;">
                        <a href="<spring:url value='/FlexibleUIConfig/appointment/get/${appointmentResult.appointment.id}' />" >view</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <div  style="text-align: center; margin-top: 40px; color: ${headerColour}; font-weight:bold; font-size: 0.8em;" >These results auto-refresh every 30 seconds</div>
    </c:if>


</form:form>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/jquery-ui.min.js'/>" ></script>


<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
<script type="text/javascript" src="<spring:url value='/resources/DataTables/datatables.min.js'/>"></script>
<script>
    $(function() {
        $( "#strFromDate" ).datepicker({dateFormat: "dd-mm-yy"});
        $( "#strToDate" ).datepicker({dateFormat: "dd-mm-yy"});

        $("#resultsTable").DataTable();
    });
</script>
</body>
</html>
