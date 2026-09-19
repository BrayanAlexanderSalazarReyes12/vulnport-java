package com.auditorlab.vulnport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String q = request.getParameter("q");
        if (q == null) q = "";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // LAB-XSS-001: salida HTML sin escape.
        out.println("<h2>Resultados para: " + q + "</h2>");

        try {
            Connection cn = Database.getConnection();
            Statement st = cn.createStatement();

            // LAB-SQLI-002: parámetro de búsqueda concatenado.
            String sql = "SELECT ID, NAME, OWNER, PRICE FROM PRODUCTS " +
                    "WHERE NAME LIKE '%" + q + "%' OR OWNER LIKE '%" + q + "%'";

            ResultSet rs = st.executeQuery(sql);
            out.println("<ul>");
            while (rs.next()) {
                out.println("<li>" + rs.getInt("ID") + " - " +
                        rs.getString("NAME") + " / " + rs.getString("OWNER") +
                        " / $" + rs.getBigDecimal("PRICE") + "</li>");
            }
            out.println("</ul>");

            rs.close();
            st.close();
            cn.close();
        } catch (Exception e) {
            out.println("<pre>" + e.toString() + "</pre>");
        }
    }
}
