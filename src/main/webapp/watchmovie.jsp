<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="dto.Movie"%>
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
List<Movie>  movies = (List) request.getAttribute("usermovie");
%>

<table border="1px">
<tr>
<th>Userid</th>
<th>UserName</th>
<th>MovieId</th>
<th>MovieName</th>
<th>Movie Image</th>
<th>DeleteWatchMovie</th>

</tr>

<%
for(Movie m : movies){
%>
<tr>
<td><%= session.getAttribute("userid")%></td>
<td><%= session.getAttribute("username")%></td>
<td><%=m.getMovieid() %></td>
<td><%=m.getMoviename() %></td>



<%String base64image = new String(Base64.getEncoder().encode(m.getMovieimage())); %>
<td><img src="data:image/jpeg;base64 , <%=base64image%>" height="120px" width="120px"></td>

<td><a href="deletewatchmovie?id=<%=m.getMovieid()%>">Delete Watch Movie</a></td>
</tr>


<% }%>


</table>

<% String mes = (String)request.getAttribute("mes"); %>
<%if(mes != null){ %>
<%= mes %>
<% } %>

</body>
</html>