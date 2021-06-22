<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 08.06.2021
  Time: 14:10
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
        <div class="wrapper">
            <header class='header_menu' >
                <h1>webdev</h1>
                <nav>
                    <ul class="main_menu">
                        <li class="header_item" id="first"><a href="#">Заказы</a></li>
                        <li class="header_item parent_item"><a href="#">Товары</a>
                            <ul class="submenu">
                                <li><a href="controller?command=productview&name=ebook"> Электронные книги</a></li>
                                <li><a href="controller?command=productview&name=tablet">Планшеты</a></li>
                                <li><a href="controller?command=productview&name=laptop">Ноутбуки</a></li>
                                <li><a href="controller?command=productview&name=smartphone">Смартфоны</a></li>
                            </ul>
                        </li>
                        <li class="header_item">
                            <a href="#">
                                <img src="img/icon.svg" alt="header-logo" class="header-logo">
                            </a>
                        </li>
                        <li class="header_item"><a href="#">Пользователи</a></li>
                        <li class="header_item"><a href="#">Отзывы</a></li>
                        <li><img src="img/person.png" width="40px" height="40px"></li>
                    </ul>
                </nav>
            </header>
        </div>
    </body>
</html>
