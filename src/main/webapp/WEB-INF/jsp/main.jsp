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
    <style>
        <%@include file='../styles/style.css' %>
    </style>
    <meta charset="UTF-8" />
</head>
<body>
    <c:if test="${sessionScope.role == null || sessionScope.role == 'CUSTOMER'}">
        <jsp:include page="../jsp/header.jsp"/>
    </c:if>
    <c:if test="${sessionScope.role == 'ADMIN'}">
        <jsp:include page="../jsp/header_admin.jsp"/>
    </c:if>
    <a href="controller?command=gotologinpage">Войти</a>

</body>
</html>
