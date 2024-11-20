package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Lesson;
import com.vector.studentlessonservlet.model.Student;
import com.vector.studentlessonservlet.model.User;
import com.vector.studentlessonservlet.model.UserType;
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
        User user = (User) req.getSession().getAttribute("user");
        List<Lesson> lessons;
        if (user.getUserType() == UserType.USER) {
            lessons = lessonService.getLessonsByUserId(user.getId());
        } else {
            lessons = lessonService.getAllLessons();
        }
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/lesson.jsp").forward(req, resp);
    }
}
