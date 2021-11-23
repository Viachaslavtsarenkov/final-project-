<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>
        </title>
        <meta charset="UTF-8" />
        <style>
            <%@include file='../styles/login/login.css' %>
        </style>
    </head>
    <body>
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
                        <fmt:message bundle="${loc}" key="login.login.text"/>
                    </h2>
                    <form action="controller?command=login" method="post">
                        <input
                                placeholder="<fmt:message bundle="${loc}" key="user.email"/>"
                                class="input_field" id="name" type="text" name="login" required />
                        <br>
                        <input placeholder="<fmt:message bundle="${loc}" key="user.password"/>"
                               class="input_field" id="surname" type="text" name="password" required
                               maxlength="40"/>
                        <input class="input_btn" type="submit"
                               value="<fmt:message bundle="${loc}" key="local.button.signIn"/>" />
                        <c:if test="${requestScope.loginError != null}">
                            <p class="validation">
                                <fmt:message bundle="${loc}" key="${requestScope.loginError}"/>
                            </p>
                        </c:if>
                    </form>
                </div>
                <div class="join_us">
                    <h3>
                        <fmt:message bundle="${loc}" key="login.msg"/>
                    </h3>
                    <a href="controller?command=gotoregistrationpage" class="action_btn">
                        <fmt:message bundle="${loc}" key="local.button.createAccount"/>
                    </a>
                </div>
            </section>
        </main>
        <c:import url="footer.jsp"/>
    </body>
</html>
