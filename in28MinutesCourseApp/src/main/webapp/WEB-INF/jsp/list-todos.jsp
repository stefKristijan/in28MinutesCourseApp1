<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
	<table class="table table-striped">
		<caption>Your todos are:</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it done?</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td><fmt:formatDate value="${todo.targetDate}" pattern="dd.MM.yyyy."/></td>
					<td>${todo.done}</td> 
					<td><a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Edit</a></td>
					<td><a type="button" class="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a>
			</c:forEach>
		</tbody>
	</table>
	<div class="row">
		<a class="btn btn-primary" href="/add-todo">Add a ToDo</a>
	</div>
</div>
	<script src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<%@ include file="common/footer.jspf" %>