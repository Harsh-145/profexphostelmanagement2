package com.gecpian.profexphostelmanagement2;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO students VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, Integer.parseInt(request.getParameter("enrollment_no")));
            ps.setString(2, request.getParameter("full_name"));
            ps.setString(3, request.getParameter("gender"));
            ps.setDate(4, Date.valueOf(request.getParameter("dob")));
            ps.setString(5, request.getParameter("phone"));
            ps.setString(6, request.getParameter("email"));
            ps.setInt(7, Integer.parseInt(request.getParameter("room_no")));
            ps.setInt(8, Integer.parseInt(request.getParameter("sem")));

            ps.executeUpdate();
            
            out.println("<h3 style='color:green;'>Student Record Saved!</h3>");
            out.println("<a href='dashboard.html'>Return to Dashboard</a>");
            
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Database Error! Enrollment number might already exist.</h3>");
            e.printStackTrace(out);
        }
    }
}