package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import dao.Dao;
import dto.Movie;
import dto.Save;
import dto.User;


@WebServlet("/saveusermovie")
@MultipartConfig(maxFileSize = 5*1024*1024)
public class SaveUserMovie extends HttpServlet
{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
			int id  = Integer.parseInt(req.getParameter("id"));
		
			Dao dao = new Dao();
					
			
				try {
					
						HttpSession session = req.getSession();
						int userid  = (int) session.getAttribute("userid");
						String username = (String)session.getAttribute("username");
						if(username  != null)
						{
						
						Movie m =dao.findMovieById(id);
						
						session.setAttribute("movieprice",m.getMovieprice() );
						session.setAttribute("movieid",m.getMovieid() );
						req.setAttribute("movies", dao.getMovie());
						RequestDispatcher ds = req.getRequestDispatcher("payment.jsp");
						ds.include(req, resp);	
						
						
						
						
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
		
		



