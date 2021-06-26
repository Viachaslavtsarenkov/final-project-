<%--
  Created by IntelliJ IDEA.
  User: RedmiBook
  Date: 11.05.2021
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pagination" uri="/WEB-INF/tld/taglib.tld" %>
<html>
    <head>
        <title>Title</title>
        <style>
            <%@include file='../styles/style.css' %>
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <c:import url="header_admin.jsp"/>
            <a class="blubtn" href="controller?command=gotopage&page=${requestScope.name.toString()}_input_page.jsp">
                Добавить
            </a>
                <c:choose>
                    <c:when test="${requestScope.products.size() > 0}">
                        <table class="table_price">
                            <tr>
                                <th>ID</th>
                                <th>Название</th>
                                <th>Количество</th>
                                <th>Цена</th>
                                <th>Статус</th>
                                <th>Действия</th>
                            </tr>

                        <c:forEach var="i" begin="0" end="${requestScope.products.size() - 1}">
                            <tr>
                                <td>
                                    <p>${requestScope.products.get(i).id}</p>
                                </td>
                                <td>${requestScope.products.get(i).brand}   ${requestScope.products.get(i).model}</td>
                                <td>${requestScope.products.get(i).count}</td>
                                <td>${requestScope.products.get(i).price}</td>
                                <td>${requestScope.products.get(i).status}</td>
                                <td>
                                    <a
                                       href="controller?command=particularebookview&name=${requestScope.name}&id=${requestScope.products.get(i).id}">
                                        Посмотреть
                                    </a>
                                    <br>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>

                    </c:when>
                    <c:otherwise>
                        <c:out value="Нет товаров в данной категории"/>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <c:otherwise>
                <jsp:include page="header.jsp"/>
                <c:choose>
                    <c:when test="${requestScope.products.size() > 0}">
                        <section class="products">
                           <c:forEach var="i" begin="0" end="${requestScope.products.size() - 1}">
                               <section class="products wrapper">
                                   <div class="product_item">
                                       <img src="${requestScope.products.get(i).path}" width="160px" height="200">
                                       <p>${requestScope.products.get(i).brand} ${requestScope.products.get(i).model}</p>
                                       <p color="red">${requestScope.products.get(i).price}</p>
                                       <a class="show_product_btn"
                                       href = "controller?command=particularebookview&name=${requestScope.name}&id=${requestScope.products.get(i).id}">
                                           Посмотреть товар
                                       </a>
                                   </div>
                               </section>
                           </c:forEach>
                        </section>
                    </c:when>
                    <c:otherwise>
                        <c:out value="Нет товаров в данной категории"/>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
       <c:if test="${requestScope.products.size() > 0 && requestScope.countPage > 1}">
           <section class="pagination_btn" >
               <c:if test="${requestScope.page != 1}">
                   <a style="color:black"
                      href="controller?command=productview&name=${requestScope.name}&page=${requestScope.page - 1}"><</a>
               </c:if>
               <c:forEach var="i" begin="1" end="${requestScope.countPage}" step="1">
                   <c:choose>
                       <c:when test="${i eq requestScope.page}">
                           <a style="color:black"
                              href="controller?command=productview&name=${requestScope.name}&page=${i}">${i}</a>
                       </c:when>
                       <c:otherwise>
                           <a style="color:red"
                              href="controller?command=productview&name=${requestScope.name}&page=${i}">${i}</a>
                       </c:otherwise>
                   </c:choose>
               </c:forEach>
               <c:if test="${requestScope.page != requestScope.countPage}">
                   <a style="color:black"
                      href="controller?command=productview&name=${requestScope.name}&page=${requestScope.page + 1}">></a>
               </c:if>
           </section>
       </c:if>
    </body>
</html>
