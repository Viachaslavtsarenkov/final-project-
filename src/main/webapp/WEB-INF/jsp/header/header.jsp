<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="baskettag" uri="/WEB-INF/tld/taglib.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc" scope="session"/>

<style>
    <%@include file='../../styles/header/header.css' %>
</style>
<header>
    <section class="user_panel">
        <div class="wrapper">
            <div>
                <a href="controller?command=gotomainpage">
                    <img src="img/logo.png">
                </a>
            </div>
            <p class="name">Black & White</p>
            <div>
                <form class="search" action="controller?command=productview" method="post">
                    <input type="text" class="search_field" name="search">
                    <input type="hidden" name="criterion" value="name">
                    <input type="submit" value="Поиск" class="search_btn">
                </form>
                <a href="controller?command=gotopersonalpage">
                    <img src="img/icon_login.svg" width="32px" height="20px">
                </a>
                <a href="controller?command=gotobasketpage">
                    <baskettag:basket count="${sessionScope.count}"/>
                </a>
                <c:if test="${sessionScope.role eq 'CUSTOMER'}">
                    <a href="controller?command=logout">
                        <img src="img/log_out.png" width="20px" height="20px" alt="log out">
                    </a>
                </c:if>
            </div>
        </div>
    </section>
    <div class="wrapper">
        <nav>
            <ul class="menu">
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
                <li>
                    <a href="controller?command=productview&name=tablet&criterion=all">
                        <fmt:message bundle="${loc}" key="local.header.tablets"/>
                    </a>
                </li>
                <li>
                    <a href="controller?command=productview&name=laptop&criterion=all">
                        <fmt:message bundle="${loc}" key="local.header.laptops"/>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</header>

