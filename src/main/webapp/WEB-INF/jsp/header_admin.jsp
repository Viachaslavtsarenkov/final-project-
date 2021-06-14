<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 08.06.2021
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <header class="wrapper">
        <nav>
            <img src="img/icon.svg" width="50px" height="50px" alt="electronic.com">
            <ul class="menu_list">
                <li>
                    Товары
                    <ul class="submenu section_data">
                        <li>Электронные книги</li>
                        <li>Ноутбуки</li>
                        <li>Смартфоны</li>
                        <li>Планшеты</li>
                    </ul>
                </li>
                <li>Заказы</li>
                <li>Пользователи</li>
            </ul>
            <div class="person">
                <a href="controller?command=gotopersonalpage"><img src="img/avatar.svg" width="20px" height="30px"/></a>
                <img src="img/basket.svg" width="20px" height="30px"/>
            </div>
        </nav>
    </header>
</body>
</html>
