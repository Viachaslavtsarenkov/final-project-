<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message bundle="${loc}" key="local.header.input"/>
    </title>
    <style>
        <%@include file='../../styles/products/products.css' %>
    </style>
</head>
<body>
    <jsp:useBean id="product" type="by.tsarenkov.shop.bean.good.EBook"
                 beanName="by.tsarenkov.shop.bean.good.EBook" scope="request">
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

                <input type="hidden" name="id_category" value="1" />
                <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>" />
                <input type="hidden" name="name" value="ebook" />
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.brand"/>
                </label>
                <input type="text" name="brand"
                       maxlength="50" required
                       value="${product.brand}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.model"/>
                </label>
                <input type="text" name="model" required maxlength="50"
                       value="${product.model}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.ROM"/>
                </label>
                <input type="text" name="rom" pattern="^[0-9]+$" required title="?????????????? ??????????"
                       maxlength="2"
                       value="${product.ROM}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.RAM"/>
                </label>
                <input type="text" name="ram" pattern="^[0-9]+$" required  title="?????????????? ??????????"
                       maxlength="2"
                       value="${product.RAM}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.typeCardMemory"/>
                </label>
                <input type="text"
                       pattern="[A-Za-z??-????-??\s????0-9]{2,}" required
                       name="type_card_memory"
                       maxlength="50"
                       value="${product.typeCardMemory}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.typeScreen"/>
                </label>
                <input type="text"
                       pattern="[A-Za-z??-??\s??-??????\s]{2,}" required
                       name="type_screen"
                       maxlength="50"
                       value="${product.typeScreen}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.batteryCapacity"/>
                </label>
                <input type="text" name="battery_capacity" pattern="^[0-9]+$" required  title="?????????????? ??????????"
                       maxlength="5"
                       value="${product.batteryCapacity}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.diagonal"/>
                </label>
                <input type="text" name="diagonal" pattern="\d+(.\d{1})?" required  title="?????????????? ??????????"
                       maxlength="6"
                       value="${product.diagonal}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.wifi"/>
                </label>
                <select name="wifi" id="wifi">
                    <option value="yes">
                        <fmt:message bundle="${loc}" key="local.button.yes"/>
                    </option>
                    <option value="no">
                        <fmt:message bundle="${loc}" key="local.button.no"/>
                    </option>
                </select>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.bluetooth"/>
                </label>
                <select name="bluetooth" id="bluetooth">
                    <option value="yes">
                        <fmt:message bundle="${loc}" key="local.button.yes"/>
                    </option>
                    <option value="no">
                        <fmt:message bundle="${loc}" key="local.button.no"/>
                    </option>
                </select>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.formats"/>
                </label>
                <input type="text" name="formats"
                       pattern="[A-Za-z??-????-??????0-9,\s]{2,}" required
                       maxlength="50"
                       value="${product.formats}"/>
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
                <input type="text" name="count" pattern="^[0-9]+$" required
                       title="?????????????? ?????????? ??????????"
                       value="${product.count}"/>
                <label>
                    <fmt:message bundle="${loc}" key="product.characteristic.status"/>
                </label>
                <select selected="${product.status.toString()}"
                        id="select" name="status">
                    <option value="SUPPLIED">
                        <fmt:message bundle="${loc}" key="product.status.supplied"/>
                    </option>
                    <option value="NOT_SUPPLIED">
                        <fmt:message bundle="${loc}" key="product.status.nSupplied"/>
                    </option>
                </select>
                <input  class = "show_product_btn" type="submit" name="save"
                        value="<fmt:message bundle="${loc}" key="local.button.save"/>"/>
            </form>
        </section>
    </main>
    <c:import url="../footer.jsp"/>
    <script>
        const select = document.querySelector('#select').getElementsByTagName('option');
        let current = "${product.status}";
        for (let i = 0; i < select.length; i++) {
            if (select[i].value === current) {
                select[i].selected = true;
            }
        }

        const bluetooth = document.querySelector('#bluetooth').getElementsByTagName('option');
        let CurrentBluetooth = ${product.bluetooth};
        if (CurrentBluetooth) {
            bluetooth[0].selected = true;
        } else {
            bluetooth[1].selected = true;
        }

        const selectWiFI = document.querySelector('#wifi').getElementsByTagName('option');
        let currentWIFI = ${product.wiFi};
        if (currentWIFI) {
            selectWiFI[0].selected = true;
        } else {
            selectWiFI[1].selected = true;
        }
    </script>
</body>
</html>
