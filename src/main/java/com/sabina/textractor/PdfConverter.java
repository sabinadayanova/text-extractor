package com.sabina.textractor;

import java.io.File;
import java.io.FileInputStream;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import java.io.IOException;

public class PdfConverter implements Converter {

  @Override
  public String convert(String filename) throws IOException {
    StringBuilder parsedText = new StringBuilder(1024);
    File file = new File(filename);

    Document pdf = PDF.open(filename);
    pdf.pipe(new OutputTarget(parsedText));
    pdf.close();

    return parsedText.toString();
//    try {
//      parser = new PDFParser(new FileInputStream(file));
//    } catch (Exception e) {
//      System.out.println("Unable to open PDF Parser.");
//      return null;
//    }
//    try {
//      parser.parse();
//      cosDoc = parser.getDocument();
//      pdfStripper = new PDFTextStripper();
//      pdDoc = new PDDocument(cosDoc);
//      parsedText = pdfStripper.getText(pdDoc);
//      cosDoc.close();
//      pdDoc.close();
//    } catch (Exception e) {
//      System.out.println("An exception occured in parsing the PDF Document.");
//      e.printStackTrace();
//      try {
//        if (cosDoc != null) {
//          cosDoc.close();
//        }
//        if (pdDoc != null) {
//          pdDoc.close();
//        }
//      } catch (Exception e1) {
//        e1.printStackTrace();
//      }
//      return null;
//    }
//    System.out.println("Done.");
//    return parsedText;
    //return "LOL";
  }
}
