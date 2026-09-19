package com.auditorlab.vulnport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=UTF-8");

        try {
            Connection cn = Database.getConnection();
            Statement st = cn.createStatement();

            // LAB-SQLI-001: concatenación directa de entrada del usuario.
            String sql = "SELECT ID, USERNAME, FULL_NAME, ROLE FROM USERS " +
                    "WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";

            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                // LAB-SESSION-001: reutiliza la sesión existente y acepta sessionId externo.
                HttpSession session = request.getSession(true);
                String suppliedSession = request.getParameter("sessionId");
                session.setAttribute("user", rs.getString("USERNAME"));
                session.setAttribute("role", rs.getString("ROLE"));
                session.setAttribute("externalSessionId", suppliedSession);

                response.sendRedirect("dashboard.jsp");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<h2>Login incorrecto</h2>");
                out.println("<p>Usuario recibido: " + username + "</p>");
            }

            rs.close();
            st.close();
            cn.close();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
