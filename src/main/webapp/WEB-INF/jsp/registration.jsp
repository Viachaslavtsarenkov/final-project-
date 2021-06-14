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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Title</title>
        <style>
            <%@include file='../styles/style.css' %>
        </style>
    </head>
    <body>
    <c:choose>
        <c:when test="${sessionScope.role == null}">
            <c:import url="header.jsp"/>
        </c:when>
        <c:otherwise>
            <c:redirect url="main.jsp"/>
        </c:otherwise>
    </c:choose>
        <main class="wrapper">
            <section class="section_data">
                <form class="form" action="controller" method="post">
                    <input type="hidden" name="command" value="savenewuser"/>
                    <label>Имя</label><br>
                    <input type="text" name="name"/>
                    <c:if test="${requestScope.errorValidation.containsKey('name') == true}">
                        <p class="validation_error">${requestScope.errorValidation.get('password')}</p>
                    </c:if>
                    <br>
                    <label>Фамилия</label><br>
                    <input type="text" name="surname"/>
                    <c:if test="${requestScope.errorValidation.containsKey('surname') == true}">
                        <p class="validation_error">${requestScope.errorValidation.get('password')}</p>
                    </c:if>
                    <br>
                    <label>Почтовый адрес</label><br>
                    <input type="text" name="email"/>
                    <c:if test="${requestScope.errorValidation.containsKey('email') == true}">
                        <p class="validation_error">${requestScope.errorValidation.get('password')}</p>
                    </c:if>
                    <br>
                    <label>Пароль</label><br><input type="text" name="password"/>
                    <c:if test="${requestScope.errorValidation.containsKey('password') == true}">
                        <p class="validation_error">${requestScope.errorValidation.get('password')}</p>
                    </c:if>
                    <br>
                    <label>Повторите пароль</label><br>
                    <input type="text" name="repeated-password"/><br>
                    <label>Номер телефона</label><br>
                    <input type="text" name="phoneNumber"/>
                    <c:if test="${requestScope.errorValidation.containsKey('phoneNumber') == true}">
                        <p class="validation_error">${requestScope.errorValidation.get('password')}</p>
                    </c:if>
                    <br>
                    <input type="submit" value="Зарегистрироваться" >
                </form>
            </section>
        </main>
    </body>
</html>
