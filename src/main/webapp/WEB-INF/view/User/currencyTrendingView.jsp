<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crypto Tracker</title>
    <link rel="icon" href="https://cdn2.iconfinder.com/data/icons/cryptocurrency-vanilla-coins/90/Coin-BTC-Vanilla-3-512.png">
</head>
<body style="margin: 0">
<div class="tradingview-widget-container">
    <div id="tradingview_309ff"></div>
    <div class="tradingview-widget-copyright">
    <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
    <script type="text/javascript">
        new TradingView.widget(
            {
                "autosize": true,
                "symbol": "GATEIO:${currencySymbol}USDT",
                "interval": "30",
                "timezone": "Europe/Warsaw",
                "theme": "dark",
                "style": "3",
                "locale": "pl",
                "toolbar_bg": "#f1f3f6",
                "enable_publishing": false,
                "withdateranges": true,
                "allow_symbol_change": true,
                "container_id": "tradingview_309ff"
            }
        );
    </script>
    </div>
</div>
</body>
</html>
