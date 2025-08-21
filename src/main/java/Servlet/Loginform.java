package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/log")

	public class Loginform extends HttpServlet{
	
		Connection con =null;
	    PreparedStatement pstmt =null;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email=req.getParameter("email");
			
			String password=req.getParameter("password");
			
			System.out.println("Email received: " + email);
			System.out.println("Password received: " + password);
			
			resp.setContentType("text/html");

			PrintWriter out=resp.getWriter();
				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 System.out.println("Loaded and registered the driver");
				
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "pavithra@123");
				 System.out.println("connection established");
				 
				 String qry ="select * from studentdb.user where email=? and password=?";

				 pstmt=con.prepareStatement(qry);
				 System.out.println("platform created");
				 
				 pstmt.setString(1, email);
				 pstmt.setString(2, password);
				 
				ResultSet res= pstmt.executeQuery();
				if (res.next()) {
	                HttpSession session = req.getSession();
	                session.setAttribute("email", email);
	                resp.sendRedirect("Courses.html");
	            } else {
	            	out.println("<h3 style='color:red;'>Invalid credentials</h3>");
	            	out.println("<a href='Login.html'>Try again</a>");

	            }
			}
			catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
			}
			
		}
		
		
	}
	

