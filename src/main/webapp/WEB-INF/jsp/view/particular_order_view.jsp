<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message bundle="${loc}" key="order"/>
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
                    <fmt:message bundle="${loc}" key="user.email"/>:
                    ${requestScope.orderInf.user.email}
                </p>
                <p>
                    <fmt:message bundle="${loc}" key="order.address"/>:
                    ${requestScope.orderInf.address}
                </p>
                <p>
                    <fmt:message bundle="${loc}" key="product.characteristic.status"/>:
                    <fmt:message bundle="${loc}" key="orders.current.status.${requestScope.orderInf.statusOrder.toString().toLowerCase()}"/>
                </p>
                <c:choose>
                    <c:when test="${requestScope.orderInf.deliveryOption.toString() == 'post'}">
                        <p>
                            <fmt:message bundle="${loc}" key="orders.deliver.post"/>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p>
                            <fmt:message bundle="${loc}" key="orders.deliver.courier"/>
                        </p>
                    </c:otherwise>
                </c:choose>
                <p>
                    <fmt:message bundle="${loc}" key="orders.amount"/>
                    ${requestScope.orderInf.amount}
                </p>
                <p class="goods">
                    <fmt:message bundle="${loc}" key="product.list"/>
                </p>
                <table class="table_price">
                    <tr>
                        <th>
                            <fmt:message bundle="${loc}" key="product.characteristic.id"/>
                        </th>
                        <th>
                            <fmt:message bundle="${loc}" key="product"/>
                        </th>
                        <th>
                            <fmt:message bundle="${loc}" key="product.characteristic.price"/>
                        </th>
                        <th>
                        </th>
                    </tr>
                    <c:forEach var="i" begin="0" end="${requestScope.orderInf.products.size() -1}" step="1">
                        <tr>
                            <td>
                                ${requestScope.orderInf.products.get(i).id}
                            </td>
                            <td>
                                    ${requestScope.orderInf.products.get(i).brand}
                                ${requestScope.orderInf.products.get(i).model}
                            </td>
                            <td>
                                    ${requestScope.orderInf.products.get(i).price}
                            </td>
                            <td>
                                <a href="controller?command=particularebookview&id=${requestScope.orderInf.products.get(i).id}">
                                    <fmt:message bundle="${loc}" key="local.button.see"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <c:choose>
                    <c:when test="${sessionScope.role == 'ADMIN'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="changeorderstatus"/>
                            <input type="hidden" name="id" value="${requestScope.orderInf.idOrder}"/>
                            <select name="status">
                                <option value="NEW">
                                    <fmt:message bundle="${loc}" key="orders.current.status.new"/>
                                </option>
                                <option value="RECEIVED">
                                    <fmt:message bundle="${loc}" key="orders.current.status.received"/>
                                </option>
                                <option value="SENT">
                                    <fmt:message bundle="${loc}" key="orders.current.status.sent"/>
                                </option>
                                <option value="PROCESSED">
                                    <fmt:message bundle="${loc}" key="orders.current.status.processed"/>
                                </option>
                                <option value="CANCELED">
                                    <fmt:message bundle="${loc}" key="orders.current.status.canceled"/>
                                </option>
                            </select>
                            <br><br><br>
                            <input type="submit" class="show_product_btn"
                                   value="<fmt:message bundle="${loc}" key="user.status.change"/>"
                                   key="local.button.edit"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${requestScope.orderInf.statusOrder.toString() != 'CANCELED'}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="changeorderstatus"/>
                                <input type="hidden" name="id" value="${requestScope.orderInf.idOrder}"/>
                                <input type="hidden" name="status" value="CANCELED"/>
                                <input type="submit" class="show_product_btn"
                                       value="<fmt:message bundle="${loc}" key="order.cancel"/>" />
                            </form>

                        </c:if>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>
    <c:import url="../footer.jsp"/>
</body>
</html>
