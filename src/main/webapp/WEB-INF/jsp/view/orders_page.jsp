<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
        <%@include file='../../styles/products/products.css' %>
    </style>
</head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <c:import url="../header/header_admin.jsp"/>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.ordersList.size() > 0}">
                <main class="wrapper">
                    <c:if test="${requestScope.criterionOrder.toString() eq 'ALL'
                     or requestScope.criterionOrder.toString() eq 'USER'}">
                        <form class="search" action="controller?command=ordersview" method="post">
                            <input type="text" class="search_field" name="search">
                            <input type="hidden" name="criterionOrder" value="user">
                            <input type="submit" value="Поиск" class="search_btn">
                        </form>
                    </c:if>

                    <table class="table_price">
                        <tr>
                            <th>
                                <fmt:message bundle="${loc}" key="product.characteristic.id"/>
                            </th>
                            <th>
                                <fmt:message bundle="${loc}" key="order.customer"/>
                            </th>
                            <th>
                                <fmt:message bundle="${loc}" key="order.price"/>
                            </th>
                            <th>
                                <fmt:message bundle="${loc}" key="product.characteristic.status"/>
                            </th>
                            <th>
                                <fmt:message bundle="${loc}" key="order.date"/>
                            </th>
                            <th></th>
                        </tr>

                        <c:forEach var="i" begin="0" end="${requestScope.ordersList.size() - 1}">
                            <tr>
                                <td>
                                    ${requestScope.ordersList.get(i).idOrder}
                                </td>
                                <td>${requestScope.ordersList.get(i).user.name}
                                        ${requestScope.ordersList.get(i).user.surname}
                                </td>
                                <td>${requestScope.ordersList.get(i).amount}</td>
                                <td>${requestScope.ordersList.get(i).statusOrder}</td>
                                <td>
                                        ${requestScope.ordersList.get(i).date}
                                </td>
                                <td>
                                    <a href="controller?command=particularorderview&id=${requestScope.ordersList.get(i).idOrder}">
                                        <fmt:message bundle="${loc}" key="local.button.see"/>
                                    </a>
                                    <br>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </main>
            </c:when>
            <c:otherwise>
                <p class="wrapper noproducts">
                    <fmt:message bundle="${loc}" key="order.noorders"/>
                </p>
            </c:otherwise>
        </c:choose>

        <c:if test="${requestScope.ordersList.size() > 0 && requestScope.countPage > 1}">
            <section class="pagination_btn wrapper" >
                <div class="btn_navigation">
                    <c:if test="${requestScope.page != 1}">
                        <a style="color:black"
                           href="controller?command=ordersview&search=${requestScope.search}&&status=${requestScope.status}&criterionOrder=${requestScope.criterionOrder}&page=${requestScope.page - 1}"><</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.countPage}" step="1">
                        <c:choose>
                            <c:when test="${i eq requestScope.page}">
                                <a style="color:black"
                                   href="controller?command=ordersview&status=${requestScope.status}&search=${requestScope.search}&criterionOrder=${requestScope.criterionOrder}&page=${i}">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="add_btn"
                                   href="controller?command=ordersview&status=${requestScope.status}&search=${requestScope.search}&criterionOrder=${requestScope.criterionOrder}&page=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${requestScope.page != requestScope.countPage}">
                        <a style="color:black"
                           href="controller?command=ordersview&page=${requestScope.page + 1}">></a>
                    </c:if>
                </div>
            </section>
        </c:if>
        <c:import url="../footer.jsp"/>
    </body>
</html>
