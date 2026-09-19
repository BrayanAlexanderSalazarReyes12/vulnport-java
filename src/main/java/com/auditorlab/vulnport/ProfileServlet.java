package com.auditorlab.vulnport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Connection cn = Database.getConnection();

            // LAB-IDOR-001: cualquier usuario puede consultar cualquier perfil por ID.
            PreparedStatement ps = cn.prepareStatement(
                    "SELECT ID, USERNAME, FULL_NAME, EMAIL, ROLE FROM USERS WHERE ID=?");
            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                out.println("<h2>Perfil " + rs.getInt("ID") + "</h2>");
                out.println("<p>Usuario: " + rs.getString("USERNAME") + "</p>");
                out.println("<p>Nombre: " + rs.getString("FULL_NAME") + "</p>");
                out.println("<p>Email: " + rs.getString("EMAIL") + "</p>");
                out.println("<p>Rol: " + rs.getString("ROLE") + "</p>");
            } else {
                out.println("Perfil no encontrado.");
            }

            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
