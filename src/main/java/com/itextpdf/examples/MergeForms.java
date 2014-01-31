package com.itextpdf.examples;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class MergeForms {

	public byte[] manipulatePdf(ServletContext context, String template) throws IOException, DocumentException {
    // step 1
    Document document = new Document();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
		// step 2
    PdfCopy copy = new PdfCopy(document, output);
		copy.setMergeFields();
    // step 3
		document.open();
    // step 4
		List<PdfReader> readers = new ArrayList<PdfReader>();
		for (int i = 0; i < 3; ) {
			PdfReader reader = new PdfReader(renameFields(context.getResourceAsStream(template), ++i));
			readers.add(reader);
			copy.addDocument(reader);
		}
    // step 5
		document.close();
		for (PdfReader reader : readers) {
			reader.close();
		}
    return output.toByteArray();
	}
	
	public byte[] renameFields(InputStream is, int i) throws IOException, DocumentException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfReader reader = new PdfReader(is);
		PdfStamper stamper = new PdfStamper(reader, baos);
    AcroFields form = stamper.getAcroFields();
    Set<String> keys = new HashSet<String>(form.getFields().keySet());
    for (String key : keys) {
        form.renameField(key, String.format("%s_%d", key, i));
    }
		stamper.close();
		reader.close();
		return baos.toByteArray();
	}
}