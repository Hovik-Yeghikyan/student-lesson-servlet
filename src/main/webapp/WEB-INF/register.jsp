<%--
  Created by IntelliJ IDEA.
  User: hovoe
  Date: 19.11.2024
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<span>
    <%
        if (session.getAttribute("msg") != null) {%>
    <h3 style="color: red"> <%=session.getAttribute("msg")%></h3>
    <%
            session.removeAttribute("msg");
        }
    %>
</span>
<form action="/register" method="post">
    name:<input type="text" name="name"><br>
    рsurname:<input type="text" name="surname"><br>
    email:<input type="text" name="email"><br>
    password:<input type="password" name="password"><br>
    user type:<select name="user_type">
    <option value="USER">USER</option>
    <option value="ADMIN">ADMIN</option>
</select><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
