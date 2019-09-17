package ua.company.training.kukuruza.controller;

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
        String indexURI = req.getContextPath() + "/index.jsp";
        String registrationURI = req.getContextPath() + "/registration.jsp";
        String signInURI = req.getContextPath() + "/signIn.jsp";
        String signInCommand = req.getContextPath() + "/signIn.do";
        String registrationCommand = req.getContextPath() + "/registration.do";
        String localizationCommand = req.getContextPath() + "/localization.do";

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
