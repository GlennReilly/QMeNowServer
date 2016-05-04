<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="logoPath" required="false" %>
<%@ attribute name="businessName" required="false" %>
<%@ attribute name="headerColour" required="false" %>

<div style="height:100px; padding-bottom: 4vh; width:40vw; background-color:${headerColour};">
    <c:if test="${not empty logoPath}">
        <div id="logoDiv" style="float:left;width:20%;  padding: 20px;">
            <img id="logoImg" style="width: 100px;" src="/resources/images/<c:out value="${logoPath}"/>">
        </div>
    </c:if>
    <div class="businessName" style="color:${backgroundColourHexCode}"><c:out value="${businessName}" /></div>
</div>