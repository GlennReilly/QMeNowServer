<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="logoPath" required="false" %>
<%@ attribute name="businessName" required="false" %>
<div style="padding-bottom: 4vh; width:60vw;">
    <c:if test="${not empty logoPath}">
        <div style="float:left;">
            <img style="width: 150px;" src="/resources/images/<c:out value="${logoPath}"/>">
        </div>
    </c:if>
    <div class="businessName"><c:out value="${businessName}" /></div>
</div>