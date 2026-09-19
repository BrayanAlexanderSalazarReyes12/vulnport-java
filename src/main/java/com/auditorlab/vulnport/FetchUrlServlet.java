package com.auditorlab.vulnport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

@WebServlet("/fetch")
public class FetchUrlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String target = request.getParameter("url");
        if (target == null) {
            response.sendError(400, "Falta el parámetro url");
            return;
        }

        // LAB-SSRF-001: realiza solicitudes a una URL controlada por el usuario.
        URL url = new URL(target);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"));
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");

        String line;
        int lines = 0;
        while ((line = reader.readLine()) != null && lines++ < 50) {
            out.println(line);
        }
        reader.close();
    }
}
