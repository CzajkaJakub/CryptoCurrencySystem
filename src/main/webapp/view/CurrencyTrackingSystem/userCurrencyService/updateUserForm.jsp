<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

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
    <form:form method="get" action="showAdminForm" cssStyle="text-align: right">
    <input href="showMainSystem" type="image" src="${pageContext.request.contextPath}/resources/userCurrencyService/images/arrow.png" alt="Submit" width="24" height="24">
    </form:form>


    <form:form method="get" action="processAnUpdate" modelAttribute="userToUpdate">
        <div class="title">Chan</div>
        <div class="subtitle">Change it </div>
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
