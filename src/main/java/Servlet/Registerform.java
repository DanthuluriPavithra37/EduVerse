package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reg")

	public class Registerform extends HttpServlet{
	
		Connection con =null;
	    PreparedStatement pstmt =null;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String name=req.getParameter("name");
			
			String dob = req.getParameter("dob");
			
			String email=req.getParameter("email");
			
			String password=req.getParameter("password");
			
			resp.setContentType("text/html");
			
			PrintWriter out=resp.getWriter();
			
			System.out.println("Name: " + name);
			System.out.println("DOB: " + dob);
			System.out.println("Email: " + email);
			System.out.println("Password: " + password);

				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 System.out.println("Loaded and registered the driver");
				
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?user=root&password=pavithra@123");
				 System.out.println("connection established");
				 
				 String qry ="insert into studentdb.user values(?,?,?,?)";

				 pstmt=con.prepareStatement(qry);
				 System.out.println("platform created");
				 
				 pstmt.setString(1, name);
				 pstmt.setString(2, dob);
				 pstmt.setString(3, email);
				 pstmt.setString(4, password);
				 
				int res= pstmt.executeUpdate();
				if(res>0) {
					 resp.sendRedirect("Courses.html");
					 pstmt.close();
			         con.close();
				}
				else {
					out.print("<h1>Failed</h1>");
				}
			}
			catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
			}
			
		}
		
		
	}
	

