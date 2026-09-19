package com.auditorlab.vulnport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // LAB-AUTHZ-001: sólo verifica que exista sesión, no que el rol sea ADMIN.
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.println("Debes iniciar sesión.");
            return;
        }

        out.println("<h2>Panel administrativo</h2>");
        out.println("<p>Operación sensible disponible para " +
                session.getAttribute("user") + "</p>");
        out.println("<p>Clave de backup: " + AppConfig.BACKUP_KEY + "</p>");
    }
}
