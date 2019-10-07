package ua.company.training.kukuruza.presentation.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

/**
 * The filter allows to set request and response encoding.
 * Default encoding is UTF-8. You can change it by
 * setting "encoding" initialize parameter to web.xml.
 * The filter has to be first in a filters queue.
 *
 * @author Andrii Kukuruza
 */
public class EncodingFilter implements Filter {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String ENCODING_INIT_PARAM = "encoding";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM);
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
