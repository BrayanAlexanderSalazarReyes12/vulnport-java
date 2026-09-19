package com.auditorlab.vulnport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/download")
public class FileDownloadServlet extends HttpServlet {

    private final File baseDir = new File(System.getProperty("java.io.tmpdir"), "vulnport-files");

    @Override
    public void init() {
        if (!baseDir.exists()) baseDir.mkdirs();
        try {
            File sample = new File(baseDir, "manual.txt");
            if (!sample.exists()) {
                FileWriter writer = new FileWriter(sample);
                writer.write("Manual de laboratorio VulnPort");
                writer.close();
            }
        } catch (IOException ignored) {}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("file");
        if (name == null) name = "manual.txt";

        // LAB-TRAV-001: concatena la ruta sin normalizar ni validar.
        File target = new File(baseDir, name);

        if (!target.exists() || !target.isFile()) {
            response.sendError(404, "Archivo no encontrado: " + target.getPath());
            return;
        }

        response.setContentType("text/plain;charset=UTF-8");
        BufferedReader reader = new BufferedReader(new FileReader(target));
        PrintWriter out = response.getWriter();
        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }
        reader.close();
    }
}
