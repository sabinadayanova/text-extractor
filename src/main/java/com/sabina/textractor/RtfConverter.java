package com.sabina.textractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class RtfConverter implements Converter {

  @Override
  public String convert(String filename) throws IOException, BadLocationException {
    File file = new File(filename);
    DefaultStyledDocument styledDoc = new DefaultStyledDocument();
    new RTFEditorKit().read(new FileInputStream(file), styledDoc, 0);
    return styledDoc.getText(0, styledDoc.getLength());
  }
}
