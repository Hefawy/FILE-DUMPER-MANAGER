package Parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import MainProject.LoadProperties;

public class UserHandler extends DefaultHandler {

	String Delimiter = LoadProperties.properties.getProperty("Delimiter");

	static public int count;

	private LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();

	boolean bID = false;
	boolean bName = false;
	boolean bLastName = false;
	boolean bBDate = false;
	boolean bNationalID = false;
	boolean bPhone = false;
	boolean bGender = false;
	boolean bAddress = false;

	String fileName = "";

	public UserHandler(String fileName) {

		this.fileName = fileName;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO startElement -> UserHandler Class

		if (qName.equalsIgnoreCase("CDR")) {

		} else if (qName.equalsIgnoreCase("ID")) {
			bID = true;
		} else if (qName.equalsIgnoreCase("Name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("LastName")) {
			bLastName = true;
		} else if (qName.equalsIgnoreCase("BDate")) {
			bBDate = true;
		} else if (qName.equalsIgnoreCase("NationalID")) {
			bNationalID = true;
		} else if (qName.equalsIgnoreCase("Phone")) {
			bPhone = true;
		} else if (qName.equalsIgnoreCase("Gender")) {
			bGender = true;
		} else if (qName.equalsIgnoreCase("Address")) {
			bAddress = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO endElement -> UserHandler Class

		if (qName.equalsIgnoreCase("CDR")) {

			getMap(map, fileName);
			count++;

		}

		if (qName.equalsIgnoreCase("CDRS")) {

		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO characters -> UserHandler Class

		if (bID) {
			map.put(1, new String(ch, start, length).trim());
			bID = false;
		} else if (bName) {
			map.put(2, new String(ch, start, length).trim());
			bName = false;
		} else if (bLastName) {
			map.put(3, new String(ch, start, length).trim());
			bLastName = false;
		} else if (bBDate) {
			map.put(4, new String(ch, start, length).trim());
			bBDate = false;
		} else if (bNationalID) {
			map.put(5, new String(ch, start, length).trim());
			bNationalID = false;
		} else if (bPhone) {
			map.put(6, new String(ch, start, length).trim());
			bPhone = false;
		} else if (bGender) {
			map.put(7, new String(ch, start, length).trim());
			bGender = false;
		} else if (bAddress) {
			map.put(8, new String(ch, start, length).trim());
			bAddress = false;
		}

	}

	public void getMap(LinkedHashMap<Integer, String> map, String fileName) {
		try {

			File parserPath = new File(LoadProperties.properties.getProperty("ParserFilepath"));

			String extention = ".prs";

			FileWriter fileWriter = null;
			BufferedWriter writer = null;

			String sourceName = FilenameUtils.removeExtension(fileName);

			String fileNameInParser = parserPath + "/" + sourceName + extention;

			if (sourceName != "") {
				fileWriter = new FileWriter(fileNameInParser, true);
				writer = new BufferedWriter(fileWriter);

				for (int i = 0; i < 8; i++) {
					if (map.containsKey(i + 1))
						writer.write(map.get(i + 1).toString().trim());
					else
					writer.write("");
					writer.write(Delimiter);
					writer.flush();

				}
				writer.write(fileName);
				writer.newLine();
				writer.flush();
				writer.close();
				map.clear();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}