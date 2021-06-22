<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 21.06.2021
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <style>
        <%@include file='../styles/style.css' %>
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.role != 'ADMIN'}">
            <c:import url="header.jsp"/>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="0" end="${requestScope.basketList.size() - 1}" step="1">
    <section class="products wrapper">
        <div class="product_basket">
            <img src="img/phone.jpg" width="120px" height="120px">
            <div>
                <p>${requestScope.basketList.get(i).brand}</p>
                <p color="red">${requestScope.basketList.get(i).price}</p>
                <a class="show_product_btn" href="">Оформить</a><br>
                <a class="show_product_btn" href="">Удалить</a>
            </div>
        </div>
    </section>
    </c:forEach>
</body>
</html>
