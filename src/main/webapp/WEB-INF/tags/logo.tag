<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="logoPath" required="false" %>
<div style="padding-bottom: 4vh; width:30vw;">
    <c:if test="${not empty logoPath}">
        <img style="width: 200px;" src="<c:out value="${logoPath}"/>">
    </c:if>
</div>