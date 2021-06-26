<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 21.06.2021
  Time: 13:36
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
<main class="wrapper">
    <section class="section_data">

        <form class="form" action="controller" method="post">
            <input type="hidden" name="command" value="addneworder" />
            order
            <input type="submit" name="save" value="Сохранить"/>
        </form>
    </section>
</main>
</body>
</html>
