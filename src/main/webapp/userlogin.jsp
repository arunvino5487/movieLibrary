<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="userlog" method="post">


User Mail  :<input type="email" name="umail"><br><br>
User password  :<input type="password" name="upass"><br><br>

<input type="submit">

<%String mes = (String)request.getAttribute("mes"); %>
<%if(mes != null){ %>
<%= mes %>
<%} %>

</form>


</body>
</html>