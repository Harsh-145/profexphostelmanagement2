package com.gecpian.profexphostelmanagement2;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// This filter protects the specific URLs listed below
@WebFilter(urlPatterns = {"/dashboard.html", "/add-student.html", "/update-semester.html", "/delete-student.html", "/AddStudentServlet", "/ViewStudentsServlet", "/UpdateSemesterServlet", "/DeleteStudentServlet"})
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Check if user is logged in
        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (loggedIn) {
            // User is logged in, let them access the page/servlet
            chain.doFilter(request, response);
        } else {
            // Not logged in, redirect to login page
            res.sendRedirect("login.html");
        }
    }

    public void destroy() {}
}