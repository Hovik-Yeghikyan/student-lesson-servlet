package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Lesson;
import com.vector.studentlessonservlet.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet {

    private LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/lesson.jsp").forward(req, resp);

    }
}
