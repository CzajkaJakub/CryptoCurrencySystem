<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtilsUsers" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Users list</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/navbar.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/table.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/background.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
</head>
<body>

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
    <div class="background-container">
        <div class="stars"></div>
        <div class="twinkling"></div>
        <div class="clouds"></div>
    </div>
    <table class="container">
        <thead>
        <tr>

            <th><h1>Name</h1></th>
            <th><h1>Symbol</h1></th>
            <th><h1>Market cap rank</h1></th>
            <th><h1>Market cap</h1></th>
            <th><h1>Currency price [$]</h1></th>
            <th><h1>Ath</h1></th>
            <th><h1>Atl</h1></th>
            <th><h1>High 24h</h1></th>
            <th><h1>Low 24h</h1></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="tempCurrency" items="${currenciesData}">
            <tr>
                <td>${tempCurrency.name}</td>
                <td>${tempCurrency.symbol}</td>
                <td>${tempCurrency.market_cap_rank}</td>
                <td>${tempCurrency.market_cap}</td>
                <td>${tempCurrency.current_price}</td>
                <td>${tempCurrency.ath}</td>
                <td>${tempCurrency.atl}</td>
                <td>${tempCurrency.high_24h}</td>
                <td>${tempCurrency.low_24h}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<script src="${pageContext.request.contextPath}/resources/userCurrencyService/js/navbar.js"></script>
</body>
</html>
