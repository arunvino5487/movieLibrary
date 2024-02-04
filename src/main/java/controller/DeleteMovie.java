package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
@MultipartConfig(maxFileSize = 5*1024*1024)
@WebServlet("/deletemovie")
public class DeleteMovie extends HttpServlet
{
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id = Integer.parseInt(req.getParameter("id"));
		
		Dao dao = new Dao();
		
		try {
				HttpSession sesion = req.getSession();
				String adminname =(String) sesion.getAttribute("adminname");
				
				if(adminname != null) 
				{
					dao.deleteMovie(id);
					req.setAttribute("movies", dao.getMovie());
					RequestDispatcher dsp = req.getRequestDispatcher("home.jsp");
					dsp.include(req, resp);
				}
				else
				{
					req.setAttribute("message", "Acces Denied , Admin Login Required");
					RequestDispatcher rd = req.getRequestDispatcher("adminlogin.jsp");
					rd.include(req, resp);
				}
			
				
				
			
			
		} catch (ClassNotFoundException e) {
			
			
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
