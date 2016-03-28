<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="logoPath" required="false" %>
<script src="<spring:url value='/resources/scripts/jquery-2.1.4.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/jquery-ui.min.js'/>" ></script>
<script src="<spring:url value='/resources/scripts/common.js'/>" ></script>
<c:if test="${not empty logoPath}">
<script>
    checkLogoExists("${logoPath}");
</script>
</c:if>
