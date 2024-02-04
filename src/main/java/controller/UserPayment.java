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
import dto.Movie;
@WebServlet("/payment")
public class UserPayment extends HttpServlet
{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		Dao dao = new Dao();
		HttpSession session = req.getSession();
		
		int movieid =(int) session.getAttribute("movieid") ;
		String username = (String)session.getAttribute("username");
		int userid = (int)session.getAttribute("userid");
		
	
		
		try {	
			
			if(username != null)
			{		
			
				Movie m = dao.findMovieById(movieid);
				
  				if(m.getMovieprice() == amount)
				{
					dao.addUserMovie( userid , m.getMovieid() , username , m.getMoviename() );
					req.setAttribute("movies", dao.getMovie());
					RequestDispatcher ds = req.getRequestDispatcher("userhome.jsp");
					ds.include(req, resp);	
				}
				else
				{
					req.setAttribute("mes", "Enter Proper Amount and Make a Payment");
					RequestDispatcher ds = req.getRequestDispatcher("payment.jsp");
					ds.include(req, resp);	
				}
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
