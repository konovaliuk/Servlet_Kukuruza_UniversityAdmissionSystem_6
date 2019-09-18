<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/components/bootstrap.jsp" %>
    <title><fmt:message key="specialtySelection.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/user/navbar.jsp" %>
<c:if test="${not empty requestScope.specialties}">
    <div class="container">
        <h2 class="mt-2 mb-4">
            <i class="fa fa-university" aria-hidden="true"></i>
            <fmt:message key="specialtySelection.specialties" bundle="${lang}"/>
        </h2>
        <c:if test="${requestScope.notAvailableSpecialty ne null}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="specialtySelection.choiceError" bundle="${lang}"/>
                <b>${requestScope.notAvailableSpecialty.name}</b>.
                <c:if test="${not empty requestScope.requiredSubjects}">
                    <fmt:message key="specialtySelection.youHaveToPass" bundle="${lang}"/>
                    <b>
                        <c:forEach items="${requestScope.requiredSubjects}" var="subject">
                            ${subject.name}
                        </c:forEach>
                    </b>
                    <fmt:message key="specialtySelection.exams" bundle="${lang}"/>.
                </c:if>
            </div>
        </c:if>
        <div class="row">
            <c:forEach items="${requestScope.specialties}" var="specialty">
                <div class="col-sm-4 mb-4">
                    <div class="card">
                        <form action="<c:url value="/selectSpecialty.do"/>" method="post">
                            <div class="card-body">
                                <h5 class="card-title">${specialty.name}</h5>
                                <input type="hidden" name="universityId" value="${param.universityId}">
                                <input type="hidden" name="specialtyId" value="${specialty.id}">
                                <input type="hidden" name="command" value="submitRequest">
                                <button class="btn btn-sm btn-block btn-primary">
                                    <fmt:message key="specialtySelection.chooseSpecialtyButton" bundle="${lang}"/>
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
