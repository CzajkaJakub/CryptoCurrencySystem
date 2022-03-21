<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/userFormStyle.scss">
</head>

<body>

<div class="background-container">
    <img src="${pageContext.request.contextPath}/resources/userCurrencyService/images/moon2.png" alt="">
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="clouds"></div>
</div>


<div class="form">
    <form:form method="get" action="../showMainSystem" id="backWrapper">
        <input href="showMainSystem" type="image" src="${pageContext.request.contextPath}/resources/userCurrencyService/images/arrow.png" alt="Submit" width="24" height="24">
    </form:form>


    <form:form action="${pageContext.request.contextPath}/authenticateTheUser"
               method="POST">

        <div class="title">Welcome</div>
        <div class="subtitle">Let's login into your account!</div>
        <div class="input-container ic1">

            <c:if test="${param.error != null}">
                <i class="loginError">Sorry! You entered invalid username/password.</i>
            </c:if>

            <input type="text" name="username" placeholder="  " class="input" id="login" />
            <div class="cut cut-short"></div>
            <label for="login" class="placeholder">Login</label>
        </div>
        <div class="input-container ic2">
            <input type="password" name="password" placeholder="  " class="input" id="password" />
            <div class="cut"></div>
            <label for="password" class="placeholder">Password</label>
        </div>
        <input type="submit" class="submit" value="Login into account"/>
    </form:form>
</div>


</body>
</html>
