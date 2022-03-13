<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Users list</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/userCurrencyService/css/customerResult.scss">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>UAM - Users Accounts Manager</h2>
    </div>
</div>

<div id="menu-buttons">
    <a href="../showMainSystem" class="menu-button">Log out</a>
    <a href="../portfolio/showPortfolio" class="menu-button">Back to portfolio</a>
</div>

<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Currency</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Value</th>
            </tr>
            <c:forEach var="tempUser" items="${users}">
                <tr>
                    <td>${tempUser.login}</td>
                    <td>${tempUser.password}</td>
                    <td>${tempUser.email}</td>
                    <td>
                        todo
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>



</body>
</html>
