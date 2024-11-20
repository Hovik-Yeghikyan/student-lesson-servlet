<%@ page import="com.vector.studentlessonservlet.model.Lesson" %>
<%@ page import="com.vector.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 17.11.2024
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<%List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<%Student student = (Student) request.getAttribute("student");%>
<h1>Update Student</h1>
<a href="/student">Student Page</a> | <a href="index.jsp">Main</a>
<form action="/updateStudent" method="post">
    id:<input type="hidden" name="id" value="<%=student.getId()%>"><br>
    Name: <input type="text" name="name" value="<%=student.getName()%>"> <br>
    Surname: <input type="text" name="surname" value="<%=student.getSurname()%>"> <br>
    Email: <input type="text" name="email" value="<%=student.getEmail()%>"> <br>
    Age: <input type="number" name="age" value="<%=student.getAge()%>"> <br>
    Lesson: <select name="lesson_id">
    <% for (Lesson lesson : lessons) {%>
    <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
    </option>
    <% }%>
</select>
    <input type="submit" value="UPDATE">
</form>
</body>
</html>