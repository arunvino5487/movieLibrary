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
@WebServlet("/watchmovie")
public class WatchMovie extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		HttpSession session = req.getSession();
	
		
		Dao dao = new Dao();
		
		
		try {
			
			req.setAttribute("usermovie", dao.getUserMovie(	(int)session.getAttribute("userid")));
			RequestDispatcher rst = req.getRequestDispatcher("watchmovie.jsp");
			rst.include(req, resp);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
