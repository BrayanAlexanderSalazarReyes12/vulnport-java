package com.auditorlab.vulnport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/diagnostic")
public class CommandServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String host = request.getParameter("host");
        if (host == null) host = "127.0.0.1";

        // LAB-CMD-001: entrada del usuario se incorpora a un comando del sistema.
        String os = System.getProperty("os.name").toLowerCase();
        String command = os.contains("win")
                ? "cmd /c ping -n 1 " + host
                : "sh -c ping -c 1 " + host;

        Process process = Runtime.getRuntime().exec(command);

        response.setContentType("text/plain;charset=UTF-8");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        PrintWriter out = response.getWriter();
        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }
        reader.close();
    }
}
