package ua.company.training.kukuruza.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

public class EncodingFilter implements Filter {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if (Objects.nonNull(encoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        } else {
            request.setCharacterEncoding(DEFAULT_ENCODING);
            response.setCharacterEncoding(DEFAULT_ENCODING);
        }
        chain.doFilter(request, response);
    }
}
