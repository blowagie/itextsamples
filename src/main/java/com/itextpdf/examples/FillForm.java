package com.itextpdf.examples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class FillForm {

    public byte[] manipulatePdf(InputStream is, Map<String, String> map) throws DocumentException, IOException {
    	  // create a reader object
        PdfReader reader = new PdfReader(is);
        // create a stamper object
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);
        // get fields and set their values
        AcroFields fields = stamper.getAcroFields();
        for (String key : map.keySet()) {
        	fields.setField(key, map.get(key));
        }
        // close the stamper and the reader
        stamper.close();
        reader.close();
        return baos.toByteArray();
    }
}
