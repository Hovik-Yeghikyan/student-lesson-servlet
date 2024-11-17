package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Lesson;
import com.vector.studentlessonservlet.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateLesson")
public class UpdateLessonServlet extends HttpServlet {

    LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson != null) {
            req.setAttribute("lesson", lesson);
            req.getRequestDispatcher("updateLesson.jsp").forward(req, resp);
        }else {
            throw new ServletException("Lesson not found");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int duration = Integer.parseInt(req.getParameter("duration"));
        double price = Double.parseDouble(req.getParameter("price"));
        String lecturerName = req.getParameter("lecturerName");

        Lesson lesson = Lesson.builder()
                .id(id)
                .name(name)
                .duration(duration)
                .price(price)
                .lecturerName(lecturerName)
                .build();

        lessonService.update(lesson);
        resp.sendRedirect("/lesson");
    }
}
