package com.auditorlab.vulnport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/debug")
public class DebugServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String value = request.getParameter("number");
            int parsed = Integer.parseInt(value);
            out.println("Valor: " + parsed);
        } catch (Exception e) {
            // LAB-INFO-001: expone stack trace y configuración interna al cliente.
            out.println("ERROR INTERNO");
            out.println("java.home=" + System.getProperty("java.home"));
            out.println("user.dir=" + System.getProperty("user.dir"));
            out.println("api.user=" + AppConfig.INTERNAL_API_USER);
            e.printStackTrace(out);
        }
    }
}
