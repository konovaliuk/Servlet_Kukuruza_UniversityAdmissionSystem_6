<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="signIn.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col"></div>
        <div class="col">
            <form class="form-signin mt-4" method="post" action="<c:url value="/signIn.do"/>">
                <c:if test="${requestScope.signInError ne null}">
                    <div class="alert alert-danger" role="alert">
                            ${requestScope.signInError}
                    </div>
                </c:if>
                <div class="text-center">
                    <h1 class="h3 mb-3 font-weight-normal">
                        <fmt:message key="signIn.signIn" bundle="${lang}"/>
                    </h1>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-address-card fa-fw"
                                                          aria-hidden="true"></i></span>
                    </div>
                    <input name="login" class="form-control"
                           placeholder="<fmt:message key="signIn.login" bundle="${lang}"/>" required autofocus>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-key fa-fw" aria-hidden="true"></i></span>
                    </div>
                    <input name="password" type="password" class="form-control"
                           placeholder="<fmt:message key="signIn.password" bundle="${lang}"/>" required>
                </div>
                <input type="hidden" name="command" value="signIn">
                <button class="btn btn btn-primary btn-block" type="submit">
                    <fmt:message key="signIn.button" bundle="${lang}"/><i class="fa fa-sign-in" aria-hidden="true"></i>
                </button>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
