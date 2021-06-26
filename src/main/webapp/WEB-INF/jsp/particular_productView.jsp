<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 20.06.2021
  Time: 10:14
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
            <c:import url="header.jsp"/>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${requestScope.type == 'EBOOK'}">
            <c:import url="ebook_item_view.jsp"/>
        </c:when>
        <c:when test="${requestScope.type == 'TABLET'}">
            <c:import url="tablet_item_view.jsp"/>
        </c:when>
        <c:when test="${requestScope.type == 'LAPTOP'}">
            <c:import url="laptop_item_view.jsp"/>
        </c:when>
        <c:otherwise >
            <c:import url="smartphone_item_view.jsp"/>
        </c:otherwise>
    </c:choose>
</body>
</html>
