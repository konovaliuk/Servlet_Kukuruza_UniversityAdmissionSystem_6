<%@ page contentType="text/html;charset=UTF-8" import="ua.company.training.kukuruza.controller.util.UserStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="sendNotification.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/admin/navbar.jsp" %>
<div class="container">
    <h2 class="mt-2"><fmt:message key="sendNotification.title" bundle="${lang}"/></h2>

    <form action="findUser.do" class="my-4" method="post">
        <input type="hidden" name="command" value="findUserSendingNotification">

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">
                    <fmt:message key="sendNotification.searchUser" bundle="${lang}"/>
                </span>
            </div>

            <input type="text" name="first_name"
                   placeholder="<fmt:message key="sendNotification.firstName" bundle="${lang}"/>"
                   class="form-control" required autofocus>

            <input type="text" name="second_name"
                   placeholder="<fmt:message key="sendNotification.secondName" bundle="${lang}"/>"
                   class="form-control" required>

            <div class="input-group-append">
                <button class="btn btn-outline-secondary"><i class="fa fa-search"></i></button>
            </div>
        </div>
    </form>

    <c:if test="${requestScope.success ne null}">
        <div class="alert alert-success rounded-0">
            <fmt:message key="sendNotification.done" bundle="${lang}"/>!
        </div>
    </c:if>

    <c:if test="${requestScope.firstName ne null and requestScope.secondName ne null}">
        <div class="alert alert-danger rounded-0">
            <fmt:message key="sendNotification.wrongUser" bundle="${lang}"/>:
                ${requestScope.firstName} ${requestScope.secondName}
        </div>
    </c:if>

    <c:if test="${not empty requestScope.users}">
        <h3 class="my-4"><fmt:message key="sendNotification.users" bundle="${lang}"/></h3>

        <div class="row font-weight-bold border-bottom mb-2">
            <div class="col-sm"><fmt:message key="sendNotification.firstName" bundle="${lang}"/></div>
            <div class="col-sm"><fmt:message key="sendNotification.secondName" bundle="${lang}"/></div>
            <div class="col-sm"><fmt:message key="sendNotification.passportCode" bundle="${lang}"/></div>
            <div class="col-sm"></div>
        </div>

        <c:forEach items="${requestScope.users}" var="user">
            <form action="setUserStatus.do" method="post">
                <input type="hidden" name="command" value="setUserStatus">

                <div class="row border-bottom mb-2 pb-2">
                    <div class="col-sm text-break">${user.firstName}</div>
                    <div class="col-sm text-break">${user.secondName}</div>
                    <div class="col-sm text-break">${user.passportCode}</div>
                    <div class="col-sm text-break">
                        <div class="input-group input-group-sm">
                            <select class="form-control" name="userStatus" required>
                                <option value=""></option>
                                <option value="${UserStatus.SUCCESS.id}">
                                    <fmt:message key="sendNotification.enrolled" bundle="${lang}"/>
                                </option>
                                <option value="${UserStatus.FAIL.id}">
                                    <fmt:message key="sendNotification.notEnrolled" bundle="${lang}"/>
                                </option>
                            </select>
                            <input type="hidden" name="user_id" value="${user.id}">
                            <div class="input-group-append">
                                <button class="btn btn-primary">
                                    <fmt:message key="sendNotification.apply" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </c:forEach>
    </c:if>
</div>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
