package com.itextpdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.examples.UnitedStates;

public class PublishStates extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      try {
          // We create the PDF
          UnitedStates app = new UnitedStates();
          ServletContext context = this.getServletContext();
          InputStream is = context.getResourceAsStream("/WEB-INF/united_states.csv");
          byte[] pdf = app.createPdf(is);
          response.setContentType("application/pdf");
          response.setContentLength(pdf.length);
          OutputStream os = response.getOutputStream();
          os.write(pdf);
          os.flush();
          os.close();
      }
      catch(DocumentException de) {
          throw new IOException(de);
      }
  }

}