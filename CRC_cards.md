## CRC cards

### **Extractor**

Responsibility: extracting text.
Extracting consists of determining file extension and converting file to text using special tools for a
particular file type.

Collaboration: `Parser`, `Converter`, `FileType`.

### **Parser**

Responsibility: determining file extension.
During first steps we aim to support `.pdf`, `.docx` and `.rtf` formats.

Collaboration: `FileType`.

### **Converter (Interface)**

Sub-classes: `PdfConverter`, `DocxConverter`, `RtfConverter`.

Responsibility: converting file to text using libraries for text extraction. 
Depending on the file type there are several classes that implement this interface.

### **FileType (Enum)**

Responsibility: denoting file extensions -- `PDF`, `DOCX`, `RTF` so far.
