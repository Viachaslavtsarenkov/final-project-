<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 26.06.2021
  Time: 16:36
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
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.products.size() > 0}">
                <table class="table_price">
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Количество</th>
                        <th>Цена</th>
                        <th>Статус</th>
                        <th>Действия</th>
                    </tr>

                    <c:forEach var="i" begin="0" end="${requestScope.products.size() - 1}">
                        <tr>
                            <td>
                                <p>${requestScope.products.get(i).id}</p>
                            </td>
                            <td>${requestScope.products.get(i).brand}   ${requestScope.products.get(i).model}</td>
                            <td>${requestScope.products.get(i).count}</td>
                            <td>${requestScope.products.get(i).price}</td>
                            <td>${requestScope.products.get(i).status}</td>
                            <td>
                                <a href="">
                                    Посмотреть
                                </a>
                                <br>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </c:when>
            <c:otherwise>
                <c:out value="Нет товаров в данной категории"/>
            </c:otherwise>
        </c:choose>
        </c:when>

    </body>
</html>
