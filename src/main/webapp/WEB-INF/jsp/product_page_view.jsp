<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 13.06.2021
  Time: 18:48
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
        <c:when test="${sessionScope.role == 'CUSTOMER' || sessionScope.role == null}">
            <c:import url="header.jsp"/>
        </c:when>
    </c:choose>

    <c:forEach var="i" begin="0" end="${requestScope.products.size() - 1}">
        <div>
            <input type="hidden" value="${requestScope.products.get(i).id}">
            <section class="shop_description">
                <div class="product">
                    <img src="img/ebook.jpg" width="200px" height="200px">
                    <div class="product_description">
                        <h3>Электронная книга</h3>
                        <p>${requestScope.products.get(i).id}</p>
                        <input type="button" value="Посмотреть">
                    </div>
                </div>
            </section>
        </div>
    </c:forEach>
</body>
</html>
