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
        <jsp:useBean id="product" beanName="by.tsarenkov.shop.bean.good.EBook"
                     scope="request" type="by.tsarenkov.shop.bean.good.EBook"/>
        <section class="wrapper">
            <div class="product_part">
                <img src="/images/${product.path}" width="450px" height="450px">
                <div>
                    <p>
                        ${product.brand} ${product.model}
                    </p>
                    <div class="characteristic">
                        <input type="hidden" name="name" value="ebook">
                        <input type="hidden" name="name" value="ebook">

                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.RAM"/>:
                            ${product.RAM}
                        </p>
                        <p>

                            <fmt:message bundle="${loc}" key="product.characteristic.ROM"/>:
                            ${product.ROM}
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.typeCardMemory"/>:
                            ${product.typeCardMemory}
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.typeScreen"/>:
                            ${product.typeScreen}
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.batteryCapacity"/>:
                            ${product.batteryCapacity}
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.diagonal"/>:
                            <jsp:getProperty name="product" property="diagonal"/>
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.wifi"/>:
                            <fmt:message bundle="${loc}" key="${product.wiFi ? 'local.button.yes' : 'local.button.no'}"/>
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.bluetooth"/>:
                            <fmt:message bundle="${loc}" key="${product.bluetooth ? 'local.button.yes' : 'local.button.no'}"/>
                        </p>
                        <p>
                            <fmt:message bundle="${loc}" key="product.characteristic.formats"/>:
                            ${product.formats}
                        </p>
                    </div>
                        <p class="price">
                            ${product.price}
                        </p>
                        <c:choose>
                            <c:when test="${sessionScope.role == 'ADMIN'}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="changeebook"/>
                                    <input type="hidden" name="id" value="${product.id}"/>
                                    <input type="hidden" name="name" value="ebook"/>
                                    <input type="submit" class="show_product_btn"
                                           value="<fmt:message bundle="${loc}" key="local.button.edit"/>"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${requestScope.basket == true}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="deleteproductfrombasket"/>
                                            <input type="hidden" name="id"
                                                   value="${product.id}"/>
                                            <input type="hidden" name="name" value="ebook"/>
                                            <input type="submit" class="show_product_btn"
                                                   value="<fmt:message bundle="${loc}" key="local.button.removeCart"/>"/>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="saveproductinbasket"/>
                                            <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                                            <input type="hidden" name="name" value="ebook"/>
                                            <input type="submit" class="show_product_btn"
                                                   value="<fmt:message bundle="${loc}" key="local.button.addCart"/>"/>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </div>
            </div>
        </section>
    </body>
</html>
