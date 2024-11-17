<%@ page import="com.vector.studentlessonservlet.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 17.11.2024
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Lesson</title>
</head>
<body>
<%Lesson lesson = (Lesson) request.getAttribute("lesson");%>
<h1>Update Lesson</h1>
<a href="/lesson">Lesson</a> | <a href="index.jsp">Main</a>
<form action="/updateLesson" method="post">
    id:<input type="hidden" name="id" value="<%=lesson.getId()%>"><br>
    Name: <input type="text" name="name" value="<%=lesson.getName()%>"> <br>
    Duration: <input type="number" name="duration" value="<%=lesson.getDuration()%>"> <br>
    Price: <input type="number" name="price" value="<%=lesson.getPrice()%>"> <br>
    Lecturer Name: <input type="text" name="lecturerName" value="<%=lesson.getLecturerName()%>"> <br>
    <input type="submit" value="UPDATE">
</form>
</body>
</html>