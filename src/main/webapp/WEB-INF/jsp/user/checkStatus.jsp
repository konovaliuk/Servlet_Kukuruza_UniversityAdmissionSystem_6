<%@ page contentType="text/html;charset=UTF-8" import="ua.company.training.kukuruza.util.UserStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="checkStatus.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<div class="container">
    <c:if test="${sessionScope.user.userStatusId eq UserStatus.UNKNOWN.id}">
        <br>
        <div class="alert alert-info text-center" role="alert">
            <h2><fmt:message key="checkStatus.noInfo" bundle="${lang}"/></h2>
        </div>
    </c:if>

    <c:if test="${sessionScope.user.userStatusId eq UserStatus.SUCCESS.id}">
        <br>
        <div class="alert alert-success text-center" role="alert">
            <h2>
                <fmt:message key="checkStatus.success" bundle="${lang}"/>
                <i class="fa fa-check"></i>
            </h2>
        </div>
    </c:if>

    <c:if test="${sessionScope.user.userStatusId eq UserStatus.FAIL.id}">
        <br>
        <div class="alert alert-danger text-center" role="alert">
            <h2>
                <fmt:message key="checkStatus.fail" bundle="${lang}"/>
                <i class="fa fa-times" aria-hidden="true"></i>
            </h2>
        </div>
    </c:if>
</div>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
