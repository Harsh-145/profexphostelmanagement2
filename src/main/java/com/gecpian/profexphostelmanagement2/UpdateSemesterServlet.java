package com.gecpian.profexphostelmanagement2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateSemesterServlet")
public class UpdateSemesterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int enrollNo = Integer.parseInt(request.getParameter("enrollment_no"));
        int newSem = Integer.parseInt(request.getParameter("sem"));

        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE students SET sem = ? WHERE enrollment_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, newSem);
            ps.setInt(2, enrollNo);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h3>Semester updated successfully!</h3>");
            } else {
                out.println("<h3>Student not found.</h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Database Error!</h3>");
        }
        out.println("<br><a href='dashboard.html'>Back to Dashboard</a>");
    }
}