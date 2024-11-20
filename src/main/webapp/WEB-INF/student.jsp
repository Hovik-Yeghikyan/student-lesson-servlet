<%@ page import="com.vector.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vector.studentlessonservlet.model.Student" %>
<%@ page import="com.vector.studentlessonservlet.model.User" %>
<%@ page import="com.vector.studentlessonservlet.model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 16.11.2024
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>

</head>
<body>
<h2>Student </h2> <a href="/addStudent">Add Student</a>
<%
    User user = (User) session.getAttribute("user");
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<table border="1">
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>age</th>
    <th>lesson</th>
    <th>added by</th>
    <%if (user.getUserType() == UserType.ADMIN){%>
    <th>action</th>
    <%}%>
    <%
        for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getLesson().getName()%>
        </td>
        <td><%=student.getUser().getName()%>
        </td>
        <% if (user.getUserType() == UserType.ADMIN)
        {%>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a>/ <a href="updateStudent?id=<%=student.getId()%>">update</a> </td>
    </tr>
    <%}%>
    <%
        }
    %>
</table>

</body>
</html>
