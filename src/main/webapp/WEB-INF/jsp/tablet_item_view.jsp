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
<section class="wrapper">
    <div class="product_part">
        <img src="<jsp:getProperty name="product" property="path"/>" width="450px" height="450px">
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
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="changeebook"/>
                        <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                        <input type="hidden" name="name" value="tablet"/>
                        <input type="submit" class="blubtn" value="Изменить"/>
                    </form>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${requestScope.basket == true}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="deleteproductfrombasket"/>
                                <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                                <input type="hidden" name="name" value="tablet"/>
                                <input type="submit" class="blubtn" value="Удалить из корзины"/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="saveproductinbasket"/>
                                <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>"/>
                                <input type="hidden" name="name" value="tablet"/>
                                <input type="submit" class="blubtn" value="Добавить в козину"/>
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