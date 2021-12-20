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
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="changelanguage">
                    <input type="hidden" name="langpage" value="${requestScope.langpage.toString()}">
                    <input type="hidden" name="local" value="ru">
                    <input type="submit" value="rus">
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="changelanguage">
                    <input type="hidden" name="langpage" value="${requestScope.langpage.toString()}">
                    <input type="hidden" name="local" value="en">
                    <input type="submit" value="eng">
                </form>
            </diV>
        </footer>
    </body>
</html>
