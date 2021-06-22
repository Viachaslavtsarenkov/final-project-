<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 15.06.2021
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <c:import url="header_admin.jsp"/>
        </c:when>
    </c:choose>
    <main class="wrapper">
        <section class="section_data">
            <form class="form_input" action="controller" method="post">
                <c:choose>
                <c:when test="${requestScope.ebook == null}">
                <input type="hidden" name="command" value="savenewproduct" />
                </c:when>
                <c:otherwise>
                <input type="hidden" name="command" value="savechangedebook" />
                </c:otherwise>
                </c:choose>
                <input type="hidden" name="command" value="savenewproduct" />
                <input type="hidden" name="id_category" value="4" />
                <input type="hidden" name="name" value="smartphone" />
                <input type="hidden" name="command" value="savenewproduct" />
                <input type="hidden" name="id_category" value="1" />
                <label>Бренд</label>
                <input type="text" name="name"/>
                <label>Модель</label>
                <input type="text" name="model"/>
                <label>ОС</label>
                <input type="text" name="operation_system"/>
                <label> Диагональ</label>
                <input type="text" name="diagonal"/>
                <label>ROM</label>
                <input type="text" name="rom"/>
                <label>RAM</label>
                <input type="text" name="ram"/>
                <label>Количество симкарт</label>
                <input type="text" name="count_sim_card"/>
                <label>CPU</label>
                <input type="text" name="cpu"/>
                <label>Количество ядер</label>
                <input type="text" name="count_cores"/>
                <label>Фронтальная камера</label>
                <input type="text" name="front_camera"/>
                <label>Камера</label>
                <input type="text" name="camera"/>
                <label>Цена</label>
                <input type="text" name="price" value="${requestScope.ebook.price}" />
                <label>Фото</label>
                <input type="file" name="photo" />
                <label>Количество</label>
                <input type="text" name="count" pattern="^[ 0-9]+$" value="${requestScope.ebook.count}"/>
                <label>Статус</label>
                <select name="status">
                    <option value="EXPECTED">Ожидаем поступления</option>
                    <option value="SUPPLIED">В наличии</option>
                    <option value="NOT_SUPPLIED">Не поставляется</option>
                </select>
                <input class = "blubtn" type="submit" name="save" value="Сохранить"/>
            </form>
        </section>
    </main>
    </body>
</html>
