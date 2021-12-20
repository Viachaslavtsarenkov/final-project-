<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>
            <fmt:message bundle="${loc}" key="login.registration"/>
        </title>
        <style>
            <%@include file='../styles/style.css' %>
            <%@include file="../styles/registration/registration.css"%>
        </style>
    </head>
    <body>
    <jsp:useBean id="user" type="by.tsarenkov.shop.bean.UserRegistrationInfo"
                 beanName="by.tsarenkov.shop.bean.UserRegistrationInfo" scope="request">
     </jsp:useBean>
    <c:choose>
        <c:when test="${sessionScope.role == 'GUEST'}">
            <c:import url="header/header.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:forward page="main.jsp"></jsp:forward>
        </c:otherwise>
    </c:choose>
        <main>
            <section class="wrapper data">
                <div class = "registration">
                    <h2>
                        <fmt:message bundle="${loc}" key="login.newAcc.text"/>
                    </h2>
                    <form class="user_inf" action="controller" method="post">
                        <input type="hidden" name="command" value="savenewuser"/>
                        <input maxlength="40"
                                placeholder="<fmt:message bundle="${loc}" key="user.firstName"/>"
                                value="${user.name}"
                                class="input_field" id="name" type="text" name="name" required />
                        <c:if test="${requestScope.errorValidation.get('name') != null}">
                            <div class="validation">
                                <fmt:message bundle="${loc}" key="error.validation.simpleText"/>
                            </div>
                        </c:if>
                        <br>
                        <input placeholder="<fmt:message bundle="${loc}" key="user.lastName"/>"
                               class="input_field" id="surname" type="text" name="surname" required
                               value="${user.surname}"
                               maxlength="40"/>
                        <c:if test="${requestScope.errorValidation.get('surname') != null}">
                            <div class="validation">
                            <fmt:message bundle="${loc}" key="error.validation.simpleText"/>
                            </div>
                        </c:if>
                        <br>
                        <input  placeholder="<fmt:message bundle="${loc}" key="user.email"/>"
                                class="input_field" id="email" type="text" name="email" required
                                value="${user.email}"
                                maxlength="50"/>
                        <c:if test="${requestScope.errorValidation.get('email') != null}">
                            <div class="validation">
                            <fmt:message bundle="${loc}" key="${requestScope.errorValidation.get('email')}"/>
                            </div>
                        </c:if>
                        <br>
                        <input  placeholder="<fmt:message bundle="${loc}" key="user.password"/>"
                                class="input_field"
                                id="password" type="text" name="password" required
                                maxlength="30"/>
                        <c:if test="${requestScope.errorValidation.get('password') != null}">
                            <div class="validation">
                            <fmt:message bundle="${loc}" key="${requestScope.errorValidation.get('password')}"/>
                            </div>
                        </c:if>
                        <br>
                        <input  placeholder="<fmt:message bundle="${loc}" key="user.repeatPassword"/>"
                                class="input_field" id="repeated" type="text" name="repeated-password" required
                                maxlength="30"/><br>
                        <c:if test="${requestScope.errorValidation.get('repeated') != null}">
                            <div class="validation">
                                <fmt:message bundle="${loc}" key="${requestScope.errorValidation.get('repeated')}"/>
                            </div>
                        </c:if>
                        <input  placeholder="<fmt:message bundle="${loc}" key="user.phone"/>"
                                class="input_field" id="phoneNumber" type="text" name="phoneNumber" required
                                maxlength="14"
                                value="${user.phoneNumber}"
                        />
                        <c:if test="${requestScope.errorValidation.get('phoneNumber') != null}">
                            <div class="validation">
                            <fmt:message bundle="${loc}" key="${requestScope.errorValidation.get('phoneNumber')}"/>
                            </div>
                        </c:if>
                        <br>
                        <input class="input_btn" type="submit"
                               value="<fmt:message bundle="${loc}" key="local.button.registration"/>"/>
                    </form>
                </div>
                <div class="join_us">
                    <h3>
                        <fmt:message bundle="${loc}" key="login.msg"/>
                    </h3>
                </div>
            </section>
        </main>
        <c:import url="footer.jsp"/>
    </body>
</html>
