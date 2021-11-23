<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 15.06.2021
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='../../styles/products/products.css'%>
    </style>
</head>
<body>
    <jsp:useBean id="product" scope="request" beanName="by.tsarenkov.shop.bean.good.Laptop"
    type="by.tsarenkov.shop.bean.good.Laptop">
    </jsp:useBean>
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="../header/header_admin.jsp"/>
        </c:when>
    </c:choose>
    <main>
        <main class="wrapper">
            <section class="section_data">
                <form class="form_input" action="controller"method="post" enctype="multipart/form-data">
                    <c:choose>
                        <c:when test="${requestScope.product.id == 0}">
                            <input type="hidden" name="command" value="savenewproduct" />
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="command" value="savechangedebook" />
                        </c:otherwise>
                        </c:choose>
                    <input type="hidden" name="id_category" value="3" />
                    <input type="hidden" name="name" value="laptop" />
                    <input type="hidden" name="id" value="<jsp:getProperty name="product" property="id"/>" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.brand"/>
                    </label>
                    <input type="text" name="brand"
                           required maxlength="50"
                    value="${product.brand}" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.model"/>
                    </label>
                    <input type="text" name="model"
                           required maxlength="50"
                           value="${product.model}" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.type"/>
                    </label>
                    <input type="text" name="type"
                           required maxlength="50"
                           value="${product.diagonal}"  />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.diagonal"/>
                    </label>
                    <input type="text" name="diagonal"
                           pattern="\d+(.\d{1})?" required  title="Введите число в формате 1.1"
                           maxlength="6"
                           value="${product.diagonal}"/>
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.typeProcessor"/>
                    </label>
                    <select id="processor"
                            name="type_processor" value="${product.typeProcessor}"/>" >
                        <option value="AMD">AMD</option>
                        <option value="INTEL">INTEL</option>
                    </select>
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.serialProcessor"/>
                    </label>
                    <input type="text" name="serial_processor"
                           maxlength="50" required
                           value="${product.serialProcessor}" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.ROM"/>
                    </label>
                    <input type="text" name="rom"
                           pattern="^[0-9]+$" required title="Введите число"
                           maxlength="2"
                           value="<jsp:getProperty name="product" property="ROM"/>" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.RAM"/>
                    </label>
                    <input type="text" name="ram"
                           pattern="^[0-9]+$" required title="Введите число"
                           maxlength="2"
                           value="<jsp:getProperty name="product" property="RAM"/>" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.bluetoothVersion"/>
                    </label>
                    <input type="text" name="bluetooth_version"
                           pattern="\d+(.\d{1})?" required  title="Введите число в формате 1.1"
                           maxlength="6"
                           value="${product.bluetoothVersion}"/>
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.coutnUSBPorts"/>
                    </label>
                    <input type="text" name="count_usb_port"
                           pattern="^[0-9]+$" required title="Введите число"
                           maxlength="2"
                           value="${product.countUSBPort}"/>
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.typeGraphicsCard"/>
                    </label>
                    <input type="text" name="type_graphics_card"
                           required maxlength="50"
                           value="${product.typeGraphicsCard}" />
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.graphicsCard"/>
                    </label>
                    <input type="text" name="graphics_card"
                           required maxlength="50"
                           value="${product.graphicsCard}"/>
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.screenExtension"/>
                    </label>
                    <input type="text" name="screen_extension"
                           maxlength="10"
                           required
                           value="${product.screenExtension}"/>
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
                    <input type="text" name="count" pattern="^[ 0-9]+$" required
                           value="${product.count}"/>
                    <label>
                        <fmt:message bundle="${loc}" key="product.characteristic.status"/>
                    </label>
                    <select name="status" id="select" <jsp:getProperty name="product" property="status"/>>
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
                let current = "${product.status}";
                for (let i = 0; i < select.length; i++) {
                    if (select[i].value === current) {
                        select[i].selected = true;
                    }
                }

                const selectProcessor = document.querySelector('#processor').getElementsByTagName('option');
                let currentProcessor = "${product.typeProcessor}";
                for (let i = 0; i < selectProcessor.length; i++) {
                    if (selectProcessor[i].value === currentProcessor) {
                        selectProcessor[i].selected = true;
                    }
                }
            </script>
</body>
</html>
