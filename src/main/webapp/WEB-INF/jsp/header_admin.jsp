<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 08.06.2021
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='../styles/style.css' %>
    </style>
    <fmt:setLocale value="en" scope="session"/>
    <fmt:setBundle basename="locale" var="loc" scope="session"/>
</head>
    <body>
        <div class="wrapper">
            <header class='header_menu' >
                <h1>webdev</h1>
                <nav>
                    <ul class="main_menu">
                        <li class="header_item" id="first"><a href="#">
                            <fmt:message bundle="${loc}" key="local.header.orders"/>
                        </a></li>
                        <li class="header_item parent_item"><a href="#">
                            <fmt:message bundle="${loc}" key="local.header.goods"/>
                        </a>
                            <ul class="submenu">
                                <li>
                                    <a href="controller?command=productview&name=ebook">
                                        <fmt:message bundle="${loc}" key="local.header.ebooks"/>
                                    </a></li>
                                <li><a href="controller?command=productview&name=tablet">
                                    <fmt:message bundle="${loc}" key="local.header.tablets"/>
                                </a></li>
                                <li><a href="controller?command=productview&name=laptop">
                                    <fmt:message bundle="${loc}" key="local.header.laptops"/>
                                </a></li>
                                <li><a href="controller?command=productview&name=smartphone">
                                    <fmt:message bundle="${loc}" key="local.header.smartphone"/>
                                </a></li>
                            </ul>
                        </li>
                        <li class="header_item">
                            <a href="#">
                                <img src="img/icon.svg" alt="header-logo" class="header-logo">
                            </a>
                        </li>
                        <li class="header_item"><a href="#">Пользователи</a></li>
                        <li class="header_item"><a href="#">Отзывы</a></li>
                        <li>
                            <img src="img/person.png" width="40px" height="40px">
                        </li>
                    </ul>
                </nav>
            </header>
        </div>
    </body>
</html>
