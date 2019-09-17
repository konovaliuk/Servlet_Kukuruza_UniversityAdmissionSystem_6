package ua.company.training.kukuruza.controller;

import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.util.UserType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AuthorizationFilter implements Filter {
    private static final Set<String> secureCommands = new HashSet<>();

    static {
        secureCommands.add("findUserSendingNotification");
        secureCommands.add("findUserSettingGrade");
        secureCommands.add("setGrade");
        secureCommands.add("setUserStatus");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String securePath = req.getContextPath() + "/admin";
        String command = req.getParameter("command");
        boolean isSecurePageRequest = req.getRequestURI().startsWith(securePath);
        boolean isSecureCommandRequest = isSecureCommand(command);

        if (isSecurePageRequest || isSecureCommandRequest) {
            if (isUserAdmin(req, resp)) {
                chain.doFilter(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher(Path.ACCESS_DENIED_PAGE);
                dispatcher.forward(req, response);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    private boolean isSecureCommand(String command) {
        if (Objects.isNull(command))
            return false;
        return secureCommands.contains(command);
    }

    private boolean isUserAdmin(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user"));
        if (isLoggedIn) {
            User user = (User) session.getAttribute("user");
            Integer userTypeId = user.getUserTypeId();
            if (Objects.isNull(userTypeId)) {
                return false;
            }
            return userTypeId == UserType.ADMIN.getId();
        } else {
            return false;
        }
    }
}
