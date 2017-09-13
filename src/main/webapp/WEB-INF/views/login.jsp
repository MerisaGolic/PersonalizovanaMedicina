<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Symptom this!!!</title>
  <link href="<c:url value="/resources/login.css" />" rel="stylesheet">
</head>

<body>
<div class="container">
	<section id="content">
		<form:form action="provjeraLogina" method="post" commandName="loginForm">
			<h1>Login</h1>
			<p>${LoginFailed}</p>
			<div>
				<form:input type="text" placeholder="Username" required="" id="username" path="username" />
			</div>
			<div>
				<form:input type="password" placeholder="Password" required="" id="password" path="password" />
			</div>
			<div>
				<input type="submit" value="Log in" />
			</div>
		</form:form><!-- form -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>