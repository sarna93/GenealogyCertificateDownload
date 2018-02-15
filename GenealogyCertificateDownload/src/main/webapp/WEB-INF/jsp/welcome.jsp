<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap  -->
<link href="<c:url value = "/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>"
        rel="stylesheet">

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

			<div class="starter-template text-center">
				<h1>${message}</h1>
				<h2></h2>
			</div>

		</div>

	<script type="text/javascript" src="/webjars/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>