<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 17.05.2021
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Title</title>
        <style>
            <%@include file='../styles/style.css' %>
        </style>
        <meta charset="UTF-8" />
        <fmt:setLocale value="ru" scope="session"/>
        <fmt:setBundle basename="locale" var="loc" scope="session"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <c:import url="header_admin.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="header.jsp"/>
            </c:otherwise>
        </c:choose>
        <main class="wrapper">
            <section class="description">
            </section>
        </main>
      <fmt:message bundle="${loc}" key="local.basket"/>

        <a href="controller?command=orderview">sddsd</a>
    </body>
</html>