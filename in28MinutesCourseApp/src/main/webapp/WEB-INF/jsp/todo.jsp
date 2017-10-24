<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
<title>First Web Application</title>
<link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body>
	<div class="container">
		<form:form method="post" commandName="todo">
			<form:hidden path="id"/>
			<fieldset class="form-group">
				<form:label path="desc">Description</form:label> 
				<form:input path="desc" type="text"
					class="form-control" required="required"/>
				<form:errors path="desc" cssClass="text-warning"/>
			</fieldset>

			<button type="submit" class="btn btn-success">Add</button>
		</form:form>
	</div>

	<script src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>