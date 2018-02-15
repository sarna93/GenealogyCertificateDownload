<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html lang="en">
<head>

<!-- Access the bootstrap  -->
<link
	href="<c:url value = "/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>"
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

		<div class="container">

			<div class="starter-template text-center">
				<h1>${portal}</h1>
				<h2>${message}</h2>
			</div>

		</div>

		<form:form method="post" action="${action}" commandName="downloadForm"
			class="form-horizontal">
			<div class="form-group">
				<label for="email">Link to certificate:</label>
				<form:input type="text" size="100" path="linkToCertificate"
					id="linkToCertificate" class="form-control input-sm" />
			</div>

			<div class="form-group">
				<label for="email">Absolute folder path to save certifate(s)</label>
				<form:input type="text" size="100" path="folderPath" id="folderPath"
					class="form-control input-sm" />
			</div>

			<div class="form-group">
				<label for="email">Download Type</label>
				<form:select id="downloadType" name="downloadType"
					path="downloadType">
					<form:option selected="selected" value="singleCertificate">Single Certificate</form:option>
					<form:option value="certificatesBook">Certificates Book</form:option>
				</form:select>
			</div>
			<button class="btn btn-success" type="submit">Download</button>

		</form:form>
		<div class="starter-template text-center">
				<h2><font color="${downloadMessageColor}">${downloadMessage}</font></h2>
		</div>

		<script type="text/javascript"
			src="/webjars/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript"
			src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</div>
</body>

</html>