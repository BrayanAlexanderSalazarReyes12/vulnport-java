package com.auditorlab.vulnport;

import org.w3c.dom.Document;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/xml-import")
public class XmlImportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // LAB-XXE-001: parser XML sin deshabilitar DTD/entidades externas.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(request.getInputStream());

            out.println("Raíz importada: " + document.getDocumentElement().getNodeName());
            out.println("Contenido: " + document.getDocumentElement().getTextContent());
        } catch (Exception e) {
            response.setStatus(400);
            out.println("Error procesando XML: " + e.toString());
        }
    }
}
