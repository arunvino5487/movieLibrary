<%@page import="java.util.Base64"%>
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


<%
	 List<Movie>  movies = (List) request.getAttribute("movies");
%>

<table border="1px">
<tr>
<th>id</th>
<th>name</th>
<th>price</th>
<th>Rating</th>
<th>image</th>
<th>Buy</th>
</tr>

<%
for(Movie m : movies){
%>
<tr>
<td><%=m.getMovieid() %></td>
<td><%=m.getMoviename() %></td>
<td><%=m.getMovieprice() %></td>
<td><%=m.getMovierating() %></td>


<%String base64image = new String(Base64.getEncoder().encode(m.getMovieimage())); %>
<td><img src="data:image/jpeg;base64 , <%=base64image%>" height="120px" width="120px"></td>

<td><a href="saveusermovie?id=<%=m.getMovieid()%>">Buy</a></td>


</tr>

<% } %>
    

</table>
<a href="watchmovie">Watch movie</a>
<a href="userlogout">logout </a>


</body>
</html>