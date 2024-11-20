<%@ page import="com.vector.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 17.11.2024
  Time: 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<%
    if (session.getAttribute("msg") != null) {%>
<h3 style="color: red"><%=session.getAttribute("msg")%>
</h3>
<%
        session.removeAttribute("msg");
    }
%>
<%
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
%>
<h1>Add Student</h1>
<a href="/student">Student Page</a> | <a href="/">Main</a>
<form action="/addStudent" method="post">
    Name: <input type="text" name="name"> <br>
    Surname: <input type="text" name="surname"> <br>
    Email: <input type="text" name="email"> <br>
    Age: <input type="number" name="age"> <br>
    Lesson: <select name="lesson_id">
    <% for (Lesson lesson : lessons) {%>
    <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
    </option>
    <% }%>
</select>
    <input type="submit" value="ADD">
</form>
</body>
</html>
