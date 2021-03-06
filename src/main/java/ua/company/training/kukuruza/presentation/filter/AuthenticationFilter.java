package ua.company.training.kukuruza.presentation.filter;

import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.RequestParameters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static ua.company.training.kukuruza.util.CommandNames.*;
import static ua.company.training.kukuruza.util.Path.*;

/**
 * The filter restricts access for non-authenticated users.
 *
 * @author Andrii Kukuruza
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String rootURI = req.getContextPath() + "/";
        String assetURI = req.getContextPath() + ASSETS;
        String indexURI = req.getContextPath() + INDEX_PAGE;
        String registrationURI = req.getContextPath() + REGISTRATION_PAGE;
        String signInURI = req.getContextPath() + SIGN_IN_PAGE;
        String command = req.getParameter(RequestParameters.COMMAND);

        boolean isLoggedIn = Objects.nonNull(session) && Objects.nonNull(session.getAttribute(AttributeNames.USER));
        boolean isRootRequest = req.getRequestURI().equals(rootURI);
        boolean isAssetRequest = req.getRequestURI().startsWith(assetURI);
        boolean isIndexRequest = req.getRequestURI().equals(indexURI);
        boolean isRegistrationRequest = req.getRequestURI().equals(registrationURI);
        boolean isSignInRequest = req.getRequestURI().equals(signInURI);
        boolean isSignInCommand = Objects.nonNull(command) && command.equals(SIGN_IN_COMMAND)
                && req.getMethod().equals("POST");
        boolean isRegistrationCommand = Objects.nonNull(command) && command.equals(REGISTRATION_COMMAND)
                && req.getMethod().equals("POST");
        boolean isLocalizationCommand = Objects.nonNull(command) && command.equals(LOCALIZATION_COMMAND);

        if (isLoggedIn) {
            if (isRegistrationRequest || isSignInRequest || isRegistrationCommand || isSignInCommand) {
                resp.sendRedirect(indexURI);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (isIndexRequest || isLocalizationCommand || isRegistrationRequest || isRootRequest ||
                    isSignInRequest || isRegistrationCommand || isSignInCommand || isAssetRequest) {
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(signInURI);
            }
        }
    }
}
