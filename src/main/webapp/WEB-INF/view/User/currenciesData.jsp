<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 22.03.2022
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<table>
    <thead>
    <tr>

        <c:url var="sortLinkName" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.name_sort) %>" />
        </c:url>
        <c:url var="sortLinkSymbol" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.symbol_sort) %>" />
        </c:url>
        <c:url var="sortLinkCurrentPrice" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.current_price_sort) %>" />
        </c:url>
        <c:url var="sortLinkMarketCap" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.market_cap_sort) %>" />
        </c:url>
        <c:url var="sortLinkMarketCapRank" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.market_cap_rank_sort) %>" />
        </c:url>
        <c:url var="sortLinkAth" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.ath_sort) %>" />
        </c:url>
        <c:url var="sortLinkAtl" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.atl_sort) %>" />
        </c:url>
        <c:url var="sortLinkHigh24h" value="/user/showSortedCurrencies">
            <c:param name="sortType" value="<%= Integer.toString(SortUtilsCurrencies.high_24h_sort) %>" />
        </c:url>
        <c:url var="sortLinkLow24h" value="/user/showSortedCurrencies">
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


</body>
</html>
