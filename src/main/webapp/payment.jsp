<%@page import="dao.Dao"%>
<%@page import="dto.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>






<% Dao dao = new Dao(); %>

<% int movieid =(int) session.getAttribute("movieid") ; %>

<% Movie m = dao.findMovieById(movieid) ;%>
<table border="1px">

<tr>

<td>Movie Price</td> <td> <%=m.getMovieprice() %></td>

</tr>
</table>


<form action="payment" method="post" >

Enter Your Payment Amount   :<input type="number" name="amount" required ><br><br>



<input type="submit">

</form>
 

<% String mes = (String)request.getAttribute("mes"); %>
<%if(mes != null){ %>
<%= mes %>
<% } %>

</body>
</html>