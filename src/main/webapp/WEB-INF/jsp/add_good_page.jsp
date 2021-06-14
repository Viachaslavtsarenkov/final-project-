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
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="header_admin.jsp"/>
        </c:when>
        <c:otherwise>
            <c:redirect url="main.jsp"/>
        </c:otherwise>
    </c:choose>

    </body>
</html>
