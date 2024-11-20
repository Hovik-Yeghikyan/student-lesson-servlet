<%@ page import="com.vector.studentlessonservlet.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "WELCOME TO STUDENT-LESSON SERVLET" %>
</h1>
<br/>
<%User user = (User) session.getAttribute("user");%>
<%
    if (user != null){%>
<span>Welcome<br>
    <%=user.getName() + " " + user.getSurname()%></span> <a href="/logout">LogOut</a> <br>
<%}else {%>
<a href="/login">Login</a><br>
<a href="/register">REGISTER</a><br>
<%}%>
<a href="/student">Student Page</a>
<a href="/lesson">Lesson Page</a>
</body>
</html>