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

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int enrollNo = Integer.parseInt(request.getParameter("enrollment_no"));

        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM students WHERE enrollment_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, enrollNo);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h3>Student deleted successfully!</h3>");
            } else {
                out.println("<h3>Student not found.</h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Database Error!</h3>");
        }
        out.println("<br><a href='dashboard.html'>Back to Dashboard</a>");
    }
}