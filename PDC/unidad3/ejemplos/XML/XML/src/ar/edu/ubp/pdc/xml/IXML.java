package ar.edu.ubp.pdc.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface IXML {

	public void load(String xmlFile, String xsdFile) throws ParserConfigurationException, SAXException, IOException;
	
}
