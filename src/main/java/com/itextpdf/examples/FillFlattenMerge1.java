package com.itextpdf.examples;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class FillFlattenMerge1 {

    public byte[] manipulatePdf(ServletContext context, String template, String data) throws DocumentException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Document document = new Document();
        // Using PdfCopy isn't a good idea in this use case.
        // Take a look at FillFlattenMerge2 to find out how to do it right!
        PdfCopy copy = new PdfCopy(document, output);
        document.open();
        ByteArrayOutputStream baos;
        PdfReader reader;
        PdfStamper stamper;
        AcroFields fields;
        StringTokenizer tokenizer;
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResourceAsStream(data)));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            // create a PDF in memory
            baos = new ByteArrayOutputStream();
            reader = new PdfReader(context.getResourceAsStream(template));
            stamper = new PdfStamper(reader, baos);
            fields = stamper.getAcroFields();
            tokenizer = new StringTokenizer(line, ";");
            fields.setField("name", tokenizer.nextToken());
            fields.setField("abbr", tokenizer.nextToken());
            fields.setField("capital", tokenizer.nextToken());
            fields.setField("city", tokenizer.nextToken());
            fields.setField("population", tokenizer.nextToken());
            fields.setField("surface", tokenizer.nextToken());
            fields.setField("timezone1", tokenizer.nextToken());
            fields.setField("timezone2", tokenizer.nextToken());
            fields.setField("dst", tokenizer.nextToken());
            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
            // add the PDF to PdfCopy
            reader = new PdfReader(baos.toByteArray());
            copy.addDocument(reader);
            reader.close();
        }
        br.close();
        document.close();
        return output.toByteArray();
    }
}
