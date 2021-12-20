<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pagination" uri="/WEB-INF/tld/taglib.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>
            <fmt:message bundle="${loc}" key="local.header.${requestScope.name}s"/>
        </title>
        <style>
            <%@include file='../../styles/products/products.css' %>
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <c:import url="../header/header_admin.jsp"/>
            <div class="wrapper product_table">
                <a class="add_btn" href="controller?command=gotopage&page=${requestScope.name}_input_page_path">
                Добавить
                </a>
                <c:choose>
                    <c:when test="${requestScope.products.size() > 0}">
                        <table class="table_price">
                            <thead>
                                <tr>
                                    <th>
                                        <fmt:message bundle="${loc}" key="product.characteristic.id"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${loc}" key="product.characteristic.name"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${loc}" key="product.characteristic.count"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${loc}" key="product.characteristic.price"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${loc}" key="product.characteristic.status"/>
                                    </th>
                                    <th> </th>
                                </tr>
                            </thead>
                        <c:forEach var="i" begin="0" end="${requestScope.products.size() - 1}">
                            <tbody>
                                <tr>
                                    <td>
                                        <p>${requestScope.products.get(i).id}</p>
                                    </td>
                                    <td>${requestScope.products.get(i).brand}   ${requestScope.products.get(i).model}</td>
                                    <td>${requestScope.products.get(i).count}</td>
                                    <td>${requestScope.products.get(i).price}</td>
                                    <td>
                                           <fmt:message bundle="${loc}" key="${requestScope.products.get(i).status}"/>
                                    </td>
                                    <td>
                                        <a class="add_btn"
                                           href="controller?command=particularebookview&name=${requestScope.name}&id=${requestScope.products.get(i).id}">
                                            <fmt:message bundle="${loc}" key="local.button.see"/>
                                        </a>
                                        <br>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <c:out value="Нет товаров в данной категории"/>
                    </c:otherwise>
                </c:choose>
            </div>
            </c:when>

            <c:otherwise>
                <jsp:include page="../header/header.jsp"/>
                <c:choose>
                    <c:when test="${requestScope.products.size() > 0}">
                        <section class="products wrapper">
                            <c:forEach var="i" begin="0" end="${requestScope.products.size() - 1}">
                                   <div class="product_item">
                                       <img src="/images/${requestScope.products.get(i).path}" width="160px" height="200">
                                       <p>${requestScope.products.get(i).brand} ${requestScope.products.get(i).model}</p>
                                       <p color="red">${requestScope.products.get(i).price}</p>
                                       <a class="show_product_btn"
                                       href = "controller?command=particularebookview&id=${requestScope.products.get(i).id}">
                                           <fmt:message bundle="${loc}" key="local.button.see"/>
                                       </a>
                                   </div>
                           </c:forEach>
                        </section>
                    </c:when>
                    <c:otherwise>
                        <p class="wrapper noproducts">
                            <fmt:message bundle="${loc}" key="error.product.notFound"/>
                        </p>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
       <c:if test="${requestScope.products.size() > 0 && requestScope.countPage > 1}">
           <section class="pagination_btn wrapper" >
               <div class="btn_navigation">
                   <c:if test="${requestScope.page != 1}">
                       <a style="color:black"
                          href="controller?command=productview&name=${requestScope.name}&page=${requestScope.page - 1}&criterion=${requestScope.criterion}"><</a>
                   </c:if>
                   <c:forEach var="i" begin="1" end="${requestScope.countPage}" step="1">
                       <c:choose>
                           <c:when test="${i eq requestScope.page}">
                               <a style="color:black"
                                  href="controller?command=productview&name=${requestScope.name}&page=${i}&criterion=${requestScope.criterion}">${i}</a>
                           </c:when>
                           <c:otherwise>
                               <a class="add_btn"
                                  href="controller?command=productview&criterion=${requestScope.criterion}&name=${requestScope.name}&page=${i}">${i}</a>
                           </c:otherwise>
                       </c:choose>
                   </c:forEach>
                   <c:if test="${requestScope.page != requestScope.countPage}">
                       <a style="color:black"
                          href="controller?command=productview&name=${requestScope.name}&page=${requestScope.page + 1}&criterion=${requestScope.criterion}">></a>
                   </c:if>
               </div>
           </section>
       </c:if>
        <c:import url="../footer.jsp"/>
    </body>
</html>
