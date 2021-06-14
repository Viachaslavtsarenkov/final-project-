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
</head>
<body>
<header class="wrapper">
    <nav>
        <img src="img/icon.svg" width="50px" height="50px" alt="electronic.com">
        <ul class="menu_list">
            <a href="controller?command=ebookview"><li>Смартфоны</li></a>
            <li>Ноутбуки</li>
            <li>Планшеты</li>
            <li>Электронные книги</li>
        </ul>
        <div class="person">
            <a href="controller?command=gotopersonalpage"><img src="img/avatar.svg" width="20px" height="30px"/></a>
            <img src="img/basket.svg" width="20px" height="30px"/>
        </div>
    </nav>
</header>
</body>
</html>
