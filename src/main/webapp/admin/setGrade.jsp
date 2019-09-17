<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="setGrade.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/admin/navbar.jsp" %>
<div class="container">
    <h2 class="mt-2"><fmt:message key="setGrade.title" bundle="${lang}"/></h2>
    <form action="findUser.do" class="my-4" method="post">
        <input type="hidden" name="command" value="findUserSettingGrade">

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">
                    <fmt:message key="setGrade.searchUser" bundle="${lang}"/>
                </span>
            </div>

            <input type="text" name="first_name"
                   placeholder="<fmt:message key="setGrade.firstName" bundle="${lang}"/>"
                   class="form-control" required autofocus>

            <input type="text" name="second_name"
                   placeholder="<fmt:message key="setGrade.secondName" bundle="${lang}"/>"
                   class="form-control" required>

            <div class="input-group-append">
                <button class="btn btn-outline-secondary"><i class="fa fa-search"></i></button>
            </div>
        </div>
    </form>

    <c:if test="${requestScope.success ne null}">
        <div class="alert alert-success rounded-0">
            <fmt:message key="setGrade.done" bundle="${lang}"/>!
        </div>
    </c:if>

    <c:if test="${requestScope.firstName ne null and requestScope.secondName ne null}">
        <div class="alert alert-danger rounded-0">
            <fmt:message key="setGrade.wrongUser" bundle="${lang}"/>:
                ${requestScope.firstName} ${requestScope.secondName}
        </div>
    </c:if>

    <c:if test="${not empty requestScope.users}">
        <h3 class="my-4"><fmt:message key="setGrade.users" bundle="${lang}"/></h3>

        <div class="row font-weight-bold border-bottom mb-2">
            <div class="col-sm">
                <fmt:message key="setGrade.firstName" bundle="${lang}"/>
            </div>
            <div class="col-sm">
                <fmt:message key="setGrade.secondName" bundle="${lang}"/>
            </div>
            <div class="col-sm">
                <fmt:message key="setGrade.passportCode" bundle="${lang}"/>
            </div>
            <div class="col-sm">
                <fmt:message key="setGrade.subject" bundle="${lang}"/>
            </div>
            <div class="col-sm">
                <fmt:message key="setGrade.grade" bundle="${lang}"/>
            </div>
        </div>

        <c:forEach items="${requestScope.users}" var="user">
            <form action="setGrade.do" method="post">
                <input type="hidden" name="command" value="setGrade">

                <div class="row border-bottom mb-2 pb-2">
                    <div class="col-sm text-break">${user.firstName}</div>
                    <div class="col-sm text-break">${user.secondName}</div>
                    <div class="col-sm text-break">${user.passportCode}</div>
                    <div class="col-sm text-break">
                        <select class="form-control form-control-sm" name="subject_id" required>
                            <option value="">
                                <fmt:message key="setGrade.selectSubject" bundle="${lang}"/>
                            </option>
                            <c:forEach items="${requestScope.subjects}" var="subject">
                                <option value="${subject.id}">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm text-break">
                        <div class="input-group input-group-sm">
                            <input class="form-control" name="grade"
                                   placeholder="<fmt:message key="setGrade.grade" bundle="${lang}"/>" required>
                            <input type="hidden" name="user_id" value="${user.id}">
                            <div class="input-group-append">
                                <button class="btn btn-primary">
                                    <fmt:message key="setGrade.set" bundle="${lang}"/>
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
