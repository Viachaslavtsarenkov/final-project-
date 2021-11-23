<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>
        </title>
        <style>
            <%@include file='../styles/main/style.css' %>
        </style>
        <meta charset="UTF-8" />
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <c:import url="header/header_admin.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="header/header.jsp"/>
            </c:otherwise>
        </c:choose>
        <main class="wrapper">
            <section class="inf">
                <div class="inf_content">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.main.experience"/>
                    </h2>
                    <input class="inf_action" type="button"
                           value="<fmt:message bundle="${loc}" key="local.button.shopNow"/>">
                </div>
            </section>
            <section class="products">
                <div class="mobile">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.header.smartphone"/>
                    </h2>
                    <a class="show_btn" href="controller?command=productview&name=smartphone&criterion=all">
                        <fmt:message bundle="${loc}" key="local.button.seeMore"/>
                    </a>
                </div>
                <div class="laptop">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.header.laptops"/>
                    </h2>
                    <a class="show_btn" href="controller?command=productview&name=laptop&criterion=all">
                        <fmt:message bundle="${loc}" key="local.button.seeMore"/>
                    </a>
                </div>

                <div class="tablet">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.header.tablets"/>
                    </h2>
                    <a class="show_btn" href="controller?command=productview&name=tablet&criterion=all">
                        <fmt:message bundle="${loc}" key="local.button.seeMore"/>
                    </a>
                </div>

                <div class="ebook">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.header.ebooks"/>
                    </h2>
                    <a class="show_btn" href="controller?command=productview&name=ebook&criterion=all">
                        <fmt:message bundle="${loc}" key="local.button.seeMore"/>
                    </a>
                </div>
            </section>
            <section class="about_store">
                <div>
                    <img src="img/main/delivery.png">
                    <h3 class="about_store_description">
                        <fmt:message bundle="${loc}" key="local.main.delivery"/>
                    </h3>
                </div>
                <div>
                    <img src="img/main/price.png">
                    <h3 class="about_store_description">
                        <fmt:message bundle="${loc}" key="local.main.favorable"/>
                    </h3>
                </div>
                <div>
                    <img src="img/main/quality.png">
                    <h3 class="about_store_description">
                        <fmt:message bundle="${loc}" key="local.main.quality"/>
                    </h3>
                </div>
            </section>
        </main>
        <c:import url="footer.jsp"/>
    </body>
</html>