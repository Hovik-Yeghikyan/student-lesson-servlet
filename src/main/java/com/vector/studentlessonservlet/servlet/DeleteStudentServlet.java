package com.vector.studentlessonservlet.servlet;

import com.vector.studentlessonservlet.model.Student;
import com.vector.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student studentById = studentService.getStudentById(id);
        if (studentById != null) {
            studentService.deleteStudent(id);
            resp.sendRedirect("/student");
        }else {
            throw new ServletException("Student not found");
        }
    }
}
