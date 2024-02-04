<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="saveuser" method="post">


User Id	:<input type="number" name="uid"><br><br>
User name	:<input type="text" name="uname"><br><br>
User contact:<input type="tel" name="ucontact"><br><br>
User Mail  :<input type="email" name="umail"><br><br>
User password  :<input type="password" name="upass"><br><br>

<input type="submit">

</form>

<% String mes = (String)request.getAttribute("mes"); %>
<%if(mes != null){ %>
<%= mes %>
<% } %>
</body>
</html>