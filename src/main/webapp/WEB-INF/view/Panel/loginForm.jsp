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
	<div class="login-box">
		<h2>Log into your account!</h2>

		<!-- Login Form -->
		<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" id="loginForm">

			<div class="user-box">
				<input type="text" name="username" required="">
				<label>Username</label>
			</div>

			<div class="user-box">
				<input type="password" name="password" required="">
				<label>Password</label>
			</div>

			<c:if test="${param.error != null}">
				<div class="loginError">Invalid username or password.</div>
			</c:if>

			<a href="#" onclick="document.getElementById('loginForm').submit()">
				<span></span>
				<span></span>
				<span></span>
				<span></span>
				Login
			</a>

		</form:form>
	</div>
</div>
</body>
</html>









