package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Lesson;
import com.vector.studentlessonservlet.model.User;
import com.vector.studentlessonservlet.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addLesson")
public class AddLessonServlet extends HttpServlet {
    private LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String name = req.getParameter("name");
        int duration = Integer.parseInt(req.getParameter("duration"));
        double price = Double.parseDouble(req.getParameter("price"));
        String lecturerName = req.getParameter("lecturerName");
        Lesson lessonByNameAndUserId = lessonService.getLessonByNameAndUserId(name, user.getId());
        if (lessonByNameAndUserId == null) {
            Lesson lesson = Lesson.builder()
                    .name(name)
                    .duration(duration)
                    .price(price)
                    .lecturerName(lecturerName)
                    .user(user)
                    .build();

            lessonService.add(lesson);
            resp.sendRedirect("/lesson");
        }else {
            req.getSession().setAttribute("msg","Lesson is exists for you");
            resp.sendRedirect("/addLesson");
        }
    }
}
