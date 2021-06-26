<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 10.06.2021
  Time: 15:32
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
    <jsp:useBean id="product" type="by.tsarenkov.shop.bean.good.EBook"
                 beanName="by.tsarenkov.shop.bean.good.EBook" scope="request">
        <jsp:setProperty name="product" property="id" value="0"/>
        <jsp:setProperty name="product" property="brand" value=""/>
        <jsp:setProperty name="product" property="model" value=""/>
        <jsp:setProperty name="product" property="ROM" value=""/>
        <jsp:setProperty name="product" property="RAM" value=""/>
        <jsp:setProperty name="product" property="typeCardMemory" value=""/>
        <jsp:setProperty name="product" property="typeScreen" value=""/>
        <jsp:setProperty name="product" property="batteryCapacity" value=""/>
        <jsp:setProperty name="product" property="diagonal" value=""/>
        <jsp:setProperty name="product" property="wiFi" value=""/>
        <jsp:setProperty name="product" property="bluetooth" value=""/>
        <jsp:setProperty name="product" property="formats" value=""/>
        <jsp:setProperty name="product" property="price" value=""/>
        <jsp:setProperty name="product" property="status" value=""/>
    </jsp:useBean>
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="header_admin.jsp"/>
        </c:when>
    </c:choose>
    <main class="wrapper">
        <section class="section_data">
            <form class="form_input" action="controller" method="post" enctype="multipart/form-data">
                <c:choose>
                    <c:when test="${requestScope.product.id == 0}">
                        <input type="hidden" name="command" value="savenewproduct" />
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="savechangedebook" />
                    </c:otherwise>
                </c:choose>

                <input type="hidden" name="id_category" value="1" />
                <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>" />
                <input type="hidden" name="name" value="ebook" />
                <label>Бренд</label>
                <input type="text" name="brand"
                       pattern="[A-Za-zА-Яа-яЁё0-9]{2,}" required
                       value="<jsp:getProperty name="product" property="brand"/>"/>
                <label>Модель</label>
                <input type="text" name="model"
                       pattern="[A-Za-zА-Яа-яЁё0-9]{2,}" required
                       value="<jsp:getProperty name="product" property="model"/>"/>
                <label>ROM</label>
                <input type="text" name="rom" pattern="^[0-9]+$" required
                       value="<jsp:getProperty name="product" property="ROM"/>" />
                <label>RAM</label>
                <input type="text" name="ram" pattern="^[0-9]+$" required
                       value="<jsp:getProperty name="product" property="RAM"/>" />
                <label>Тип карты памяти</label>
                <input type="text"
                       pattern="[A-Za-zА-Яа-яЁё0-9]{2,}" required
                       name="type_card_memory"
                       value="<jsp:getProperty name="product" property="typeCardMemory"/>"/>
                <label>Тип экрана</label>
                <input type="text"
                       pattern="[A-Za-zА-Яа-яЁё]{2,}" required
                       name="type_screen"
                       value="<jsp:getProperty name="product" property="typeScreen"/>"/>
                <label> Емкость батареи</label>
                <input type="text" name="battery_capacity" pattern="^[0-9]+$" required
                       value="<jsp:getProperty name="product" property="batteryCapacity"/>"/>
                <label> Диагональ</label>
                <input type="text" name="diagonal" pattern="\d{1,}+(.\d{1})?" required
                       value="<jsp:getProperty name="product" property="diagonal"/>"/>
                <label>WIFI</label>
                <select name="wifi"
                        value="<jsp:getProperty name="product" property="wiFi"/>">
                    <option>Есть</option>
                    <option>Нет</option>
                </select>
                <label>Блютуз</label>
                <select name="bluetooth_version" value=
                        "<jsp:getProperty name="product" property="bluetooth"/>">
                    <option>Есть</option>
                    <option>Нет</option>
                </select>
                <label>Formats</label>
                <input type="text" name="formats"
                       pattern="[A-Za-zА-Яа-яЁё0-9, ]{2,}" required
                       value="<jsp:getProperty name="product" property="formats"/>"/>
                <label>Цена</label>
                <input type="text" name="price"
                       pattern="\d{1,}+(.\d{1})?" required
                       value="<jsp:getProperty name="product" property="price"/>"/>
                <label>Фото</label>
                <input type="file" name="photo" />
                <label>Количество</label>
                <input type="text" name="count" pattern="^[0-9]+$" required
                       value="<jsp:getProperty name="product" property="count"/>"/>
                <label>Статус</label>
                <select name="status"
                        value="<jsp:getProperty name="product" property="status"/>">
                    <option value="EXPECTED">Ожидаем поступления</option>
                    <option value="SUPPLIED">В наличии</option>
                    <option value="NOT_SUPPLIED">Не поставляется</option>
                </select>
                <input  class = "blubtn" type="submit" name="save" value="Сохранить"/>
            </form>
        </section>
    </main>

</body>
</html>
