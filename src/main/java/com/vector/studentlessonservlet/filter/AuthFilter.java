package com.vector.studentlessonservlet.filter;

import com.vector.studentlessonservlet.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addLesson","/addStudent","/deleteLesson",
        "/deleteStudent", "/lesson", "/student", "/updateLesson", "/updateStudent"})
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User)req.getSession().getAttribute("user");
        if (user != null) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/error");

        }
    }
}
