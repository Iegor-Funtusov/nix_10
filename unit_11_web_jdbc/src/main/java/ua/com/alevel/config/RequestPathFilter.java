package ua.com.alevel.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestPathFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("RequestPathFilter.doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("request = " + request.getRequestURI());
        System.out.println("request = " + request.getContextPath());
        System.out.println("request = " + request.getServletPath());

        if (request.getServletPath().endsWith("details")) {
            response.sendRedirect(request.getServletPath() + "/0");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
