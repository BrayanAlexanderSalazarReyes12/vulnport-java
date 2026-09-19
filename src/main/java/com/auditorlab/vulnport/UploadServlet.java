package com.auditorlab.vulnport;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Part filePart = request.getPart("file");
        String submittedName = filePart.getSubmittedFileName();

        File uploadDir = new File(System.getProperty("java.io.tmpdir"), "vulnport-uploads");
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // LAB-UPLOAD-001: no valida extensión, MIME, tamaño real ni nombre.
        File destination = new File(uploadDir, submittedName);

        InputStream in = filePart.getInputStream();
        FileOutputStream out = new FileOutputStream(destination);
        byte[] buffer = new byte[4096];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.close();
        in.close();

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<p>Archivo guardado en: " + destination.getAbsolutePath() + "</p>");
    }
}
