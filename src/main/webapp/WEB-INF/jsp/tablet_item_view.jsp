<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 21.06.2021
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='../styles/style.css' %>
    </style>
</head>
<body>
<jsp:useBean id="product" scope="request" beanName="by.tsarenkov.shop.bean.good.Tablet"
type="by.tsarenkov.shop.bean.good.Tablet"/>
<section class="products wrapper">
    <div class="product_part">
        <img src="img/phone.jpg" width="450px" height="450px">
        <div>
            <p>
                <jsp:getProperty name="product" property="brand"/>
                <jsp:getProperty name="product" property="model"/>
            </p>
            <div class="characteristic">
                <p class="char">ОЗУ</p>
                <p><jsp:getProperty name="product" property="RAM"/></p><br>
                <p class="char">ПЗУ</p>
                <p><jsp:getProperty name="product" property="RAM"/></p> <br>
                <p class="char">ОС</p>
                <p><jsp:getProperty name="product" property="operationSystem"/></p><br>
                <p class="char">Диагональ</p>
                <p><jsp:getProperty name="product" property="diagonal"/></p><br>
                <p class="char">Процессор</p>
                <p><jsp:getProperty name="product" property="processor"/></p><br>
                <p class="char">Тип USB</p>
                <p><jsp:getProperty name="product" property="typeUSB"/></p><br>
            </div>
            <p><jsp:getProperty name="product" property="price"/></p>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <a class="show_product_btn"
                            href="controller?command=changeebook&id=<jsp:getProperty name="product" property="id"/>&name=tablet">
                        Изменить
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="show_product_btn" href="">
                        Оформить
                    </a><br>
                    <c:choose>
                        <c:when test="${requestScope.basket == true}">
                            <a class="show_product_btn add_basket"
                               href="controller?command=deleteproductfrombasket&id=<jsp:getProperty name="product" property="id"/>">
                                Удалить из корзины
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="show_product_btn add_basket"
                               href="controller?command=saveproductinbasket&id=<jsp:getProperty name="product" property="id"/>">
                                Добавить в корзину
                            </a>
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
