<%--
  Created by IntelliJ IDEA.
  User: glenn
  Date: 9/08/15
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <configName></configName>

  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>

<form:form method="post" action="/FlexibleUIConfig/save/${command.currentAppConfig.id}">
<div class="divMain">
    <div class="divTitle">
        EditConfigForms
    </div>
    <div class="divCustomerDetails">
        ${command.currentAppConfig.id}
    </div>

  <br/>

  <br/>
<div class="labelColumn">
    <div>
        <span class="formLabel">Enter pageTitle:</span>
    </div>
    <div>
        <span class="formLabel">Select picture:</span>
    </div>
    <div>
        <span class="formLabel">Select number of buttons:</span>
    </div>
</div>
<div class="formElementsColumn">
    <div>
        <form:input path="pageTitle" />
    </div>
    <div>
        <form:select path="headerImagePath">
            <form:option value="0" label="Please select" />
        </form:select>
    </div>
    <div>
      <form:select path="numberOfButtonsRequired">
         <form:option value="0" label="Please select" />
           <c:forEach begin="1" end="20" var="i">
          <form:option value="${i}" />
        </c:forEach>
      </form:select>
    </div>
</div>
  <div id="divForAllButtonConfigs" class="divForAllButtonConfig"></div>

    <div id="divForLinks">
        <a href="<spring:url value='../buttonStyle/add' />" >Add a new button style</a>
        <br/>
        <a href="<spring:url value='../buttonDestination/add' />" >Add a new button destination</a>
    </div>
    <div class="formButtonColumn">
        <div class="divForSubmitButton">
            <input type="submit" value="Submit"/>
        </div>
    </div>

<!-- the below elements are dynamically inserted into the layout by js -->

  <div id="hiddenElements" style="visibility: hidden;">
    <div id="divForBtnStyleTemplate" class="divForEachButtonConfig">
        <div class="BtnRowStyleTitle">button #</div>
        <div class="labelColumn">
            <div>
                <span class="formLabel">Enter button text:</span>
            </div>
            <div>
                <span class="formLabel">Select button style:</span>
            </div>
            <div>
                <span class="formLabel">Select button destination:</span>
            </div>
        </div>

        <div class="formElementsColumn">
            <div>
                <form:input path="buttonTexts" />
            </div>
            <div>
                <form:select path="selectedButtonStyles">
                    <form:option value="0" label="Please select"  />
                    <form:options items="${command.availableButtonStyles}" itemValue="Id" itemLabel="Name" />
                </form:select>
            </div>
            <div>
                <form:select path="selectedButtonDestinations">
                    <form:option value="0" label="Please select" />
                    <c:forEach begin="1" end="5" var="i">
                        <form:option value="${i}" />
                    </c:forEach>
                </form:select>
            </div>
        </div>
    </div>
  </div>
</div>

    <!-- the above elements are dynamically inserted into the layout by js -->

</form:form>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/editConfigForm.js'/>" ></script>
</body>
</html>

