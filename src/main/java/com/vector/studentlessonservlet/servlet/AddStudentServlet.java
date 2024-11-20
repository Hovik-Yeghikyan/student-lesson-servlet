package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Lesson;
import com.vector.studentlessonservlet.model.Student;
import com.vector.studentlessonservlet.model.User;
import com.vector.studentlessonservlet.service.LessonService;
import com.vector.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private StudentService studentService = new StudentService();
    private LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lesson_id"));
        Student studentByEmail = studentService.getStudentByEmail(email);
        if (studentByEmail == null) {

            Student student = Student.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .lesson(lessonService.getLessonById(lessonId))
                    .user(user)
                    .build();

            studentService.add(student);
            resp.sendRedirect("/student");
        } else {
            req.getSession().setAttribute("msg", "Student with email " + studentByEmail.getEmail() + " already exists!!!");
            resp.sendRedirect("/addStudent");
        }
    }
}
