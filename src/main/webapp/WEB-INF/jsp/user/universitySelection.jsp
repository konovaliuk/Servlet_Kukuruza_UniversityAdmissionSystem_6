<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="universitySelection.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<c:if test="${requestScope.chosenSpecialty ne null}">
    <div class="container">
        <div class="jumbotron bg-success mt-4 pb-4 text-center">
            <h3 class="display-4 text-white">
                <p><fmt:message key="universitySelection.successChosenSpecialty" bundle="${lang}"/>!</p>
                <p><fmt:message key="universitySelection.yourChoice" bundle="${lang}"/>
                    <em>${requestScope.chosenSpecialty.name}</em>
                </p>
            </h3>
            <p class="lead text-white-50">
                <fmt:message key="universitySelection.changeChoice" bundle="${lang}"/>
            </p>
            <hr class="my-4">

            <a class="btn btn-light font-weight-bold" href="changeSpecialty.do?command=changeSpecialty" role="button">
                <fmt:message key="universitySelection.changeChoiceButton" bundle="${lang}"/>
            </a>
        </div>
    </div>
</c:if>
<c:if test="${not empty requestScope.universities}">
    <div class="container">
        <h2 class="mt-2 mb-4">
            <i class="fa fa-university" aria-hidden="true"></i>
            <fmt:message key="universitySelection.universities" bundle="${lang}"/>
        </h2>
        <div class="row">
            <c:forEach items="${requestScope.universities}" var="university">
                <div class="col-sm-4 mb-4">
                    <div class="card">
                        <form action="selectUniversity.do" method="post">
                            <div class="card-body">
                                <h5 class="card-title">${university.name}</h5>

                                <input type="hidden" name="universityId" value="${university.id}">
                                <input type="hidden" name="command" value="specialtySelection">

                                <button class="btn btn-sm btn-block btn-primary">
                                    <fmt:message key="universitySelection.chooseUniversityButton" bundle="${lang}"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>
</body>
</html>