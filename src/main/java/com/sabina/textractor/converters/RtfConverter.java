package com.sabina.textractor.converters;

import com.sabina.textractor.exceptions.ExtractionException;
import java.io.IOException;

import java.io.InputStream;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class RtfConverter implements Converter {

  @Override
  public String convert(InputStream is) {
    try {
      DefaultStyledDocument styledDoc = new DefaultStyledDocument();
      new RTFEditorKit().read(is, styledDoc, 0);
      return styledDoc.getText(0, styledDoc.getLength());
    } catch (BadLocationException e) {
      throw new ExtractionException("BadLocationException occurred when extracting text from RTF file", e);
    } catch (IOException e) {
      throw new ExtractionException("IOException occurred when extracting text from RTF file", e);
    }
  }
}
