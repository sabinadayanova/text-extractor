package com.sabina.textractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

public class FileCache {

  private static FileCache fileCache = null;
  private HashMap<String, String> cache;
  private final Gson gson;

  private FileCache() {
    gson = new GsonBuilder().create();
    String filename = "/cache.json";
    InputStream is = Main.class.getResourceAsStream(filename);
    assert is != null;
    cache = gson.fromJson(new InputStreamReader(is), HashMap.class);
    if (cache == null) {
      cache = new HashMap<>();
    }
  }

  public static FileCache getInstance() {
    if (fileCache == null) {
      fileCache = new FileCache();
    }
    return fileCache;
  }

  public void updateCache(String hash, String content) {
    cache.putIfAbsent(hash, content);
  }

  public String get(String hash) {
    return cache.get(hash);
  }

  public boolean contains(String hash) {
    return cache != null && cache.containsKey(hash);
  }

  public void write() throws URISyntaxException, FileNotFoundException {
    String filename = "/home/sabina/go/src/github.com/java/text-extractor/src/main/resources/cache.json";
    File file = new File(filename);
    OutputStream os = new FileOutputStream(file);
    String json = gson.toJson(cache);
    try {
      IOUtils.write(json, os, StandardCharsets.UTF_8.name());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
