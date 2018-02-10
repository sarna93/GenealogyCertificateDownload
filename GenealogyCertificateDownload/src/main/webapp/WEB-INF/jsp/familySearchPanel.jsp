<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="<c:url value = "/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>" />

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
					<li><a href="/szukajwarchiwach">szukajwarchiwach.pl</a></li>
					<li><a href="/familysearch">familysearch.org</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>${portal}</h1>
			<h2>${message}</h2>
		</div>

	</div>

	<form>
		<div class="form-group">
			<label for="inputCertificateLink">Link to genealogy
				certificate</label> <input type="email" class="form-control"
				id="inputCertificateLink" aria-describedby="linkHelp"
				placeholder="Enter link"> <small id="LinkHelp"
				class="form-text text-muted">...</small>
		</div>
		<div class="form-group">
			<label for="formSelectDownload">Download type</label> <select
				class="form-control" id="formSelectDownload">
				<option>Single Certificate</option>
				<option>Certificates Book</option>
			</select>
		</div>
		
		<button type="submit" action= class="btn btn-primary">Submit</button>
	</form>

	<script type="text/javascript" src="/webjars/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>