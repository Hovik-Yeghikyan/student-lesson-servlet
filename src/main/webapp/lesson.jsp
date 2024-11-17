<%@ page import="com.vector.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 16.11.2024
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lesson</title>

</head>
<body>
<h2>Lesson </h2> <a href="/addLesson">Add Lesson</a>
<%
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
%>

<table border="1">
    <th>id</th>
    <th>name</th>
    <th>duration</th>
    <th>price</th>
    <th>Lecturer Name</th>
    <th>action</th>
    <%
        for (Lesson lesson : lessons) { %>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=lesson.getDuration()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">delete</a>/ <a href="updateLesson?id=<%=lesson.getId()%>">update</a> </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
