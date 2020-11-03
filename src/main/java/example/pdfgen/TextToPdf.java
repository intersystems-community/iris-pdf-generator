package example.pdfgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class TextToPdf {
	
	public TextToPdf() {
	}

	public static void generatePDFFromText(String textFilename, String pdfFilename) throws IOException, DocumentException {
		Document pdfDoc = new Document(PageSize.A4);
		PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfFilename))
				.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
		pdfDoc.open();

		Font myfont = new Font(Font.FontFamily.COURIER, 12);
		// A few other example fonts...
//		Font myfont = new Font(Font.FontFamily.HELVETICA, 12);
//		Font myfont = new Font(Font.FontFamily.TIMES_ROMAN, 12);

		pdfDoc.add(new Paragraph("\n"));
		
		BufferedReader br = new BufferedReader(new FileReader(textFilename));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			Paragraph para = new Paragraph(strLine + "\n", myfont);
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDoc.add(para);
		}
		
		pdfDoc.close();
		br.close();
	}

}
