<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/adminPage.jsp"/>">
                <i class="fa fa-home" aria-hidden="true"></i>
                <fmt:message key="navbar.home" bundle="${lang}"/>
            </a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                <i class="fa fa-language" aria-hidden="true"></i>
                <fmt:message key="navbar.language" bundle="${lang}"/>
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<c:url value="/localization.do?command=localization&lang=en-US"/>">
                    <fmt:message key="navbar.en_US" bundle="${lang}"/>
                </a>
                <a class="dropdown-item" href="<c:url value="/localization.do?command=localization&lang=uk-UA"/>">
                    <fmt:message key="navbar.uk_UA" bundle="${lang}"/>
                </a>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/setGrade.jsp"/>">
                <fmt:message key="adminNavbar.setGrades" bundle="${lang}"/>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/sendNotification.jsp"/>">
                <fmt:message key="adminNavbar.sendNotifications" bundle="${lang}"/>
            </a>
        </li>
    </ul>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
                <span class="nav-link my-2 font-weight-bold" data-toggle="dropdown">
                    <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                    <jsp:useBean id="admin" class="ua.company.training.kukuruza.entity.User" scope="session"/>
                    <jsp:getProperty name="admin" property="firstName"/>
                </span>
        </li>

        <li class="nav-item">
            <a href="<c:url value="/signOut.do?command=signOut"/>"
               class="nav-link my-2 btn btn-sm btn-success ml-3 font-weight-bold">
                <fmt:message key="navbar.signOut" bundle="${lang}"/>
                <i class="fa fa-sign-out" aria-hidden="true"></i>
            </a>
        </li>
    </ul>
</nav>