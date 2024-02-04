package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.User;

@WebServlet("/saveuser")
public class SaveUser extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("uid"));
		String name = req.getParameter("uname");
		long contact = Long.parseLong(req.getParameter("ucontact"));
		String email = req.getParameter("umail");
		String pass = req.getParameter("upass");
		
		Dao dao = new Dao();
		
		User user = new User();
		
		user.setUserid(id);
		user.setUsername(name);
		user.setUsercontact(contact);
		user.setUseremail(email);
		user.setUserpassword(pass);
		
		try {
			
				dao.saveUser(user);
				RequestDispatcher rdt  = req.getRequestDispatcher("user.jsp");
				rdt.include(req, resp);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
		
}
