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
		ByteArrayOutputStream output = new ByteArrayOutputStream();
    PdfReader reader = new PdfReader(is);
		int n = reader.getNumberOfPages();
		PdfStamper stamper = new PdfStamper(reader, output);
		PdfContentByte pagecontent;
		for (int i = 0; i < n; ) {
			pagecontent = stamper.getOverContent(++i);
			ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
					new Phrase(String.format("page %s of %s", i, n)), 559, 806, 0);
		}
		stamper.close();
		reader.close();
    return output.toByteArray();
	}
}