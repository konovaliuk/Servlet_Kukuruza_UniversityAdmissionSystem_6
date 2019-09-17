package ua.company.training.kukuruza.controller;

import ua.company.training.kukuruza.controller.util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String rootURI = req.getContextPath() + "/";
        String indexURI = req.getContextPath() + Path.INDEX_PAGE;
        String registrationURI = req.getContextPath() + Path.REGISTRATION_PAGE;
        String signInURI = req.getContextPath() + Path.SIGN_IN_PAGE;
        String signInCommand = req.getContextPath() + Path.SIGN_IN_COMMAND;
        String registrationCommand = req.getContextPath() + Path.REGISTRATION_COMMAND;
        String localizationCommand = req.getContextPath() + Path.LOCALIZATION_COMMAND;

        boolean isLoggedIn = Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user"));
        boolean isRootRequest = req.getRequestURI().equals(rootURI);
        boolean isIndexRequest = req.getRequestURI().equals(indexURI);
        boolean isRegistrationRequest = req.getRequestURI().equals(registrationURI);
        boolean isSignInRequest = req.getRequestURI().equals(signInURI);
        boolean isSubmitSignInData = req.getRequestURI().equals(signInCommand) && req.getMethod().equals("POST");
        boolean isSubmitRegistrationData = req.getRequestURI().equals(registrationCommand)
                && req.getMethod().equals("POST");
        boolean isLocalizationCommand = req.getRequestURI().equals(localizationCommand);

        if (isLoggedIn) {
            if (isRegistrationRequest || isSignInRequest || isSubmitRegistrationData || isSubmitSignInData) {
                resp.sendRedirect(indexURI);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (isIndexRequest || isLocalizationCommand || isRegistrationRequest || isRootRequest ||
                    isSignInRequest || isSubmitRegistrationData || isSubmitSignInData) {
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(signInURI);
            }
        }
    }
}
