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
        <fmt:setLocale value="en" scope="session"/>
        <fmt:setBundle basename="locale" var="loc" scope="session"/>
    </head>
    <body>
        <div class="wrapper">
            <header class='header_menu' >
                <h1>store</h1>
                <nav>
                    <ul class="main_menu">
                        <li class="header_item" id="first">
                            <a href="controller?command=productview&name=ebook">
                                <fmt:message bundle="${loc}" key="local.header.ebooks"/>
                            </a>
                        </li>
                        <li class="header_item">
                            <a href="controller?command=productview&name=smartphone">
                                <fmt:message bundle="${loc}" key="local.header.smartphone"/>
                            </a></li>
                        <li class="header_item">
                            <a href="#">
                                <img src="img/icon.svg" alt="header-logo" class="header-logo">
                            </a>
                        </li>
                        <li class="header_item">
                            <a href="controller?command=productview&name=tablet">
                                <fmt:message bundle="${loc}" key="local.header.tablets"/>
                            </a></li>
                        <li class="header_item">
                            <a href="controller?command=productview&name=laptop">
                                <fmt:message bundle="${loc}" key="local.header.laptops"/>
                            </a></li>
                        <li>
                            <a href="controller?command=gotopersonalpage">
                                <img src="img/person.png" width="40px" height="40px">
                            </a>
                            <a href="controller?command=gotobasketpage">
                                <img src="img/basket.png" width="30px" height="30px">
                            </a>
                        </li>
                    </ul>
                </nav>
            </header>
        </div>
    </body>
</html>
