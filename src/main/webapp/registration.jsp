<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="registration.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col"></div>
        <div class="col">
            <form class="form-signin mt-4" action="<c:url value="/registration.do"/>" method="post">
                <c:if test="${requestScope.registrationError ne null}">
                    <div class="alert alert-danger" role="alert">
                            ${requestScope.registrationError}
                    </div>
                </c:if>
                <div class="text-center">
                    <h1 class="h3 mb-3 font-weight-normal">
                        <fmt:message key="registration.formTitle" bundle="${lang}"/>
                    </h1>
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-address-card fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="login" class="form-control"
                           placeholder="<fmt:message key="registration.login" bundle="${lang}"/>" required autofocus>
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-address-card fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="first_name" class="form-control"
                           placeholder="<fmt:message key="registration.firstName" bundle="${lang}"/>" required>
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-address-card fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="second_name" class="form-control"
                           placeholder="<fmt:message key="registration.secondName" bundle="${lang}"/>" required>
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-address-card fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="passport_code" class="form-control"
                           placeholder="<fmt:message key="registration.passportCode" bundle="${lang}"/>" required>
                </div>
                <div class="btn-group btn-group-toggle mb-3 btn-group-sm" data-toggle="buttons">
                    <label class="btn btn-secondary active">
                        <input type="radio" name="gender" value="M" autocomplete="off" required>
                        <fmt:message key="registration.male" bundle="${lang}"/>
                    </label>
                    <label class="btn btn-secondary">
                        <input type="radio" name="gender" value="F" autocomplete="off">
                        <fmt:message key="registration.female" bundle="${lang}"/>
                    </label>
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-envelope fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="email" type="email" class="form-control"
                           placeholder="<fmt:message key="registration.email" bundle="${lang}"/>">
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-phone fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="phone" type="tel" class="form-control"
                           placeholder="<fmt:message key="registration.phoneNumber" bundle="${lang}"/>" required>
                </div>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="fa fa-key fa-fw" aria-hidden="true"></i>
                        </span>
                    </div>
                    <input name="password" type="password" class="form-control"
                           placeholder="<fmt:message key="registration.password" bundle="${lang}"/>" required>
                </div>

                <input type="hidden" name="command" value="registration">

                <button class="btn btn-sm btn-primary btn-block" type="submit">
                    <fmt:message key="registration.button" bundle="${lang}"/>
                </button>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
