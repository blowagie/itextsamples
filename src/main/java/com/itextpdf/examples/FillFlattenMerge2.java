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
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class FillFlattenMerge2 {

    public byte[] manipulatePdf(ServletContext context, String template, String data) throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PdfCopy copy = new PdfSmartCopy(document, output);
        // step 3
        document.open();
        // step 4
        ByteArrayOutputStream baos;
        PdfReader reader;
        PdfStamper stamper;
        AcroFields fields;
        StringTokenizer tokenizer;
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResourceAsStream(data)));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            // create a reader
            reader = new PdfReader(context.getResourceAsStream(template));
            // create a stamper
            baos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, baos);
            // get fields and add values
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
            // close the stamper and the reader
            stamper.close();
            reader.close();
            // add the PDF to PdfCopy
            reader = new PdfReader(baos.toByteArray());
            copy.addDocument(reader);
            reader.close();
        }
        br.close();
        // step 5
        document.close();
        return output.toByteArray();

    }
}
