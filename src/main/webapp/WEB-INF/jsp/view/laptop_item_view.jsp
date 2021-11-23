<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='../../styles/products/products.css' %>
    </style>
</head>
<body>
    <body>
    <jsp:useBean id="product" scope="request" beanName="by.tsarenkov.shop.bean.good.Laptop"
                 type="by.tsarenkov.shop.bean.good.Laptop"/>
    <section class="wrapper">
        <div class="product_part">
            <img src="/images/<jsp:getProperty name="product" property="path"/>" width="450px" height="450px">
            <div>
                <p>
                    ${product.brand} ${product.model}
                </p>
                <div class="characteristic">
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.type"/>:
                        ${product.type}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.RAM"/>:
                        ${product.RAM}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.ROM"/>:
                        ${product.ROM}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.bluetoothVersion"/>:
                        ${product.bluetoothVersion}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.diagonal"/>:
                        ${product.diagonal}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.typeProcessor"/>:
                        ${product.typeProcessor}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.serialProcessor"/>:
                        ${product.serialProcessor}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.coutnUSBPorts"/>:
                        ${product.countUSBPort}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.typeGraphicsCard"/>:
                        ${product.typeGraphicsCard}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.graphicsCard"/>:
                        ${product.graphicsCard}
                    </p>
                    <p>
                        <fmt:message bundle="${loc}" key="product.characteristic.screenExtension"/>:
                        ${product.screenExtension}
                    </p>
                </div>
                <p>
                    ${product.price}
                </p>
                <c:choose>
                    <c:when test="${sessionScope.role == 'ADMIN'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="changeebook"/>
                            <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                            <input type="hidden" name="name" value="laptop"/>
                            <input type="submit" class="show_product_btn"
                                   value="<fmt:message bundle="${loc}" key="local.button.edit"/>"/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${requestScope.basket == true}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteproductfrombasket"/>
                                    <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                                    <input type="hidden" name="name" value="tablet"/>
                                    <input type="submit" class="show_product_btn"
                                           value="<fmt:message bundle="${loc}" key="local.button.removeCart"/>"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="saveproductinbasket"/>
                                    <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                                    <input type="hidden" name="name" value="tablet"/>
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
</body>
</html>