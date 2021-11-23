<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
        <%@include file='../../styles/header/header.css' %>
    </style>
</head>
    <body>
        <header>
            <section class="user_panel">
                <div class="wrapper user">
                    <div>
                        <a href="controller?command=gotomainpage">
                            <img src="img/logo.png">
                        </a>
                    </div>
                    <p class="name">Black & White</p>
                    <div>
                        <a href="controller?command=logout">
                            <img src="img/log_out.png" width="20px" height="20px" alt="log out">
                        </a>
                    </div>
                </div>
            </section>
            <div class="wrapper">
                <nav class="admin">
                    <ul class="menu">
                        <li>
                            <a>
                                <fmt:message bundle="${loc}" key="local.header.goods"/>
                            </a>
                            <ul>
                                <li>
                                    <a href="controller?command=productview&name=laptop&criterion=all">
                                        <fmt:message bundle="${loc}" key="local.header.laptops"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=productview&name=tablet&criterion=all">
                                        <fmt:message bundle="${loc}" key="local.header.tablets"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=productview&name=ebook&criterion=all">
                                        <fmt:message bundle="${loc}" key="local.header.ebooks"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=productview&name=smartphone&criterion=all">
                                        <fmt:message bundle="${loc}" key="local.header.smartphone"/>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="controller?command=ordersview&page=1&criterionOrder=all">
                                <fmt:message bundle="${loc}" key="local.header.orders"/>
                            </a>
                            <ul>
                                <li>
                                    <a href="controller?command=ordersview&page=1&name=laptop&criterionOrder=status&status=new">
                                        NEW
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=ordersview&page=1&name=laptop&criterionOrder=status&status=RECEIVED">
                                        RECEIVED
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=ordersview&page=1&name=laptop&criterionOrder=status&status=SENT">
                                        SENT
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=ordersview&page=1&name=laptop&criterionOrder=status&status=PROCESSED">
                                         PROCESSED
                                    </a>
                                </li>
                                <li>
                                    <a href="controller?command=ordersview&page=1&name=laptop&criterionOrder=status&status=CANCELED">
                                        CANCELED
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="controller?command=alluserview">
                                <fmt:message bundle="${loc}" key="local.header.users"/>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>
    </body>
</html>
