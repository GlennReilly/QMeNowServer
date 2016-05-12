<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 11/10/15
  Time: 7:58 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery-ui.min.css'/>" />
  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/jquery.ptTimeSelect.css'/>" />
</head>
<body style="background-color: ${backgroundColourHexCode}">
<tags:menu></tags:menu>
<tags:header
        logoPath="${logoFileName}"
        businessName="${businessName}">
</tags:header>

<%--<div class="pageTitle">${pageTitle}</div>--%>
<div class="pageMessage">${message}</div>
<br/>

<form:form modelAttribute="addAppointmentFormHelper" action="/FlexibleUIConfig/appointment/addOrUpdate/" method="post" >

    <fieldset class="userHomeFieldSet">
        <legend>${pageTitle}</legend>
        <div>
            <div>
                <div class="label2">
                    <c:if test="${addAppointmentFormHelper.appointment.id > 0}">
                        <span style="font-size: 20pt; font-weight: bold;"><c:out
                                value="${addAppointmentFormHelper.appointment.appointmentTypePrefix}${addAppointmentFormHelper.appointment.id}"/></span>
                    </c:if>
                </div>
                <div class="label2">
                    <label>appointment date:</label>
                    <form:input path="appointment.strAppointmentDate" id="strAppointmentDate"/>
                    <form:errors path="appointment.strAppointmentDate" cssClass="error"/>
                </div>

                <div class="label2">
                    <label>appointment time:</label>
                    <form:input path="appointment.strAppointmentTime" id="strAppointmentTime"/>
                    <form:errors path="appointment.strAppointmentTime" cssClass="error"/>
                </div>

                <div class="label2">
                    <label>status:</label>
                    <form:select path="appointment.status" id="ddlStatus" onchange="loadStatusColour(this.value);">
                        <form:option value="0" label="Please select"/>
                        <form:options items="${addAppointmentFormHelper.appointmentStatusList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <div id="divStatusColour"
                         style="height:20px; width: 20px; float:right; margin-left: 5px; background-color: <c:out
                                 value='${addAppointmentFormHelper.appointment.statusHexCode}'/>;"></div>
                    <form:errors path="appointment.status" cssClass="error"/>
                    <span class="jserror" style="color:red; display: none;">required field</span>
                </div>

                <div class="label2" style="margin-bottom: 50px;">
                    <label>complete:</label><form:checkbox path="appointment.isComplete"
                                                           id="isCompleteCheck"></form:checkbox>
                </div>


                <div class="label2">
                    <label>location:</label>
                    <form:select path="appointment.locationId" id="ddlLocation"
                                 onchange="loadLocationColour(this.value);">
                        <form:option value="0" label="Please select"/>
                        <form:options items="${addAppointmentFormHelper.activeLocations}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <div id="divLocationColour"
                         style="height:20px; width: 20px; float:right; margin-left: 5px; background-color: <c:out
                                 value='${addAppointmentFormHelper.appointment.locationHexCode}'/>;"></div>
                    <form:errors path="appointment.locationId" cssClass="error"/>
                    <span class="jserror" style="color:red; display: none;">required field</span>
                </div>

                <div class="label2">
                    <label>appointment type:</label>
                    <form:select path="appointment.appointmentTypeId" id="ddlAppointmentType"
                                 onchange="loadAppointmentTypeColour(this.value);">
                        <form:option value="0" label="Please select"/>
                        <form:options items="${addAppointmentFormHelper.appointmentTypeList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <div id="divAppointmentTypeColour"
                         style="height:20px; width: 20px; float:right; margin-left: 5px; background-color: <c:out
                                 value='${addAppointmentFormHelper.appointment.appTypeHexCode}'/>;"></div>
                    <form:errors path="appointment.appointmentTypeId" cssClass="error"/>
                    <span class="jserror" style="color:red; display: none;">required field</span>
                </div>

                <div class="label2">
                    <label>message to Customer:</label><form:input id="txtMessageToCustomer"
                                                                   path="appointment.messageToCustomer"/>
                </div>
<%--                <div>
                    <c:choose>
                        <c:when test="${empty addAppointmentFormHelper.appointment.id || addAppointmentFormHelper.appointment.id eq 0 }">
                            <c:set var="buttonText" value="Save New Appointment"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buttonText" value="Update Appointment"/>
                        </c:otherwise>
                    </c:choose>
                </div>--%>
                <div class="label2">
                    <c:set var="buttonText" value="Save Appointment"/>
                    <input type="submit" value="${buttonText}" id="btnUpdateOrAddAppointment">
                </div>
            </div>
        </div>
    </fieldset>



  </form:form>
