<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 11.05.2021
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 11.05.2021
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="controller?command=savenewuser" method="post">
            <label>Имя</label><br>
            <input type="text" name="name"/><br>
            <label>Фамилия</label><br>
            <input type="text" name="surname"/><br>
            <label>Почтовый адрес</label><br>
            <input type="text" name="email"/><br>
            <label>Пароль</label><br>
            <input type="text" name="password"/><br>
            <label>Повторите пароль</label><br>
            <input type="text" name="repeated-password"/><br>
            <label>Дата рождения</label><br>
            <input type="text" name="dateOfBirth"/><br>
            <input type="submit" value="Зарегистрироваться" >
        </form>
    </body>
</html>
