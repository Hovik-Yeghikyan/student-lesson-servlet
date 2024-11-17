<%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 17.11.2024
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
<h1>Add lesson</h1>
<a href="/lesson">Lesson</a> | <a href="index.jsp">Main</a>
<form action="/addLesson" method="post">
  Name: <input type="text" name="name"> <br>
  Duration: <input type="number" name="duration"> <br>
  Price: <input type="number" name="price"> <br>
 Lecturer Name: <input type="text" name="lecturerName"> <br>
    <input type="submit" value="ADD">
</form>
</body>
</html>
