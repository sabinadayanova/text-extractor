public class Extractor {

  private final Parser parser;
  private final Converter converter;

  public Extractor() {
    parser = new Parser();
    converter = new Converter();
  }

  public String extract(String filename) {
    FileType fileType = parser.parse(filename);
    return converter.convert(filename, fileType);
  }

  public String badExtract(String filename) {
    FileType fileType = parser.parse(filename);
    String result;
    switch (fileType) {
      case PDF:
        BadConverter badConverter = new PdfBadConverter();
        result = badConverter.convert(filename);
        break;
      case RTF:
        result = "kick";
        break;
      default:
        result = "kek";
    }
    return result;
  }

}
