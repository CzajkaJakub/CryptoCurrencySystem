<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsUsers" %>
<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies" %>
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

            <c:url var="sortLinkName" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.name_sort) %>" />
            </c:url>
            <c:url var="sortLinkSymbol" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.symbol_sort) %>" />
            </c:url>
            <c:url var="sortLinkCurrentPrice" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.current_price_sort) %>" />
            </c:url>
            <c:url var="sortLinkMarketCap" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.market_cap_sort) %>" />
            </c:url>
            <c:url var="sortLinkMarketCapRank" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.market_cap_rank_sort) %>" />
            </c:url>
            <c:url var="sortLinkAth" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.ath_sort) %>" />
            </c:url>
            <c:url var="sortLinkAtl" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.atl_sort) %>" />
            </c:url>
            <c:url var="sortLinkHigh24h" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.high_24h_sort) %>" />
            </c:url>
            <c:url var="sortLinkLow24h" value="/adminSystem/showSortedCurrencies">
                <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.low_24h_sort) %>" />
            </c:url>



            <th><h1><a href="${sortLinkName}">Name</a></h1></th>
            <th><h1><a href="${sortLinkSymbol}">Symbol</a></h1></th>
            <th><h1><a href="${sortLinkMarketCapRank}">Market cap rank</a></h1></th>
            <th><h1><a href="${sortLinkMarketCap}">Market cap [$]</a></h1></th>
            <th><h1><a href="${sortLinkCurrentPrice}">Currency price [$]</a></h1></th>
            <th><h1><a href="${sortLinkAth}">Ath [$]</a></h1></th>
            <th><h1><a href="${sortLinkAtl}">Atl [$]</a></h1></th>
            <th><h1><a href="${sortLinkHigh24h}">High 24h [$]</a></h1></th>
            <th><h1><a href="${sortLinkLow24h}">Low 24h [$]</a></h1></th>
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
