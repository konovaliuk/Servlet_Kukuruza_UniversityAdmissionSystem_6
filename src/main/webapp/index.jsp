<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="index.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>

<div class="container">
    <c:if test="${sessionScope.user eq null}">
        <div class="jumbotron my-4 text-center">
            <h1 class="display-4"><fmt:message key="index.welcome" bundle="${lang}"/></h1>
        </div>
    </c:if>
    <c:if test="${sessionScope.user ne null}">
        <div class="jumbotron bg-success shadow my-4 pb-4 text-center">
            <h1 class="display-4 text-white">
                <fmt:message key="index.hello" bundle="${lang}"/>, ${sessionScope.user.firstName}!
            </h1>
            <p class="lead text-white-50">
                <fmt:message key="index.advice" bundle="${lang}"/>
            </p>
        </div>
    </c:if>
</div>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
