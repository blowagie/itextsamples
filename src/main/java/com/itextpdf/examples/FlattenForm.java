package com.itextpdf.examples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class FlattenForm {

    public byte[] manipulatePdf(InputStream is, Map<String, String> map) throws DocumentException, IOException {
        // create a reader
    	  PdfReader reader = new PdfReader(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // create a stamper
        PdfStamper stamper = new PdfStamper(reader, baos);
        // get fields and set their value 
        AcroFields fields = stamper.getAcroFields();
        for (String key : map.keySet()) {
        	fields.setField(key, map.get(key));
        }
        // flatten the form
        stamper.setFormFlattening(true);
        // close the stamper and the reader
        stamper.close();
        reader.close();
        return baos.toByteArray();
    }
}
