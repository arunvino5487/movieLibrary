package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Admin;
@WebServlet("/adminlogin")

public class AdminLogin extends HttpServlet
{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String adminmail = req.getParameter("amail");
		String adminpassword = req.getParameter("apass");
		
		Dao  dao  = new Dao();
	
		try
		{				
			Admin admin =	dao.findEmail(adminmail);
			
			if(admin != null)
			{
			
				if(admin.getAdminpassword().equals(adminpassword))
				{
					if(admin.getAdminemail().equals(adminmail))
					{
						
						HttpSession session = req.getSession();
						session.setAttribute("adminname", admin.getAdminemail());
					
						req.setAttribute("movies", dao.getMovie());
						RequestDispatcher dsp1 = req.getRequestDispatcher("home.jsp");
						dsp1.include(req, resp);
					
					}
				}
				else
				{					
					req.setAttribute("message", "Worng Password");
					RequestDispatcher dsp = req.getRequestDispatcher("adminlogin.jsp");
					dsp.include(req, resp);						
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
			
			req.setAttribute("message", "  Enter Valid Credintials");
			RequestDispatcher dsp = req.getRequestDispatcher("adminlogin.jsp");
			dsp.include(req, resp);
		
		}
		
		
	
	}
}
