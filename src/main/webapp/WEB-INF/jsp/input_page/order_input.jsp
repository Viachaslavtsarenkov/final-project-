<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
        <%@include file='../../styles/products/products.css' %>
    </style>
</head>
    <body>
    <c:choose>
        <c:when test="${sessionScope.role == 'CUSTOMER'}">
            <c:import url="../header/header.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:forward page="../main.jsp"></jsp:forward>
        </c:otherwise>
    </c:choose>
    <main class="wrapper">
        <section class="section_data">
            <form id="order_form" class="form_input" action="controller" method="post">
                <input type="hidden" name="command" value="addneworder" />
                <p class="order_headline">
                    <fmt:message bundle="${loc}" key="order"/>
                </p>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.brand"/>
                </label>
                <select name="delivery">
                    <option name="bycourier" value="courier">
                        <fmt:message bundle="${loc}" key="order.courier"/>
                    </option>
                    <option name="bypost" value="post" >
                        <fmt:message bundle="${loc}" key="order.post"/>
                    </option>
                </select>
                <label>
                    <fmt:message bundle="${loc}" key="order.address"/>
                </label>
                <textarea name="address"
                        form="order_form" required minlength="10"
                        maxlength="250"></textarea>
                <label class="order_goods">
                    <fmt:message bundle="${loc}" key="local.header.goods"/>
                </label>
                <c:forEach var="i" begin="0" end="${requestScope.order.size() - 1}" step="1" >
                    <a class="order_goods" href = "controller?command=particularebookview&id=${requestScope.order.get(i).id}">
                        ${requestScope.order.get(i).brand} ${requestScope.order.get(i).price}
                    </a>
                </c:forEach>
                <label class="sum">
                    Стоимость заказа:
                    ${requestScope.sum}
                </label>
                <input class="show_product_btn" type="submit" name="save"
                       value="<fmt:message bundle="${loc}" key="order.checkout"/>"/>
                <a class="change_order" href="controller?command=gotobasketpage">
                    <fmt:message bundle="${loc}" key="order.changeOrder"/>
                </a>
            </form>
        </section>
    </main>
    <c:import url="../footer.jsp"/>
</body>
</html>
