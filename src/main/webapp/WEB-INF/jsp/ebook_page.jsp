<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 10.06.2021
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='../styles/style.css' %>
    </style>
</head>
<body>
    <main class="wrapper">
        <section class="section_data">
            <form class="form" action="controller" method="post">
                <input type="hidden" name="command" value="savenewproduct" />
                <input type="hidden" name="id_category" value="1" />
                <label>Бренд</label><br>
                <input type="text" name="name"/><br>
                <label>Модель</label><br>
                <input type="text" name="model"/><br>
                <label>ROM</label><br>
                <input type="text" name="rom"/><br>
                <label>RAM</label><br>
                <input type="text" name="ram"/><br>
                <label>Тип карты памяти</label><br>
                <input type="text" name="type_card_memory"/><br>
                <label>Тип карты памяти</label><br>
                <input type="text" name="type_card_memory"/><br>
                <label>Тип карты памяти</label><br>
                <input type="text" name="type_screen"/><br>
                <label> Емкость батареи</label><br>
                <input type="text" name="battery_capacity"/><br>
                <label> Диагональ</label><br>
                <input type="text" name="diagonal"/><br>
                <label>WIFI</label><br>
                <input type="text" name="wifi"/><br>
                <label>Блютуз</label><br>
                <input type="text" name="bluetooth"/><br>
                <label>Formats</label><br>
                <input type="text" name="formats"/><br>
                <label>Price</label><br>
                <input type="text" name="price"/><br>
                <input type="submit" name="save"/>
            </form>
        </section>
    </main>

</body>
</html>
