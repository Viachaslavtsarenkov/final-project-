<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 13.06.2021
  Time: 18:48
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
    <jsp:useBean id="product" beanName="by.tsarenkov.shop.bean.good.EBook"
                 scope="request" type="by.tsarenkov.shop.bean.good.EBook"/>
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
                    <p>$<jsp:getProperty name="product" property="ROM"/></p> <br>
                    <p class="char">Тип карты памяти</p>
                    <p><jsp:getProperty name="product" property="typeCardMemory"/></p><br>
                    <p class="char">Тип экрана</p>
                    <p><jsp:getProperty name="product" property="typeScreen"/></p><br>
                    <p class="char">Емкость батареи</p>
                    <p><jsp:getProperty name="product" property="batteryCapacity"/></p><br>
                    <p class="char">Диагональ</p>
                    <p><jsp:getProperty name="product" property="diagonal"/></p><br>
                    <p class="char">WIFI</p>
                    <p><jsp:getProperty name="product" property="wiFi"/></p> <br>
                    <p class="char">Блютуз</p>
                    <p>
                        ${product.bluetooth ? 'Есть' : 'Нет'}
                    </p><br>
                    <p class="char">Formats</p>
                    <p><jsp:getProperty name="product" property="formats"/></p>
                </div>
                <p><jsp:getProperty name="product" property="price"/></p>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'ADMIN'}">
                            <a class="show_product_btn"
                               href="controller?command=changeebook&id=<jsp:getProperty name="product" property="id"/>&name=ebook">
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
                                       href="controller?command=deleteproductfrombasket&id=${requestScope.product.id}">
                                        Удалить из корзины
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="show_product_btn add_basket"
                                       href="controller?command=saveproductinbasket&id=${requestScope.product.id}">
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
