<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 22.06.2021
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <body>
    <jsp:useBean id="product" scope="request" beanName="by.tsarenkov.shop.bean.good.Laptop"
                 type="by.tsarenkov.shop.bean.good.Laptop"/>
    <section class="wrapper">
        <div class="product_part">
            <img src="img/phone.jpg" width="450px" height="450px">
            <div>
                <p>
                    <jsp:getProperty name="product" property="brand"/>
                    <jsp:getProperty name="product" property="model"/>
                </p>
                <div class="characteristic">
                    <p class="char">Тип</p>
                    <p><jsp:getProperty name="product" property="type"/></p><br>
                    <p class="char">ОЗУ</p>
                    <p><jsp:getProperty name="product" property="RAM"/></p><br>
                    <p class="char">ПЗУ</p>
                    <p><jsp:getProperty name="product" property="RAM"/></p> <br>
                    <p class="char">Версия bluetooth</p>
                    <p><jsp:getProperty name="product" property="bluetoothVersion"/></p><br>
                    <p class="char">Диагональ</p>
                    <p><jsp:getProperty name="product" property="diagonal"/></p><br>
                    <p class="char">Тип процессора</p>
                    <p><jsp:getProperty name="product" property="typeProcessor"/></p><br>
                    <p class="char">Серия процессора</p>
                    <p><jsp:getProperty name="product" property="serialProcessor"/></p><br>
                    <p class="char">Количество USB портов</p>
                    <p><jsp:getProperty name="product" property="countUSBPort"/></p><br>
                    <p class="char">Тип видеокарты</p>
                    <p><jsp:getProperty name="product" property="typeGraphicsCard"/></p><br>
                    <p class="char">Видеокарта</p>
                    <p><jsp:getProperty name="product" property="graphicsCard"/></p><br>
                    <p class="char">Разрешение экрана</p>
                    <p><jsp:getProperty name="product" property="screenExtension"/></p><br>
                </div>
                <p><jsp:getProperty name="product" property="price"/></p>
                <c:choose>
                    <c:when test="${sessionScope.role == 'ADMIN'}">
                        <a class="show_product_btn"
                           href="controller?command=changeebook&id=<jsp:getProperty name="product" property="id"/>&name=laptop">
                            Изменить
                        </a>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${requestScope.basket == true}">
                                <a class="show_product_btn add_basket"
                                   href="controller?command=deleteproductfrombasket&id=<jsp:getProperty name="product" property="id"/>">
                                    Удалить из корзины
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a style="margin-bottom: 70px" class="show_product_btn add_basket"
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
</body>
</html>
