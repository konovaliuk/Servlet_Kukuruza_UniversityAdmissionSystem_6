<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="result.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<c:if test="${not empty requestScope.userGrades}">
    <div class="container">
        <h2 class="mt-2"><fmt:message key="result.tableName" bundle="${lang}"/></h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="result.subject" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="result.grade" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.userGrades}" var="entry">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${empty requestScope.userGrades}">
    <div class="container h-100">
        <div class="row align-items-center h-100">
            <div class="col-6 mx-auto">
                <div class="jumbotron text-center">
                    <h1><fmt:message key="result.noResults" bundle="${lang}"/> ;)</h1>
                </div>
            </div>
        </div>
    </div>
</c:if>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
