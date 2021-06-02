<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 17.05.2021
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8" />
</head>
<body>

    <form action="controller?command=go_to_registration_page" method="post">
        <label>Login</label><br>
        <input type="text" name="login"/><br>
        <label>Password</label><br>
        <input type="text" name="password"/><br>
    </form>
    <br>
    <a href="controller?command=gotoregistrationpage">Загеристрироваться</a>
</body>
</html>
