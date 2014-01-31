package com.itextpdf.examples;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class StampXofY {

	public byte[] manipulatePdf(InputStream is) throws IOException, DocumentException {
    // Create a reader
    PdfReader reader = new PdfReader(is);
    // Create a stamper
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, output);
    // Add content
		PdfContentByte pagecontent;
		int n = reader.getNumberOfPages();
		for (int i = 0; i < n; ) {
			pagecontent = stamper.getOverContent(++i);
			ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
					new Phrase(String.format("page %s of %s", i, n)), 559, 806, 0);
		}
    // Close the stamper and the reader
		stamper.close();
		reader.close();
    return output.toByteArray();
	}
}