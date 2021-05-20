package com.sabina.textractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import java.security.MessageDigest;
import org.apache.commons.io.IOUtils;

public class ExtractorRunner {
  private final Extractor extractor = new Extractor();
  private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

  public static String bytesToHex(byte[] bytes) {
    byte[] hexChars = new byte[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars, StandardCharsets.UTF_8);
  }

  public void run(ArrayList<String> filenames, ArrayList<InputStream> streams, ArrayList<InputStream> disStreams, OutputStream os) {
    try {
      FileCache fileCache = FileCache.getInstance();
      int len = filenames.size();
      for (int i = 0; i < len; i++) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(disStreams.get(i), md);
        while (dis.read() != -1);
        String hash = bytesToHex(md.digest());
        if (fileCache.contains(hash)) {
          IOUtils.write(fileCache.get(hash), os, StandardCharsets.UTF_8.name());
        } else {
          extractor.extract(filenames.get(i), streams.get(i), os, fileCache, hash);
        }
      }

      fileCache.write();
    } catch (FileNotFoundException | URISyntaxException | NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("an input/output exception has occurred while parsing the  file");
      e.printStackTrace();
    } catch (BadLocationException e) {
      System.err.println("a bad location exception has occurred while parsing the  file");
      e.printStackTrace();
    }
  }
}
