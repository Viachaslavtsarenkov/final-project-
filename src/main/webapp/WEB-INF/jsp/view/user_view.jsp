<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message bundle="${loc}" key="local.header.users"/>
    </title>
    <style>
        <%@include file='../../styles/products/products.css' %>
    </style>
</head>
    <body>
    <c:import url="../header/header_admin.jsp"/>
    <main class="wrapper">
        <table class="table_price">
            <tr>
                <th>
                    <fmt:message bundle="${loc}" key="product.characteristic.id"/>
                </th>
                <th>
                    <fmt:message bundle="${loc}" key="user.text"/>
                </th>
                <th><fmt:message bundle="${loc}" key="user.phone"/></th>
                <th><fmt:message bundle="${loc}" key="user.email"/></th>
                <th><fmt:message bundle="${loc}" key="product.characteristic.status"/></th>
                <th></th>
            </tr>

            <c:forEach var="i" begin="0" end="${requestScope.userList.size() - 1}">
                <tr>
                    <td>
                        ${requestScope.userList.get(i).userId}
                    </td>
                    <td>
                            ${requestScope.userList.get(i).name}
                            ${requestScope.userList.get(i).surname}
                    </td>
                    <td>${requestScope.userList.get(i).phoneNumber}</td>
                    <td>${requestScope.userList.get(i).email}</td>
                    <td>${requestScope.userList.get(i).status}</td>
                    <td>
                        <a href="controller?command=gotopersonalpage&user=${requestScope.userList.get(i).userId}">
                            <fmt:message bundle="${loc}" key="local.button.see"/>
                        </a>
                        <br>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
    <c:import url="../footer.jsp"/>
    </body>
</html>
