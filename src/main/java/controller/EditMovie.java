package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Movie;

@WebServlet("/editmoviee")
public class EditMovie extends HttpServlet
{
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
		
			int id = Integer.parseInt(req.getParameter("id"));
			
			
			
			Dao dao = new Dao();
			
			try {
				
					HttpSession sesion = req.getSession();
					String adminname =(String) sesion.getAttribute("adminname");
					
					if(adminname != null)
					{
					
						Movie movie = dao.findMovieById(id);
						
						req.setAttribute("movie", movie);
						RequestDispatcher rsd = req.getRequestDispatcher("editmovie.jsp");
						rsd.include(req, resp);
					
					}
					else
					{
						req.setAttribute("message", "Acces Denied , Admin Login Required");
						RequestDispatcher rd = req.getRequestDispatcher("adminlogin.jsp");
						rd.include(req, resp);
					}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
}
