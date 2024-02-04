package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import controller.SaveUserMovie;
import dto.Admin;
import dto.Movie;
import dto.Save;
import dto.User;

public class Dao 
{
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movielibrary" ,"root" , "root"); 
			
		return conn;
		
	}
	
	public int saveAdmin(Admin admin) throws ClassNotFoundException, SQLException 
	{
		Connection conn = getConnection();
		
		
		PreparedStatement pst = conn.prepareStatement("insert into admin values(?,?,?,?,?)");
		
		pst.setLong(1, admin.getAdminid());
		pst.setString(2, admin.getAdminname());
		pst.setLong(3, admin.getAdmincontact());
		pst.setString(4, admin.getAdminemail());
		pst.setString(5, admin.getAdminpassword());
		
		
		return pst.executeUpdate();
		
	}
	
	public Admin findEmail(String adminemail) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("select * from admin where adminemail = ?");
		
		pst.setString(1, adminemail);
		
		ResultSet rs = 	pst.executeQuery();
		Admin admin = new Admin();
		
		rs.next();
	 	admin.setAdminid(rs.getInt(1));
		admin.setAdminname(rs.getString(2));
		admin.setAdmincontact(rs.getLong(3));
		admin.setAdminemail(rs.getString(4));
		admin.setAdminpassword(rs.getString(5));
		
		
		return admin;
	}
	
	
	
	public int saveMovie(Movie movie) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("insert into movie values (?,?,?,?,?,?,?)");
		
		pst.setInt(1, movie.getMovieid());
		pst.setString(2, movie.getMoviename()); 
		pst.setDouble(3, movie.getMovieprice()); 
		pst.setDouble(4, movie.getMovierating()); 
		pst.setString(5, movie.getMoviegenre()); 
		pst.setString(6, movie.getMovielanguage()); 
		
		
		Blob imageblob = new SerialBlob(movie.getMovieimage());
		pst.setBlob(7, imageblob);		
		
		return pst.executeUpdate();
	}
	
	public List  getMovie() throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("select * from movie");
		
		ResultSet rs = pst.executeQuery();
		
		List<Movie> movies = new ArrayList<Movie>();
		
		while(rs.next())
		{
			
			Movie m = new Movie();
			m.setMovieid(rs.getInt(1));
			m.setMoviename(rs.getString(2));
			m.setMovieprice(rs.getDouble(3));
			m.setMovierating(rs.getDouble(4));
			m.setMoviegenre(rs.getString(5));
			m.setMovielanguage(rs.getString(6));
			
			Blob b = rs.getBlob(7);
			byte[] img = b.getBytes(1, (int)b.length());
			
			m.setMovieimage(img);
			movies.add(m);
			
		}
		return movies;
		
		
	}
	
	public int  deleteMovie(int id) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("delete from movie where movieid = ?");
		
		pst.setInt(1, id);
		
		return pst.executeUpdate();
				
	}
	
	
	public Movie  findMovieById(int id) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("select * from movie where movieid = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		Movie m = new Movie();
		rs.next();
		m.setMovieid(rs.getInt(1));
		m.setMoviename(rs.getString(2));
		m.setMovieprice(rs.getDouble(3));
		m.setMovierating(rs.getDouble(4));
		m.setMoviegenre(rs.getString(5));
		m.setMovielanguage(rs.getString(6));
		
		Blob b = rs.getBlob(7);
		byte[] img = b.getBytes(1, (int)b.length());
		
		m.setMovieimage(img);
		
		
		return m;
				
	}
	public int  UpdateMovie(Movie m) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("update movie set  moviename = ? , movieprice = ?, movierating = ?, moviegenre  = ?, movielanguage  = ?, movieimage = ? where movieid= ?");
		pst.setString(1, m.getMoviename());
		pst.setDouble(2, m.getMovieprice());
		pst.setDouble(3, m.getMovierating());
		pst.setString(4, m.getMoviegenre());
		pst.setString(5, m.getMovielanguage());
		
		Blob imageblob = new SerialBlob(m.getMovieimage());
		pst.setBlob(6, imageblob);
		pst.setInt(7, m.getMovieid());
		
		return pst.executeUpdate();
			
	}
	public  Movie  getPrriviousMovieImage(int id) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("select * from movie where movieid = ?");
		pst.setInt(1, id);		
		Movie mm = new Movie();
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		Blob b = rs.getBlob(7);
		byte[] img = b.getBytes(1, (int)b.length());
		
		mm.setMovieimage(img);
		
		return mm;
		
			
	}
	
	public int saveUser(User user) throws ClassNotFoundException, SQLException 
	{
		Connection conn = getConnection();
		
		
		PreparedStatement pst = conn.prepareStatement("insert into user values(?,?,?,?,?)");
		
		pst.setLong(1, user.getUserid());
		pst.setString(2, user.getUsername());
		pst.setLong(3, user.getUsercontact());
		pst.setString(4, user.getUseremail());
		pst.setString(5, user.getUserpassword());		
		
		return pst.executeUpdate();
		
	}
	public User findUser(String useremail) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("select * from user where useremail = ?");
		pst.setString(1, useremail);
		
		ResultSet rs = pst.executeQuery();
		User user = new User();
		rs.next();
		
		user.setUserid(rs.getInt(1));
		user.setUsername(rs.getString(2));
		user.setUsercontact(rs.getLong(3));
		user.setUseremail(rs.getString(4));
		user.setUserpassword(rs.getString(5));		
		
		return user;
	}
	public List<Movie> getUserMovie( int id)throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("select * from movie inner join saveusermovie where saveusermovie.userid=? and movie.movieid = saveusermovie.movieid");
			
				
		pst.setInt(1, id);
		
		ResultSet rs = pst.executeQuery();
		
		List<Movie> movies = new ArrayList<Movie>();
		
		while(rs.next())
		{
			
			Movie m = new Movie();
			
			m.setMovieid(rs.getInt(1));
			m.setMoviename(rs.getString(2));  
			Blob b = rs.getBlob(7);
			byte[] img = b.getBytes(1, (int)b.length());
			
			m.setMovieimage(img);
			
			movies.add(m);
			
		}
		return movies;
		
}
	
	public void addUserMovie(int userid ,int movieid , String username , String moviename) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("insert into  saveusermovie values (?,?,?,?) ");
		pst.setInt(1, userid);
		pst.setInt(2, movieid);
		pst.setString(3, username);
		pst.setString(4, moviename);
			
		
		 pst.executeUpdate();	
		
	}
	public int  deleteUser(int id) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("delete from user where userid = ?");
		
		pst.setInt(1, id);
		
		return pst.executeUpdate();
				
	}
	public int  deletewatchmovie(int movieid) throws ClassNotFoundException, SQLException 
	{
		
		Connection conn = getConnection();
		
		PreparedStatement pst = conn.prepareStatement("delete  from saveusermovie where movieid = ? ");
	
		pst.setInt(1, movieid);
		
		
		return pst.executeUpdate();
				
	}
	
}
