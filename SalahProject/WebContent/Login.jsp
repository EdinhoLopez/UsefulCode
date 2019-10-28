<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Products</title>
</head>
<body>

	<h2>HTML Forms</h2>

<!-- The form calls this Servelet's post method -->
<form action = "LoginServlet" method = "post">

  <!-- Text fields for user input. Info sent to Servlet -->
  Product ID:<br>
  <input type="text" name="ProductID" placeholder="Enter id...">
  <br>
  <br>
  Product name:<br>
  <input type="text" name="ProductName" placeholder="Enter name...">
  <br>
  <br>
  Product Price:<br>
  <input type="text" name="ProductPrice" placeholder="Enter price...">
  <br><br>
  <input type="submit" value="Create product">
</form> 
	

</body>
</html>