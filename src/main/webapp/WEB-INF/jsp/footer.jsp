<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='../styles/footer/footer.css' %>
    </style>
</head>
    <body>
        <footer class="wrapper">
            <diV class="lang">
                <a href="controller?command=changelanguage&local=en">
                    <fmt:message bundle="${loc}" key="local.button.en"/>
                </a> |
                <a href="controller?command=changelanguage&local=ru">
                    <fmt:message bundle="${loc}" key="local.button.ru"/>
                </a>
            </diV>
        </footer>
    </body>
</html>
