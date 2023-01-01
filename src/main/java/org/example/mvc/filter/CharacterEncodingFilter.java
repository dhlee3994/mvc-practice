package org.example.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
        throws IOException, ServletException {
        request.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        response.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
