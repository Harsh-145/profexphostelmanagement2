package com.gecpian.profexphostelmanagement2;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sql = "INSERT INTO admins (username, email, password) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, request.getParameter("username"));
            ps.setString(2, request.getParameter("email"));
            ps.setString(3, request.getParameter("password"));

            ps.executeUpdate();
            out.println("<h3 style='color:green;'>Admin Account Created Successfully!</h3>");
            out.println("<a href='index.html'>Click here to Login</a>");
            
        } catch (SQLIntegrityConstraintViolationException e) {
            out.println("<h3 style='color:red;'>Error: Username or Email is already taken by another staff member.</h3>");
            out.println("<a href='admin-register.html'>Try Again</a>");
        } catch (Exception e) {
            out.println("<h3>Database Error.</h3>");
            e.printStackTrace(out);
        }
    }
}