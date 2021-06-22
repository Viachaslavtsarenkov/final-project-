<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 21.06.2021
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main class="wrapper">
    <section class="section_data">
        <form class="form" action="controller" method="post">
            <input type="hidden" name="command" value="saveorder" />
            <label>${requestScope.product.brand} ${requestScope.product.model}</label>

            <input type="text" name="brand" value="${requestScope.ebook.brand}"/><br>
            <input type="submit" name="save" value="Сохранить"/>
        </form>
    </section>
</main>
</body>
</html>
