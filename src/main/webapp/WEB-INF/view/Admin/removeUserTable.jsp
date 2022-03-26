<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsUsers" %>
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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/roller.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <li><a href="${pageContext.request.contextPath}/">Dashboard</a></li>

        <security:authorize access="not hasAnyRole('USER', 'ADMIN')">
            <li><a href="${pageContext.request.contextPath}/user/showLoginForm">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/showRegisterForm">Register</a></li>
        </security:authorize>

        <%--USER SECTION --%>
        <security:authorize access="hasAnyRole('ADMIN', 'USER')">
            <li>
                <span class="loader" id="loader"></span>
                <a onclick="document.getElementById('loader').style.visibility='visible'" href="${pageContext.request.contextPath}/user/showSortedCurrencies">Show currencies</a></li>
            </security:authorize>

        <security:authorize access="hasAnyRole('ADMIN', 'USER')">
            <li>
                <a href="${pageContext.request.contextPath}/user/showCryptoForm">Add your crypto address</a></li>
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


            <c:url var="sortId" value="/admin/showTableToRemove">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.id_sort) %>" />
            </c:url>
            <c:url var="sortUsername" value="/admin/showTableToRemove">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.username_sort) %>" />
            </c:url>
            <c:url var="sortFirstName" value="/admin/showTableToRemove">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.first_name_sort) %>" />
            </c:url>
            <c:url var="sortLastName" value="/admin/showTableToRemove">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.last_name_sort) %>" />
            </c:url>
            <c:url var="sortEmail" value="/admin/showTableToRemove">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.email_sort) %>" />
            </c:url>

            <th><a href="${sortId}"><h1>Id</h1></a></th>
            <th><a href="${sortUsername}"><h1>Username</h1></a></th>
            <th><h1>Password</h1></th>
            <th><a href="${sortFirstName}"><h1>First name</h1></a></th>
            <th><a href="${sortLastName}"><h1>Last name</h1></a></th>
            <th><a href="${sortEmail}"><h1>Email</h1></a></th>
            <th><h1>Delete</h1></th>


        </tr>
        </thead>

        <tbody>
        <c:forEach var="tempUser" items="${usersData}">
            <tr>
                <td>${tempUser.id}</td>
                <td>${tempUser.userName}</td>
                <td>${tempUser.password}</td>
                <td>${tempUser.firstName}</td>
                <td>${tempUser.lastName}</td>
                <td>${tempUser.email}</td>
                <td style="text-align: center">
                    <c:url var="deleteLink" value="/admin/deleteAnAccount">
                        <c:param name="userId" value="${tempUser.id}"/>
                    </c:url>

                    <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this user?'))) return false"><i id="trashIcon" class="fa fa-trash-o"></i></a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
