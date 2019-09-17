<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="accessDenied.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<div class="container">
    <div class="jumbotron bg-dark mt-4 pb-4 text-center">
        <h3 class="display-4 text-white">
            <fmt:message key="accessDenied.message" bundle="${lang}"/>
        </h3>

        <p class="lead text-white-50">
            <fmt:message key="accessDenied.advice" bundle="${lang}"/>
        </p>
        <hr class="my-4">
        <a class="btn btn-light font-weight-bold" href="${pageContext.request.contextPath}/index.jsp" role="button">
            <fmt:message key="accessDenied.button" bundle="${lang}"/>
        </a>
    </div>
</div>
</body>
</html>
