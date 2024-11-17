package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Student;
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
        List<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/student.jsp").forward(req, resp);
    }
}
