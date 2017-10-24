<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<font color="red">${errorMessage}</font>
	<form method="post">
		Name: <input type ="text" name="name"/>
		Password: <input type="password" name="password"/>
		<input type="submit"/>
	</form>
<%-- 	<h1>${name}, please log in to continue</h1> --%>
<%@ include file="common/footer.jspf" %>