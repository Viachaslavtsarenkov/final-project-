<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Title</title>
        <style>
            <%@include file='../../styles/products/products.css' %>
        </style>
    </head>
    <body>
    <jsp:useBean id="product" type="by.tsarenkov.shop.bean.good.Smartphone"
                 beanName="by.tsarenkov.shop.bean.good.Smartphone" scope="request">
    </jsp:useBean>
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="../header/header_admin.jsp"/>
        </c:when>
    </c:choose>

    <main class="wrapper">
        <section class="section_data">
            <form class="form_input" action="controller" method="post" enctype="multipart/form-data">
                <c:choose>
                    <c:when test="${requestScope.product.id == 0}">
                        <input type="hidden" name="command" value="savenewproduct" />
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="savechangedebook" />
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="id_category" value="4" />
                <input type="hidden" name="name" value="smartphone" />
                <input type="hidden" name="id" value="${product.id}" />
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.brand"/>
                </label>
                <input type="text" name="brand"
                       required
                       maxlength="50"
                       value="${product.brand}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.model"/>
                </label>
                <input type="text" name="model"
                       required
                       maxlength="50"
                       value="${product.model}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.OS"/>
                </label>
                <input type="text" name="operation_system"
                       required
                       maxlength="50"
                       value="${product.operationSystem}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.diagonal"/>
                </label>
                <input type="text" name="diagonal"
                       pattern="\d+(.\d{1})?" required  title="Введите число"
                       maxlength="6"
                       value="${product.diagonal}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.ROM"/>
                </label>
                <input type="text" name="rom"
                       pattern="^[0-9]+$" required
                       maxlength="2"
                       value="${product.ROM}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.RAM"/>
                </label>
                <input type="text" name="ram"
                       maxlength="2"
                       pattern="^[0-9]+$" required
                       value="${product.RAM}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.countSimCards"/>
                </label>
                <input type="text" name="count_sim_card"
                       pattern="^[0-9]+$" required
                       maxlength="1"
                       value="${product.countSimCard}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.CPU"/>
                </label>
                <input type="text" name="cpu"
                       maxlength="50"
                       required
                       value="${product.CPU}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.countCores"/>
                </label>
                <input type="text" name="count_cores"
                       pattern="^[0-9]+$" required
                       value="${product.countCores}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.frontCamera"/>
                </label>
                <input type="text" name="front_camera"
                       pattern="^[0-9]+$" required
                       maxlength="3"
                       value="${product.countPixelFrontCamera}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.backCamera"/>
                </label>
                <input type="text" name="camera"
                       pattern="^[0-9]+$" required
                       maxlength="3"
                       value="${product.countPixelBackCamera}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.price"/>
                </label>
                <input type="text" name="price"
                       pattern="^\d+(.)\d{0,2}$" required
                       value="${product.price}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.photo"/>
                </label>
                <input type="file" name="photo" />
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.count"/>
                </label>
                <input type="text" name="count" pattern="^[ 0-9]+$"
                       required
                       value="${product.count}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.status"/>
                </label>
                <select id="select" name="status">
                    <option value="SUPPLIED">
                        <fmt:message bundle="${loc}" key="product.status.supplied"/>
                    </option>
                    <option value="NOT_SUPPLIED">
                        <fmt:message bundle="${loc}" key="product.status.nSupplied"/>
                    </option>
                </select>
                <input class = "show_product_btn" type="submit" name="save"
                       value="<fmt:message bundle="${loc}" key="local.button.save"/>"/>
            </form>
        </section>
    </main>
    <c:import url="../footer.jsp"/>

    <script>
        const select = document.querySelector('#select').getElementsByTagName('option');
        let current = "<jsp:getProperty name="product" property="status"/>";
        for (let i = 0; i < select.length; i++) {
            if (select[i].value === current) {
                select[i].selected = true;
            }
        }
    </script>
    </body>
</html>
