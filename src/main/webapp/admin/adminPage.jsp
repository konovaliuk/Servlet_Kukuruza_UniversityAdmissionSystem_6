<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="adminPage.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/admin/navbar.jsp" %>
<div class="container">
    <div class="jumbotron bg-success shadow my-4 pb-4 text-center">
        <h1 class="display-4 text-white">
            <fmt:message key="adminPage.dear" bundle="${lang}"/> ${sessionScope.user.firstName},
            <fmt:message key="adminPage.welcome" bundle="${lang}"/>!</h1>
        <p class="lead text-white-50"><fmt:message key="adminPage.advice" bundle="${lang}"/>!</p>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
