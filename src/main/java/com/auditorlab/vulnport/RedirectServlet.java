package com.auditorlab.vulnport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/go")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String next = request.getParameter("next");
        if (next == null) next = "index.jsp";

        // LAB-REDIR-001: destino controlado directamente por el usuario.
        response.sendRedirect(next);
    }
}
