## CRC cards

### **Extractor**

Responsibility: extracting text.
Extracting consists of determining file type and converting file to text using special tools for a
particular file type.

Collaboration: `Parser`, `Converter`, `FileType`.

### **ExtractorRunner**

Responsibility: orchestrating `Extractor` class when processing series of files.

Collaboration: `Extractor`.

### **Parser**

Responsibility: determining file type.
During first steps we aim to support `.pdf`, `.docx` and `.rtf` formats.

Collaboration: `FileType`.

### **Converter (Interface)**

Sub-classes: `PdfConverter`, `DocxConverter`, `RtfConverter`.

Responsibility: converting file to text using libraries for text extraction. 
Depending on the file type there are several classes that implement this interface.

### **FileType (Enum)**

Responsibility: denoting file types -- `PDF`, `DOCX`, `RTF` so far.
