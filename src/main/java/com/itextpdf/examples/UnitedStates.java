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
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class UnitedStates {
    public static final Font FONT = new Font();
    public static final Font BOLD = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
    
    public byte[] createPdf(InputStream is) throws IOException, DocumentException {
        // step 1
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // step 2
        PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        process(table, line, BOLD);
        table.setHeaderRows(1);
        while ((line = br.readLine()) != null) {
            process(table, line, FONT);
        }
        br.close();
        document.add(table);
        // step 5
        document.close();
        return baos.toByteArray();
    }
    
    public void process(PdfPTable table, String line, Font font) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            table.addCell(new Phrase(tokenizer.nextToken(), font));
        }
    }
}