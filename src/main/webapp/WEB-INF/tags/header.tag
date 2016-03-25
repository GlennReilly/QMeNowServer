<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="logoPath" required="false" %>
<%@ attribute name="businessName" required="false" %>
<%@ attribute name="headerColour" required="false" %>

<div style="padding-bottom: 4vh; width:40vw;">
    <c:if test="${not empty logoPath}">
        <div id="logoDiv" style="float:left;width:100%;  padding: 20px;background-color:${headerColour};">
            <img id="logoImg" style="width: 100px;" src="/resources/images/<c:out value="${logoPath}"/>">
        </div>
    </c:if>
    <div class="businessName"><c:out value="${businessName}" /></div>
</div>