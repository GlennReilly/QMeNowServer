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

  <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/style1.css'/>" />
</head>

<form:form method="post" action="/FlexibleUIConfig/save/${command.currentAppConfig.id}">
<%--<form:form method="POST" action="/FlexibleUIConfig/save/23">--%>
EditConfigForms<br/>
${command.currentAppConfig.id}<br/>
Select picture:
  <br/>
Select pageTitle:
  <br/>
Select number of buttons:
  <form:select path="numberOfButtonsRequired">
     <form:option value="0" label="Please select" />
       <c:forEach begin="1" end="20" var="i">
      <form:option value="${i}" />
    </c:forEach>
  </form:select>
  <div id="divForAllButtonConfigs" class="divForAllButtonConfig"></div>
  <input type="submit" value="Submit"/>

  <div id="hiddenElements" style="visibility: hidden;">
    <div id="divForBtnStyleTemplate" class="divForEachButtonConfig">
        <div>Select button style:
          <form:select path="selectedButtonStyle"  >
            <form:option value="0" label="Please select button style" />
            <c:forEach begin="1" end="5" var="i">
              <form:option value="${i}" />
            </c:forEach>
          </form:select>
        </div>
        <div>Select button destination:
            <form:select path="selectedButtonDestination">
                <form:option value="0" label="Please select button destination" />
                <c:forEach begin="1" end="5" var="i">
                    <form:option value="${i}" />
                </c:forEach>
            </form:select>
        </div>

    </div>
  </div>

</form:form>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/editConfigForm.js'/>" ></script>
</body>
</html>

