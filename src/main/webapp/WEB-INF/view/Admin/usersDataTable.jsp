<%@ page import="com.example.cryptocurrencytrackingsystem.UserCurrencyService.SortUtils.SortUtilsUsers" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Users list</title>
</head>
<body>

<div class="btn"> <span class="fas fa-bars"></span> </div>

<div class="content">
    <table class="container">
        <thead>
        <tr>


            <c:url var="sortId" value="/admin/showUsersTable">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.id_sort) %>" />
            </c:url>
            <c:url var="sortUsername" value="/admin/showUsersTable">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.username_sort) %>" />
            </c:url>
            <c:url var="sortFirstName" value="/admin/showUsersTable">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.first_name_sort) %>" />
            </c:url>
            <c:url var="sortLastName" value="/admin/showUsersTable">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.last_name_sort) %>" />
            </c:url>
            <c:url var="sortEmail" value="/admin/showUsersTable">
                <c:param name="sort" value="<%= Integer.toString(SortUtilsUsers.email_sort) %>" />
            </c:url>

            <th><h1><a href="${sortId}">Id</a></h1></th>
            <th><h1><a href="${sortUsername}">Username</a></h1></th>
            <th><h1>Password</h1></th>
            <th><h1><a href="${sortFirstName}">First name</a></h1></th>
            <th><h1><a href="${sortLastName}">Last name</a></h1></th>
            <th><h1><a href="${sortEmail}">Email</a></h1></th>

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

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<script src="${pageContext.request.contextPath}/resources/userCurrencyService/js/navbar.js"></script>
</body>
</html>
