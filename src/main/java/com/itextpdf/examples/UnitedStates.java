/**
 * This example was written by Bruno Lowagie.
 */
package com.itextpdf.examples;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class UnitedStates {
    
    public byte[] createPdf(InputStream is) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, baos);
        document.open();
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        process(table, line);
        table.setHeaderRows(1);
        while ((line = br.readLine()) != null) {
            process(table, line);
        }
        br.close();
        document.add(table);
        document.close();
        return baos.toByteArray();
    }
    
    public void process(PdfPTable table, String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            table.addCell(tokenizer.nextToken());
        }
    }
}