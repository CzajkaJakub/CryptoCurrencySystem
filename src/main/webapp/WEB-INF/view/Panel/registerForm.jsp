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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/form.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/CurrencyService/css/roller.css">
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
    <div class="login-box">
        <h2>Create new account!</h2>

        <!-- Registration Form -->
        <form:form action="${pageContext.request.contextPath}/processRegistrationData"
                   modelAttribute="user" id="registerForm">

            <div class="user-box">
                <form:input path="userName" required="true"/>
                <label>Username</label>
                <form:errors path="userName" cssClass="registerError" />
            </div>

            <div class="user-box">
                <form:input path="password" required="true" type="password"/>
                <label>Password</label>
                <form:errors path="password" cssClass="registerError" />
            </div>

            <div class="user-box">
                <form:input path="matchingPassword" required="true" type="password"/>
                <label>Matching password</label>
                <form:errors path="matchingPassword" cssClass="registerError" />
            </div>

            <div class="user-box">
                <form:input path="firstName" required="true"/>
                <label>First name</label>
                <form:errors path="firstName" cssClass="registerError" />
            </div>


            <div class="user-box">
                <form:input path="lastName" required="."/>
                <label>Last name</label>
                <form:errors path="lastName" cssClass="registerError" />
            </div>

            <div class="user-box">
                <form:input path="email" required="true"/>
                <label>Email</label>
                <form:errors path="email" cssClass="registerError" />
                <!-- Check for registration error -->
                <c:if test="${registrationResponse != null}">
                    <div class="registrationResponse">
                            ${registrationResponse}
                    </div>
                </c:if>
            </div>

            <a href="#" onclick="document.getElementById('registerForm').submit()">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                Create an account
            </a>

        </form:form>
    </div>
</div>
</body>
</html>




















