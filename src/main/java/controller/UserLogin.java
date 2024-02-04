package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import dao.Dao;
import dto.User;
@WebServlet("/userlog")
public class UserLogin extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		   String useremail = req.getParameter("umail");
		   String userpass  = req.getParameter("upass");
		  	   
		   Dao dao = new Dao();
		   
		   PrintWriter p = resp.getWriter();
		   try {		   
					   
					User user = dao.findUser(useremail);
					
					
					if(user!=null)
					{
						if(user.getUserpassword().equals(userpass)) 
						{
							if(user.getUseremail().equals(useremail)) 
							{
						
							HttpSession session = req.getSession();
							session.setAttribute("userid", user.getUserid());
							session.setAttribute("username", user.getUsername());
							
							req.setAttribute("movies", dao.getMovie());							
							RequestDispatcher ds = req.getRequestDispatcher("userhome.jsp");
							ds.include(req, resp);
							
							}
							
							
						}
						else
						{
							req.setAttribute("mes", "Password Is Worng");
							RequestDispatcher ds = req.getRequestDispatcher("userlogin.jsp");
							ds.include(req, resp);
						}
						
					}			
					
						

   } catch (ClassNotFoundException e1) 
	{
		
		//System.out.println("dai mairu");
	}
	catch (ServletException e1) 
	{
		
		
	}catch (SQLException e)
	{
		
		req.setAttribute("mes", "  Enter ValidCredintials");
		RequestDispatcher dsp = req.getRequestDispatcher("userlogin.jsp");
		dsp.include(req, resp);
	
	}
 
	
}
			 
	
}
