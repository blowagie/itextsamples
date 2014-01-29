package com.itextpdf;

import java.io.*;
import javax.servlet.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;
import javax.servlet.http.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.examples.FillForm;

public class StateFill extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      try {
          // We create the PDF
          ServletContext context = this.getServletContext();
          InputStream is = context.getResourceAsStream("/WEB-INF/state.pdf");
          String key;
          String value;
          Map<String, String> map = new HashMap<String, String>();
          Enumeration parameters = request.getParameterNames();
          while(parameters.hasMoreElements()) {
              key = (String)parameters.nextElement();
              value = (String)request.getParameter(key);
              map.put(key, value);
          }
          FillForm app = new FillForm();
          byte[] pdf = app.manipulatePdf(is, map);
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