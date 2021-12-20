<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        ${requestScope.userInf.name}  ${requestScope.userInf.surname}
    </title>
    <style>
        <%@include file="../styles/order/order.css"%>
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="header/header_admin.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="header/header.jsp"/>
        </c:otherwise>
    </c:choose>
    <main class="wrapper">
        <section class="personal">
            <h3>
                <fmt:message bundle="${loc}" key="order.customer"/>
            </h3>
            <p>
                <fmt:message bundle="${loc}" key="order.customer"/>:
                ${requestScope.userInf.name} ${requestScope.userInf.surname}
            </p>
            <p>
                <fmt:message bundle="${loc}" key="user.phone"/>
                : ${requestScope.userInf.phoneNumber}
            </p>
            <p>
                <fmt:message bundle="${loc}" key="user.email"/>
                : ${requestScope.userInf.email}
            </p>
            <p>
                <fmt:message bundle="${loc}" key="orders.status"/>:
                <fmt:message bundle="${loc}" key="${requestScope.userInf.status.toString().toLowerCase()}"/>
            </p>
            <p class="goods">
                <fmt:message bundle="${loc}" key="user.orders"/>
            </p>
            <table class="table_price">
                <tr>
                    <th>
                        <fmt:message bundle="${loc}" key="product.characteristic.id"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="order.date"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="orders.status"/>
                    </th>
                    <th>
                    </th>
                </tr>
                <c:forEach var="i" begin="0" end="${requestScope.userOrderList.size() - 1}" step="1">
                    <tr>
                        <td>
                                ${requestScope.userOrderList.get(i).idOrder}
                        </td>
                        <td>
                                ${requestScope.userOrderList.get(i).date }
                        </td>
                        <td>
                            <fmt:message bundle="${loc}" key="orders.current.status.${requestScope.userOrderList.get(i).statusOrder.toString().toLowerCase()}"/>
                        </td>
                        <td>
                            <a href="controller?command=particularorderview&id=${requestScope.userOrderList.get(i).idOrder}">
                                <fmt:message bundle="${loc}" key="local.button.see"/>
                            </a>
                        </td>
                        </tr>

                </c:forEach>
            </table>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="changeuserstatus"/>
                    <input type="hidden" name="id" value="${requestScope.userInf.userId}"/>
                    <select name="status">
                        <option name="ACTIVATED">
                            <fmt:message bundle="${loc}" key="user.status.activated"/>
                        </option>
                        <option name="BLOCKED">
                            <fmt:message bundle="${loc}" key="user.status.blocked"/>
                        </option>
                        <option name="NO_ACTIVATED">
                            <fmt:message bundle="${loc}" key="user.status.noActivated"/>
                        </option>
                    </select>
                    <input type="submit" class="show_product_btn"
                           value="<fmt:message bundle="${loc}" key="user.status.change"/>"/>
                </form>
            </c:when>
        </c:choose>
        </section>
    </main>
    <c:import url="footer.jsp"/>
    </body>
</html>
