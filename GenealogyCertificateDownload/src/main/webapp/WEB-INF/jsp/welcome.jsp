<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">

<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />


</head>
<body>

		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">Genealogy Certificate Download</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/szukajwarchiwach/panel">szukajwarchiwach.pl</a></li>
						<li><a href="/familysearch/panel">familysearch.org</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">

			<div class="starter-template">
				<h1>Spring Boot Web JSP Example</h1>
				<h2>Message: ${message}</h2>
			</div>

		</div>

		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>