package com.ou.configs;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class FilterConfig implements Filter {
    private static final String UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String encoding;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestCharEncoding");
        if (encoding == null) {
            encoding = UTF8;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }
        servletResponse.setContentType(CONTENT_TYPE);
        servletResponse.setCharacterEncoding(UTF8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
