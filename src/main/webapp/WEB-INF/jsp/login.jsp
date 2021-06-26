<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 17.05.2021
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8" />
    <style>
        <%@include file='../styles/style.css' %>
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.role == 'GUEST'}">
            <c:import url="header.jsp"/>
        </c:when>
        <c:otherwise>
            <c:redirect url="main.jsp"/>
        </c:otherwise>
    </c:choose>
    <main class="wrapper">
        <section class="section_data">
            <form class="form_input login_form" action="controller?command=login" method="post">
                <label>Login</label><br>
                <input type="text" name="login"/><br>
                <label>Password</label><br>
                <input type="text" name="password"/><br>
                <input type="submit" class="action_btn" name = "Войти"/>
                <br>
                <c:if test="${requestScope.loginError != null}">
                    <p class="validation_error">Логин или пароль введен не верно</p>
                </c:if><br>
                <a href="controller?command=gotoregistrationpage">Загеристрироваться</a>
            </form>

        </section>
    </main>

</body>
</html>
