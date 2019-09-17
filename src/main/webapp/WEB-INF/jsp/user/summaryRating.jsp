<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="summaryRating.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<c:if test="${requestScope.userIdToRating ne null}">
    <div class="container">
        <h2 class="my-4">
            <fmt:message key="summaryRating.summaryRating" bundle="${lang}"/>:
                ${requestScope.universityName}, ${requestScope.specialtyName}
        </h2>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">
                    <fmt:message key="summaryRating.number" bundle="${lang}"/>
                </th>
                <th scope="col">
                    <fmt:message key="summaryRating.firstName" bundle="${lang}"/>
                </th>
                <th scope="col">
                    <fmt:message key="summaryRating.secondName" bundle="${lang}"/>
                </th>
                <th scope="col">
                    <fmt:message key="summaryRating.rating" bundle="${lang}"/>
                </th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.userIdToRating}" var="entry" varStatus="counter">
                <c:set var="userId" value="${entry.key}"/>
                <c:if test="${requestScope.studentLimit >= counter.count}">
                    <tr class="bg-success">
                        <td>
                                ${counter.count}
                        </td>
                        <td>
                                ${requestScope.userIdToUser[userId].firstName}
                        </td>
                        <td>
                                ${requestScope.userIdToUser[userId].secondName}
                        </td>
                        <td>
                                ${entry.value}
                        </td>
                    </tr>
                </c:if>
                <c:if test="${requestScope.studentLimit < counter.count}">
                    <tr class="bg-danger">
                        <td>
                                ${counter.count}
                        </td>
                        <td>
                                ${requestScope.userIdToUser[userId].firstName}
                        </td>
                        <td>
                                ${requestScope.userIdToUser[userId].secondName}
                        </td>
                        <td>
                                ${entry.value}
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${requestScope.userIdToRating eq null}">
    <div class="container h-100">
        <div class="row align-items-center h-100">
            <div class="col-6 mx-auto">
                <div class="jumbotron text-center">
                    <h1>
                        <fmt:message key="summaryRating.noRating" bundle="${lang}"/> ;)
                    </h1>
                </div>
            </div>
        </div>
    </div>
</c:if>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>
