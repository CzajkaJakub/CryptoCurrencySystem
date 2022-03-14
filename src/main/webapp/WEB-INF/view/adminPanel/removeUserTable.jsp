<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Users list</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/adminPanel.scss">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/navbar.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/table.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/background.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<div class="background-container">
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="clouds"></div>
</div>

<div class="btn"> <span class="fas fa-bars"></span> </div>
<nav class="sidebar">
    <div class="text"> Admin panel </div>
    <ul class="main_side">
        <li class="active"><a href="showDashboard">Dashboard</a></li>
        <li> <a id="1">Users<span class="fas fa-caret-down"></span></a>
            <ul class="item-show-1">
                <li><a href="showTable">Show users</a></li>
                <li><a href="showTableToUpdate">Update an Account</a></li>
                <li><a href="showTableToRemove">Delete an account</a></li>
            </ul>
        </li>
        <li><a href="showCurrencies">Show currencies</a></li>
        <li><a href="../showMainSystem">Log out</a></li>
    </ul>
</nav>

<div class="content">
    <div class="content2">
        <div id="main-container">

            <div id="container">
                <div id="content">
                    <table>
                        <tr>
                            <th>Login</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>

                        <c:forEach var="tempUser" items="${usersData}">
                            <tr>
                                <td>${tempUser.login}</td>
                                <td>${tempUser.password}</td>
                                <td>${tempUser.email}</td>
                                <td>
                                    <c:url var="deleteLink" value="/adminSystem/deleteAnAccount">
                                        <c:param name="userId" value="${tempUser.id}"/>
                                    </c:url>

                                    <a href="${deleteLink}">Delete an user</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>
    </div>

</div>





<script src="${pageContext.request.contextPath}/resources/userCurrencyService/js/navbar.js"></script>
</body>
</html>
