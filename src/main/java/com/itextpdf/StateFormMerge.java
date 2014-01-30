package com.itextpdf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.examples.MergeForms;

public class StateFormMerge extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      try {
          // We create the PDF
          ServletContext context = this.getServletContext();
          MergeForms app = new MergeForms();
          byte[] pdf = app.manipulatePdf(context, "/WEB-INF/state.pdf");
          response.setContentType("application/pdf");
          response.setHeader("Content-Disposition",
            "attachment; filename=\"merged_form.pdf\"");
          response.setContentLength(pdf.length);
 
          OutputStream os = response.getOutputStream();
          os.write(pdf);
          os.flush();
          os.close();
      }
      catch(DocumentException de) {
          throw new IOException(de.getMessage());
      }
  }

}