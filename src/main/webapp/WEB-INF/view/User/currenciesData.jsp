<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsCurrencies" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/background.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/navbar.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/mediaIcons.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/cryptoTable.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <title>Crypto Tracker</title>
</head>

<body>

<div class="background-container">-
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1231630/moon2.png" alt="">
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="clouds"></div>
</div>

<input type="checkbox" id="active">
<label for="active" class="menu-btn"><i class="fas fa-bars" style="margin-top: 15px"></i></label>
<div class="wrapper">
    <ul>
        <li><a href="${pageContext.request.contextPath}/">Dashboard</a></li><br><br>

        <security:authorize access="not hasAnyRole('USER', 'ADMIN')">
            <li><a href="${pageContext.request.contextPath}/user/showLoginForm">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/showRegisterForm">Register</a></li>
        </security:authorize>

        <%--USER SECTION --%>
        <security:authorize access="hasAnyRole('ADMIN', 'USER')">
            <li><a href="${pageContext.request.contextPath}/user/showSortedCurrencies">Show currencies</a></li>
        </security:authorize>



        <%--ADMIN SECTION --%>
        <security:authorize access="hasRole('ADMIN')">
            <li><a href="${pageContext.request.contextPath}/admin/showUsersTable">Show users in database</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/showTableToRemove">Remove an user</a></li>
        </security:authorize>


        <%--LOGOUT BUTTON + DATA ACCOUNT --%>
        <security:authorize access="hasAnyRole('ADMIN', 'USER')">
            <form:form id="logoutForm"
                       action="${pageContext.request.contextPath}/logout"
                       method="POST">
                <li>User: <security:authentication property="principal.username" /></li>
                <li>Role: <security:authentication property="principal.authorities" /></li>
            </form:form>
            <li><a href="#" onclick="if(confirm('Are you sure you log out?')) document.getElementById('logoutForm').submit()">Logout</a></li>
        </security:authorize>

        <div id="mediaIcon">

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

            <a href='https://www.czajkajakub.pl' target="_blank">
                <div class="icon portfolio">
                    <div class="tooltip">Portfolio</div>
                    <span><i class="fas fa-briefcase"></i></span>
                </div>
            </a>

        </div>
    </ul>

</div>

<div class="content">
    <table class="container">
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
            <tr><td colspan="9">

                <!-- TradingView Widget BEGIN -->
                <div class="tradingview-widget-container">
                    <div id="tradingview_d346a"></div>
                    <div class="tradingview-widget-copyright"><a href="https://pl.tradingview.com/symbols/AAPL/" rel="noopener" target="_blank"><span class="blue-text">Apple</span></a> od TradingView</div>
                    <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>

                    <script type="text/javascript">
                        new TradingView.MediumWidget(
                            {
                                "symbols": [
                                    [
                                        "Apple",
                                        "AAPL"
                                    ],
                                    [
                                        "Google",
                                        "GOOGL"
                                    ],
                                    [
                                        "Microsoft",
                                        "MSFT"
                                    ]
                                ],
                                "chartOnly": false,
                                "width": 1000,
                                "height": 400,
                                "locale": "pl",
                                "colorTheme": "dark",
                                "gridLineColor": "rgba(240, 243, 250, 0)",
                                "fontColor": "#787B86",
                                "isTransparent": false,
                                "autosize": false,
                                "showVolume": false,
                                "scalePosition": "no",
                                "scaleMode": "Normal",
                                "fontFamily": "-apple-system, BlinkMacSystemFont, Segoe UI, Trebuchet MS, Roboto, Ubuntu, sans-serif",
                                "noTimeScale": false,
                                "valuesTracking": "1",
                                "chartType": "line",
                                "container_id": "tradingview_d346a"
                            }
                        );
                    </script>


                </div>
                <!-- TradingView Widget END -->


            </td> </tr>


        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>









