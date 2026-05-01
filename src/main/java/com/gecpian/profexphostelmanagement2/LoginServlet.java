package com.gecpian.profexphostelmanagement2;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminUser = request.getParameter("username"); 
        String adminPass = request.getParameter("password");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            // Check the 'admins' table for the staff member
            String sql = "SELECT username FROM admins WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, adminUser);
            ps.setString(2, adminPass);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // Login Success!
                HttpSession session = request.getSession();
                session.setAttribute("username", rs.getString("username")); 
                response.sendRedirect("dashboard.html"); // Take them to the CRUD dashboard
            } else {
                // Login Failed
                out.println("<h3 style='color:red;'>Invalid Admin Username or Password!</h3>");
                out.println("<a href='index.html'>Try Again</a>");
            }
        } catch (Exception e) {
            out.println("<h3>Database Error.</h3>");
            e.printStackTrace(out);
        }
    }
}