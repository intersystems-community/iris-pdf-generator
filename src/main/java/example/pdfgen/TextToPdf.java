package example.pdfgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.Files;

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

		Font myfont = new Font(Font.FontFamily.COURIER, 9);
		// A few other example fonts...
//		Font myfont = new Font(Font.FontFamily.HELVETICA, 12);
//		Font myfont = new Font(Font.FontFamily.TIMES_ROMAN, 12);


		String sourceContent = readFile(textFilename, Charset.defaultCharset());
		String[] pages = sourceContent.split("");

		for (String page: pages) {
			String lines[] = page.split("\\r?\\n");
			
			for (String line: lines) {
				Paragraph para = new Paragraph(line, myfont);
				para.setAlignment(Element.ALIGN_LEFT);
				pdfDoc.add(para);
			}

			if (!page.replaceAll("[\\r\\n\\t ]", "").isEmpty()) {
				pdfDoc.newPage();
			}
		}
				
		pdfDoc.close();
	}

	static String readFile(String path, Charset encoding)
		throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
