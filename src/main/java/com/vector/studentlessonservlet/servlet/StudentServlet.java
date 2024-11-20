package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Student;
import com.vector.studentlessonservlet.model.User;
import com.vector.studentlessonservlet.model.UserType;
import com.vector.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Student> students;
        if (user.getUserType() == UserType.USER) {
            students = studentService.getStudentsByUserId(user.getId());
        } else {
            students = studentService.getAllStudents();
        }
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/student.jsp").forward(req, resp);
    }
}
