<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <form:form method="get" action="showDashboard" id="backWrapper">
    <input href="showMainSystem" type="image" src="${pageContext.request.contextPath}/resources/userCurrencyService/images/arrow.png" alt="Submit" width="24" height="24">
    </form:form>



    <form:form method="get" action="processAnUpdate" modelAttribute="userToUpdate">
        <div class="title">Admin panel</div>
        <div class="subtitle">Change user's data</div>
        <div class="input-container ic1">

<%--            need to assiciate user with id              --%>
            <form:hidden path="id"/>
            <form:errors path="login" cssClass="loginError" />
            <form:input  path="login" id="login" cssClass="input" type="text" readonly="true"/>
            <div class="cut cut-short"></div>
            <label for="login" class="placeholder">Login</label>
        </div>
        <div class="input-container ic2">
            <form:errors path="password" cssClass="loginError" />
            <form:input path="password" id="password" cssClass="input" type="password" placeholder="." />
            <div class="cut"></div>
            <label for="password" class="placeholder">Password</label>
        </div>
        <div class="input-container ic2">
            <form:errors path="email" cssClass="loginError" />
            <form:input path="email" id="email" cssClass="input" type="text" placeholder="."/>
            <div class="cut cut-short"></div>
            <label for="email" class="placeholder">Email</label>
        </div>
        <input type="submit" class="submit" value="Update changes"/>
    </form:form>
    </div>


</body>
</html>
