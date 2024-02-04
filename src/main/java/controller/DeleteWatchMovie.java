package controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
@WebServlet("/deletewatchmovie")
public class DeleteWatchMovie extends HttpServlet
{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
	
		String username = (String)session.getAttribute("username");
		
		int movieid  =Integer.parseInt(req.getParameter("id"));
		
		Dao dao = new Dao();
		
		try {
				
				if(username != null)
				{                                                                                            
				
				dao.deletewatchmovie(movieid);
				
				req.setAttribute("movies", dao.getMovie());
				RequestDispatcher rst = req.getRequestDispatcher("userhome.jsp");
				rst.include(req, resp);
				}
				else
				{
					req.setAttribute("mes", "Acces Denied , User Login Required");
					RequestDispatcher rd = req.getRequestDispatcher("userlogin.jsp");
					rd.include(req, resp);
				}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
