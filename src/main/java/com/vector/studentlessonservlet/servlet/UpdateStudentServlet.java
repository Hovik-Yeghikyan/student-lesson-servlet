package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Lesson;
import com.vector.studentlessonservlet.model.Student;
import com.vector.studentlessonservlet.service.LessonService;
import com.vector.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    LessonService lessonService = new LessonService();
    StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getStudentById(id);
        req.setAttribute("lessons", lessons);
        req.setAttribute("student", student);
        req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lesson_id"));

        Student student = Student.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .lesson(lessonService.getLessonById(lessonId))
                .build();

        studentService.update(student);
        resp.sendRedirect("/student");
    }
}
