<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
    </title>
    <style>
        <%@include file='../../styles/order/order.css' %>
    </style>
</head>
<body>
    <c:choose>
    <c:when test="${sessionScope.role == 'ADMIN'}">
        <c:import url="../header/header_admin.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="../header/header.jsp"/>
    </c:otherwise>
    </c:choose>
        <main class="wrapper">
            <section class="personal">
                <h3>
                    <fmt:message bundle="${loc}" key="order"/>
                    ${requestScope.order}
                </h3>
                <p>
                    <a href="controller?command=gotopersonalpage&user=${requestScope.orderInf.user.userId}">
                    <fmt:message bundle="${loc}" key="order.customer"/>
                    : ${requestScope.orderInf.user.name}
                    ${requestScope.orderInf.user.surname}
                    </a>
                </p>
                <p>
                    <fmt:message bundle="${loc}" key="user.phone"/>:
                    ${requestScope.orderInf.user.phoneNumber}
                </p>
                <p>
                    <fmt:message bundle="${loc}" key="order.address"/>:
                    ${requestScope.orderInf.address}
                </p>
                <p>
                    <fmt:message bundle="${loc}" key="product.characteristic.status"/>:
                    ${requestScope.orderInf.statusOrder}
                </p>
                <c:choose>
                    <c:when test="${requestScope.orderInf.deliveryOption.toString() == 'post'}">
                        <p>
                            Способ доставки: почтой
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p>
                            Способ доставки: курьером
                        </p>
                    </c:otherwise>
                </c:choose>
                <p>
                    Стоимость
                    ${requestScope.orderInf.amount}
                </p>
                <p class="goods">
                    <fmt:message bundle="${loc}" key="product.list"/>
                </p>
                <c:forEach var="i" begin="0" end="${requestScope.orderInf.products.size() -1}" step="1">
                    <a href="controller?command=particularebookview&id=${requestScope.orderInf.products.get(i).id}">
                        ${requestScope.orderInf.products.get(i).brand}
                                ${requestScope.orderInf.products.get(i).price}
                    </a>
                </c:forEach>

                <c:choose>
                    <c:when test="${sessionScope.role == 'ADMIN'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="changeorderstatus"/>
                            <input type="hidden" name="id" value="${requestScope.orderInf.idOrder}"/>
                            <select name="status">
                                <option value="NEW">NEW</option>
                                <option value="RECEIVED">RECEIVED</option>
                                <option value="SENT">SENT</option>
                                <option value="PROCESSED">PROCESSED</option>
                                <option value="CANCELED">CANCELED</option>
                            </select>
                            <input type="submit" class="show_product_btn"
                                   value="<fmt:message bundle="${loc}" key="user.status.change"/>"
                                   key="local.button.edit"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${requestScope.orderInf.statusOrder != 'CANCELED'}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="changeorderstatus"/>
                                <input type="hidden" name="id" value="${requestScope.orderInf.idOrder}"/>
                                <input type="hidden" name="status" value="CANCELED"/>
                                <input type="submit" class="show_product_btn"
                                       value="<fmt:message bundle="${loc}" key="order.cancel" />
                            </form>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>
    <c:import url="../footer.jsp"/>
</body>
</html>
