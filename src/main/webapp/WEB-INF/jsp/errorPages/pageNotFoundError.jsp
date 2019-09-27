<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"
         import="ua.company.training.kukuruza.util.UserType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="pageNotFoundError.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<div class="container">
    <div class="jumbotron bg-dark mt-4 pb-4 text-center">
        <h3 class="display-4 text-white">
            <fmt:message key="pageNotFoundError.message" bundle="${lang}"/>
        </h3>
        <p class="lead text-white-50">
            <fmt:message key="pageNotFoundError.advice" bundle="${lang}"/>
        </p>
        <hr class="my-4">
        <c:if test="${(sessionScope.user eq null) or (sessionScope.user.userTypeId eq UserType.STUDENT.id)}">
            <a class="btn btn-light font-weight-bold" href="<c:url value="/index.jsp"/>" role="button">
                <fmt:message key="pageNotFoundError.button" bundle="${lang}"/>
            </a>
        </c:if>
        <c:if test="${sessionScope.user.userTypeId eq UserType.ADMIN.id}">
            <a class="btn btn-light font-weight-bold" href="<c:url value="/admin/adminPage.jsp"/>"
               role="button">
                <fmt:message key="pageNotFoundError.button" bundle="${lang}"/>
            </a>
        </c:if>
    </div>
</div>
</body>
</html>
