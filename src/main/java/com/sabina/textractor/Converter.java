package com.sabina.textractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.text.BadLocationException;

public interface Converter {

  public String convert(String filename) throws IOException, BadLocationException;
}
