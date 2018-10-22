package org.artorg.tools.phantomData.server.xml;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers. DocumentBuilderFactory ;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax. SAXParseException ;
import org.xml.sax.ErrorHandler;
public class XMLDOMParsing {
	private Document xmldom = null;
	public Document getDOM () { return xmldom; }
	public XMLDOMParsing (String xmlfilepathstr) {
		String xmldoc = file2str(xmlfilepathstr );
		xmldom = XML2DOM(xmldoc );
	}
	private String file2str (String filepathstr) {
		String str = "";
		try {
			str = new String(Files.readAllBytes(Paths.get(filepathstr )),
					StandardCharsets.UTF_8 );
		} catch (Exception e) {
			e.printStackTrace (); 
		}
		return str;
	}
	
	private Document XML2DOM (String xmldoc) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance ();
		Document xmldom = null;
		try {
			DocumentBuilder db = dbf. newDocumentBuilder ();
			db. setErrorHandler (new SimpleErrorHandler ());
			InputSource src = new InputSource(new StringReader(xmldoc ));
			src.setEncoding("UTF -8");
			xmldom = db.parse(src);
		} catch (Exception e) {
			e.printStackTrace (); }
		return xmldom;
	}
	
	private class SimpleErrorHandler implements ErrorHandler {
		public void warning(SAXParseException e) throws SAXException {
			System.out.println(e.getMessage ());
		}
		
		public void error(SAXParseException e) throws SAXException {
			System.out.println(e.getMessage ());
		}
		public void fatalError(SAXParseException e) throws SAXException {
			System.out.println(e.getMessage ());
		}
	}
	

}
