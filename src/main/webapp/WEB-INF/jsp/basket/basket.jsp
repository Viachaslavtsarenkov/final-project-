<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
        <%@include file='../../styles/style.css' %>
    </style>
    <title>
        <fmt:message bundle="${loc}" key="local.header.basket"/>
    </title>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.role != 'ADMIN'}">
            <c:import url="../header/header.jsp"/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${requestScope.basketList.size() > 0}">
            <c:forEach var="i" begin="0" end="${requestScope.basketList.size() - 1}" step="1">
                <section class="basket wrapper">
                    <div class="product_basket">
                        <img src="/images/${requestScope.basketList.get(i).path}" width="120px" height="120px">
                        <div>
                            <p>${requestScope.basketList.get(i).brand} ${requestScope.basketList.get(i).model}</p>
                            <p color="red">${requestScope.basketList.get(i).price}</p>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="deleteproductfrombasket"/>
                                <input type="hidden" name="id"
                                       value="${requestScope.basketList.get(i).id}"/>
                                <input type="hidden" name="langpage" value="${requestScope.langpage.toString()}"/>
                                <input type="submit" class="show_product_btn"
                                       value="<fmt:message bundle="${loc}" key="local.button.removeCart"/>"/>
                            </form>
                        </div>
                    </div>
                </section>
            </c:forEach>

            <section class="wrapper">
                <div class="order">
                    <p>${requestScope.sum}</p><br>
                    <form>
                        <input type="hidden" name="command" value="gotoorderpage"/>
                        <input type ="submit" value="Оформить заказ" class="show_product_btn">
                    </form>
                </div>
            </section>
        </c:when>
        <c:otherwise>
            <p class="wrapper noproducts">
                <fmt:message bundle="${loc}" key="product.noProduct"/>
            </p>
        </c:otherwise>
    </c:choose>
    <c:import url="../footer.jsp"/>
</body>
</html>
