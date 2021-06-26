<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 15.06.2021
  Time: 20:44
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
                 type="by.tsarenkov.shop.bean.good.Tablet">
        <jsp:setProperty name="product" property="id" value="0"/>
        <jsp:setProperty name="product" property="brand" value=""/>
        <jsp:setProperty name="product" property="model" value=""/>
        <jsp:setProperty name="product" property="operationSystem" value=""/>
        <jsp:setProperty name="product" property="typeUSB" value=""/>
        <jsp:setProperty name="product" property="processor" value=""/>
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
                    <input type="hidden" name="id_category" value="2" />
                    <input type="hidden" name="name" value="tablet" />
                    <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>" />
                    <label>Бренд</label>
                    <input type="text" name="brand"
                    value="<jsp:getProperty name="product" property="brand"/>"/>
                    <label>Модель</label>
                    <input type="text" name="model"
                           value="<jsp:getProperty name="product" property="model"/>"/>
                    <label>ОС</label>
                    <input type="text" name="operation_system"
                           value="<jsp:getProperty name="product" property="operationSystem"/>"/>
                    <label> Диагональ</label>
                    <input type="text" name="diagonal"
                           value="<jsp:getProperty name="product" property="diagonal"/>"/>
                    <label>ROM</label>
                    <input type="text" name="rom"
                           value="<jsp:getProperty name="product" property="ROM"/>"/>
                    <label>RAM</label>
                    <input type="text" name="ram"
                           value="<jsp:getProperty name="product" property="RAM"/>"/>

                    <label>Тип USB порта</label>
                    <input type="text" name="type_usb"
                           value="<jsp:getProperty name="product" property="typeUSB"/>"/>
                    <label>Процессор</label>
                    <input type="text" name="processor"
                           value="<jsp:getProperty name="product" property="processor"/>"/>
                    <label>Цена</label>
                    <input type="text" name="price"
                           value="<jsp:getProperty name="product" property="price"/>"/>
                    <label>Фото</label>
                    <input type="file" name="photo" />
                    <label>Количество</label>
                    <input type="text" name="count" pattern="^[ 0-9]+$"
                           value="<jsp:getProperty name="product" property="count"/>"/>
                    <label>Статус</label>
                    <select name="status">
                        <option value="EXPECTED">Ожидаем поступления</option>
                        <option value="SUPPLIED">В наличии</option>
                        <option value="NOT_SUPPLIED">Не поставляется</option>
                    </select>
                    <input class = "blubtn" type="submit" name="save" value="Сохранить"/>
                </form>
            </section>
        </main>
    </body>
</html>
