<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link href="<c:url value = "/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>"
        rel="stylesheet">

<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />



</head>
<body>
	<div class="container">
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
				<h1>${portal}</h1>
				<h2>${message}</h2>
			</div>

		</div>


		<form:form method="post" action="${action}" commandName="downloadForm"
			class="form-horizontal">
			<table cellpadding="10">
				<tr>
					<th>Link to certificate</th>
					<th>Download Type</th>
				</tr>
				<tr>
					<td>
						<div class="form-group col-md-12">
							<div class="col-md-7">
								<form:input type="text" size="100" path="linkToCertificate"
									id="linkToCertificate" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="linkToCertificate" class="help-inline" />
								</div>
							</div>
						</div>
					</td>
					<td>
						<div class="form-group col-md-12">
							<div class="col-md-7">
								<form:select id="downloadType" name="downloadType"
									path="downloadType">
									<form:option selected="selected" value="singleCertificate">Single Certificate</form:option>
									<form:option value="certificatesBook">Certificates Book</form:option>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<div class="container">
				<button type="submit">Download</button>

			</div>
		</form:form>

	<script type="text/javascript" src="/webjars/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</div>

</body>

</html>