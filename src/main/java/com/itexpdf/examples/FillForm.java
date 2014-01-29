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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	PdfReader reader = new PdfReader(is);
        PdfStamper stamper = new PdfStamper(reader, baos);
        AcroFields fields = stamper.getAcroFields();
        for (String key : map.keySet()) {
        	fields.setField(key, map.get(key));
        }
        stamper.close();
        reader.close();
        return baos.toByteArray();
    }
}