<tags:jsIncludes logoPath="${logoFileName}"></tags:jsIncludes>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/jquery-ui.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/jquery.ptTimeSelect.js'/>" ></script>
<script>
    $(function() {
        $( "#strAppointmentDate" ).datepicker({dateFormat: "dd-mm-yy"});
        $("#strAppointmentTime").ptTimeSelect();
        doCompleteCheck();
    });
    function doCompleteCheck() {
        if ($("#isCompleteCheck").is(":checked")) {
            $("#strAppointmentDate").prop("disabled", true);
            $("#strAppointmentTime").prop("disabled", true);
            $("#ddlStatus").prop("disabled", true);
            $("#ddlLocation").prop("disabled", true);
            $("#ddlAppointmentType").prop("disabled", true);
            $("#txtMessageToCustomer").prop("disabled", true);
        } else {
            $("#strAppointmentDate").prop("disabled", false);
            $("#strAppointmentTime").prop("disabled", false);
            $("#ddlStatus").prop("disabled", false);
            $("#ddlLocation").prop("disabled", false);
            $("#ddlAppointmentType").prop("disabled", false);
            $("#txtMessageToCustomer").prop("disabled", false);
        }
    }
    $("#isCompleteCheck").change(function(){
        doCompleteCheck();
    });
</script>

<script>
    var StatusBackgroundColourMap = {};
    <c:forEach items="${addAppointmentFormHelper.appointmentStatusList}" var="status" varStatus="loop">
        StatusBackgroundColourMap['${status.id}'] = '<c:out value="${status.backgroundColourHexCode}" />';
    </c:forEach>

    function loadStatusColour(statusId){
        if(statusId in StatusBackgroundColourMap){
            $('#divStatusColour').css("background-color",StatusBackgroundColourMap[statusId]);
        }
        else{
            $('#divStatusColour').css("background-color","");
        }

        if(statusId == 0){
            if ($('#divStatusColour').next(".error").is(":visible")) {
                $('#divStatusColour').next(".error").css("display", "inline-block");
            }
            else{
                    $('#divStatusColour').next(".jserror").css("display", "inline-block");
                }
        }
        else{
            $('#divStatusColour').next(".error").css("display", "none");
            $('#divStatusColour').next(".jserror").css("display", "none");
        }
    }


    var LocationBackgroundColourMap = {};
    <c:forEach items="${addAppointmentFormHelper.activeLocations}" var="location" varStatus="loop">
    LocationBackgroundColourMap['${location.id}'] = '<c:out value="${location.backgroundColourHexCode}" />';
    </c:forEach>

    function loadLocationColour(locationId){
        if(locationId in LocationBackgroundColourMap){
            $('#divLocationColour').css("background-color",LocationBackgroundColourMap[locationId]);
        }
        else{
            $('#divLocationColour').css("background-color","");
        }

        if(locationId == 0){
            if ($('#divLocationColour').next(".error").is(":visible")) {
                $('#divLocationColour').next(".error").css("display", "inline-block");
            }
            else{
                $('#divLocationColour').next(".jserror").css("display", "inline-block");
            }
        }
        else{
            //alert("hide error message");
            $('#divLocationColour').next(".error").css("display", "none");
            $('#divLocationColour').next(".jserror").css("display", "none");
        }
    }


    var AppointmentTypeBackgroundColourMap = {};
    <c:forEach items="${addAppointmentFormHelper.appointmentTypeList}" var="appointmentType" varStatus="loop">
    AppointmentTypeBackgroundColourMap['${appointmentType.id}'] = '<c:out value="${appointmentType.backgroundColourHexCode}" />';
    </c:forEach>

    function loadAppointmentTypeColour(appointmentTypeId){
        if(appointmentTypeId in AppointmentTypeBackgroundColourMap){
            $('#divAppointmentTypeColour').css("background-color",AppointmentTypeBackgroundColourMap[appointmentTypeId]);
        }
        else{
            $('#divAppointmentTypeColour').css("background-color","");
        }

        if(appointmentTypeId == 0) {
            if ($('#divAppointmentTypeColour').next(".error").is(":visible"))
            {
                $('#divAppointmentTypeColour').next(".error").css("display", "inline-block");
            }
            else{
                $('#divAppointmentTypeColour').next(".jserror").css("display", "inline-block");
            }
        }
        else{
            $('#divAppointmentTypeColour').next(".error").css("display", "none");
            $('#divAppointmentTypeColour').next(".jserror").css("display", "none");
        }
    }

</script>

</body>
</html>
