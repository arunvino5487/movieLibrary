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
import javax.servlet.http.Part;

import dao.Dao;
import dto.Movie;
@MultipartConfig(maxFileSize = 5*1024*1024)
@WebServlet("/updatemovie")
public class UpdateMovie extends HttpServlet
{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int movieid = Integer.parseInt(req.getParameter("movieid"));
		String moviename  = req.getParameter("moviename");
		double movieprice = Double.parseDouble(req.getParameter("movieprice"));
		double movierating = Double.parseDouble(req.getParameter("movierating"));
		String moviegenre = req.getParameter("moviegenre");
		String movielanguage = req.getParameter("movielanguage");
		Part imagepart = req.getPart("movieimage");
	
		
		Movie movie = new Movie();
		
		movie.setMovieid(movieid);
		movie.setMoviename(moviename);
		movie.setMovieprice(movieprice);
		movie.setMovierating(movierating);
		movie.setMoviegenre(moviegenre);
		movie.setMovielanguage(movielanguage);
		
		Dao dao = new Dao();
				
		try {
				HttpSession sesion = req.getSession();
				String adminname =(String) sesion.getAttribute("adminname");
				
				if(adminname != null)
				{
					if(imagepart.getInputStream().readAllBytes().length < 1)
					{
						Movie dmovie = dao.getPrriviousMovieImage(movieid);
						movie.setMovieimage(dmovie.getMovieimage());
						dao.UpdateMovie(movie);
							
						req.setAttribute("movies",dao.getMovie());
						RequestDispatcher  dsp= req.getRequestDispatcher("home.jsp");
						dsp.include(req, resp);
								
					}
					else 
					{
						movie.setMovieimage(imagepart.getInputStream().readAllBytes());
						dao.UpdateMovie(movie);
						
						req.setAttribute("movies",dao.getMovie());
						RequestDispatcher  dsp= req.getRequestDispatcher("home.jsp");
						dsp.include(req, resp);
						
					
								
						}		
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
	
	

	
	

