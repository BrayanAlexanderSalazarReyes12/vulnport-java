package com.auditorlab.vulnport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse http = (HttpServletResponse) response;

        // LAB-CORS-001: política CORS deliberadamente permisiva.
        http.setHeader("Access-Control-Allow-Origin", "*");
        http.setHeader("Access-Control-Allow-Headers", "*");
        http.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
