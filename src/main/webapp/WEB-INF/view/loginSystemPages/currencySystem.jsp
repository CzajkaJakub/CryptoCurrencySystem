<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency System</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/mediaIcons.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/userFormStyle.scss">
    <script src="https://kit.fontawesome.com/dd120be7c7.js" crossorigin="anonymous"></script>
</head>

<body>

<div class="background-container">
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1231630/moon2.png" alt="">
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="clouds"></div>
</div>

<div class="form">
    <form:form action="showMainSystem" method="get" id="backWrapper">
        <input type="image" src="${pageContext.request.contextPath}/resources/userCurrencyService/images/home.png" alt="Submit" width="24" height="24">
    </form:form>
    <div class="title">Welcome</div>
    <div class="subtitle">Let's check your crypto-currencies!</div>
    <form:form method="get" action="loginSystem/logIntoSystem">
        <button type="submit"  class="submit">Login</button>
    </form:form>
    <form:form method="get" action="registerSystem/registerNewUser">
        <button type="submit"  class="submit">Register</button>
    </form:form>
    <form:form method="get" action="adminSystem/logIntoSystem">
        <button type="submit"  class="submit">Admin panel</button>
    </form:form>


    <div class="wrapper">
        <a href="https://www.facebook.com/kuba.czajka.376" target="_blank">
            <div class="icon facebook">
                <div class="tooltip">Facebook</div>
                <span><i class="fab fa-facebook-f"></i></span>
            </div>
        </a>

        <a href="https://github.com/CzajkaJakub" target="_blank">
            <div class="icon github">
                <div class="tooltip">Github</div>
                <span><i class="fab fa-github"></i></span>
            </div>
        </a>

        <a href="https://www.linkedin.com/in/jakub-c-66479a140/" target="_blank">
            <div class="icon linkedin">
                <div class="tooltip">LinkedIn</div>
                <span><i class="fab fa-linkedin"></i></span>
            </div>
        </a>

        <a href='mailto:CzajkaPL@o2.pl' target="_blank">
            <div class="icon email">
                <div class="tooltip">Email</div>
                <span><i class="fa fa-envelope"></i></span>
            </div>
        </a>

    </div>
</div>
</body>
</html>
