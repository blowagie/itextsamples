package com.itextpdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class HelloServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("application/pdf");
        try {
            // step 1: create a document
            Document document = new Document();
            // step 2: create a writer
            PdfWriter.getInstance(document, response.getOutputStream());
            // step 3: open the document
            document.open();
            // step 4: add content
            document.add(new Paragraph("Hello World"));
            // step 5: close the document
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
  }

}