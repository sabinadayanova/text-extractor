package com.sabina.textractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.swing.text.BadLocationException;
import java.security.MessageDigest;
import org.apache.commons.io.IOUtils;

public class ExtractorRunner {
  private final Extractor extractor;

  public ExtractorRunner() {
    extractor = new Extractor();
  }

  public void run(String[] filenames, InputStream[] streams, InputStream[] disStreams, OutputStream os) {
    try {
      FileCache fileCache = FileCache.getInstance();
      int len = filenames.length;
      for (int i = 0; i < len; i++) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(disStreams[i], md);
        while (dis.read() != -1);
        String digest = Arrays.toString(md.digest());
        if (fileCache.contains(digest)) {
          System.out.println("cache");
          IOUtils.write(fileCache.get(digest), os, StandardCharsets.UTF_8.name());
        } else {
          extractor.extract(filenames[i], streams[i], os, fileCache, digest);
        }
        dis.close();
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
