package com.sabina.textractor.converters;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import java.io.IOException;
import java.io.InputStream;

public class PdfConverter implements Converter {

  @Override
  public String convert(InputStream is) throws IOException {
    StringBuilder parsedText = new StringBuilder(1024);
    Document pdf = PDF.open(is, "none");
    pdf.pipe(new OutputTarget(parsedText));
    pdf.close();
    return parsedText.toString();
  }
}
