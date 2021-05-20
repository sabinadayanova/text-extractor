package com.sabina.textractor.converters;

import java.io.IOException;

import java.io.InputStream;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class RtfConverter implements Converter {

  @Override
  public String convert(InputStream is) throws IOException, BadLocationException {
    DefaultStyledDocument styledDoc = new DefaultStyledDocument();
    new RTFEditorKit().read(is, styledDoc, 0);
    String result = styledDoc.getText(0, styledDoc.getLength());
    return result;
  }
}
