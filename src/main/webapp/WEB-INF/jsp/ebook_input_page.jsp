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
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="header_admin.jsp"/>
        </c:when>
    </c:choose>
    <main class="wrapper">
        <section class="section_data">
            <form class="form_input" action="controller" method="post">
                <c:choose>
                    <c:when test="${requestScope.ebook == null}">
                        <input type="hidden" name="command" value="savenewproduct" />
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="savechangedebook" />
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="id_category" value="1" />
                <input type="hidden" name="id" value="${requestScope.ebook.id}" />
                <input type="hidden" name="name" value="ebook" />
                <label>Бренд</label>
                <input type="text" name="brand" value="${requestScope.ebook.brand}"/>
                <label>Модель</label>
                <input type="text" name="model" value="${requestScope.ebook.model}"/>
                <label>ROM</label>
                <input type="text" name="rom" pattern="^[ 0-9]+$" value="${requestScope.ebook.ROM}"/>
                <label>RAM</label>
                <input type="text" name="ram" pattern="^[ 0-9]+$" value="${requestScope.ebook.RAM}"/>
                <label>Тип карты памяти</label>
                <input type="text" name="type_card_memory" value="${requestScope.ebook.typeCardMemory}"/>
                <label>Тип экрана</label>
                <input type="text" name="type_screen" value="${requestScope.ebook.typeScreen}"/>
                <label> Емкость батареи</label>
                <input type="text" name="battery_capacity" pattern="^[ 0-9]+$"
                       value="${requestScope.ebook.batteryCapacity}"/>
                <label> Диагональ</label>
                <input type="text" name="diagonal" pattern="\d+(.\d{1})?" value="${requestScope.ebook.diagonal}"/>
                <label>WIFI</label>
                <select name="wifi">
                    <option>Есть</option>
                    <option>Нет</option>
                </select>
                <label>Блютуз</label>
                <select name="bluetooth_version">
                    <option>Есть</option>
                    <option>Нет</option>
                </select>
                <label>Formats</label>
                <input type="text" name="formats" value="${requestScope.ebook.formats}"/>
                <label>Цена</label>
                <input type="text" name="price" value="${requestScope.ebook.price}" />
                <label>Фото</label>
                <input type="file" name="photo" />
                <label>Количество</label>
                <input type="text" name="count" pattern="^[ 0-9]+$" value="${requestScope.ebook.count}"/>
                <label>Статус</label>
                <select name="status">
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
