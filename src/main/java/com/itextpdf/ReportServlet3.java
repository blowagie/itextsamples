package com.itextpdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.examples.FillFlattenMerge3;

public class ReportServlet3 extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      try {
          ServletContext context = this.getServletContext();
          InputStream data = context.getResourceAsStream("/WEB-INF/united_states.csv");
          InputStream template = context.getResourceAsStream("/WEB-INF/state.pdf");
          FillFlattenMerge3 app = new FillFlattenMerge3();
          byte[] pdf = app.manipulatePdf(template, data);
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