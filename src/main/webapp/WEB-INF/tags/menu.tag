<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="logoPath" required="false" %>
<div style="padding-bottom: 4vh; width:30vw;">
    <a href="<spring:url value='/'/>" style="float: left;">|  Home  |</a>
    <a href="<spring:url value='/logout'/>" style="float: right;">|  Logout  |</a>
</div>