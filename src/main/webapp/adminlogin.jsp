<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login Page</title>
 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
          	background-image:url("https://media.istockphoto.com/id/524538382/photo/urban-informatization-and-network-technology.jpg?s=1024x1024&w=is&k=20&c=2WQ6Rb3q871Dp1jJJ3z0sU3_e5KRDpZSOXEgfHMU0gw=");
            text-align: center;
            background-size:cover;
            margin: 0;
            padding: 0;
        }

        .login {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            margin-top: 100px;
            border-radius: 5px;
            box-shadow: 0 0 10px ;
           
        }
         .login
        {
        	background-image: url("https://media.istockphoto.com/id/1066096074/photo/building-skyscraper-view-from-below-modern-architecture-industry-of-the-future.jpg?s=1024x1024&w=is&k=20&c=FjWjYpmqhxkSVYhJZgBf9B3c4tT8fxvA1ufa61xXd4c=");  
        }
        

        input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
           
        }

        button {
            background-color: green;
            color: white;
            padding: 10px 15px;
          
            border-radius: 4px;
            cursor: pointer;
        }
        p
        {
        	color: red ;
        	font-size: 1.5em;
        
        }
    </style>
</head>
<body>



<div class="login">
    <h2>Login</h2>
   <form action="adminlogin" method="post">
        <label for="username">Mail:</label>
        <input type="text" id="username" name="amail" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="apass" required>

         <button type="submit" >Login </button>
    </form>
</div>



<% String message = (String)request.getAttribute("message"); %>
<%if(message != null){ %>
<p><%= message %></p>
<% } %>



</body>
</html>