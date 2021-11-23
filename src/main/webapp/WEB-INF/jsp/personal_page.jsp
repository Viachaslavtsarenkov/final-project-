<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>gggg</title>
    <style>
        <%@include file="../styles/order/order.css"%>
    </style>
</head>
<body>
    <c:import url="header/header.jsp"/>
<main class="wrapper">
    <section class="personal">
        <h3>
            <fmt:message bundle="${loc}" key="product.characteristic.id"/>
            ${requestScope.userInf.userId}</h3>
        <p>
            <fmt:message bundle="${loc}" key="order.customer"/>:
            ${requestScope.userInf.name} ${requestScope.userInf.surname}
        </p>
        <p>
            <fmt:message bundle="${loc}" key="user.phone"/>
            : ${requestScope.userInf.phoneNumber}
        </p>
        <p>
            <fmt:message bundle="${loc}" key="user.status"/>
            : ${requestScope.userInf.status}
        </p>
        <p class="goods">
            <fmt:message bundle="${loc}" key="user.orders"/>
        </p>
        <c:forEach var="i" begin="0" end="${requestScope.userOrderList.size() - 1}" step="1">
            <a href="controller?command=particularorderview&id=${requestScope.userOrderList.get(i).idOrder}">
                Order № ${requestScope.userOrderList.get(i).idOrder}
                    ${requestScope.userOrderList.get(i).date }
                    ${requestScope.userOrderList.get(i).statusOrder }
            </a>
        </c:forEach>
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
