package Parser;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class ReadXMLFile {

	public void readXMLFile(File pathDestination, String fileName) throws IOException {

		try {
			File inputFile = new File(pathDestination.toString());

			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = parserFactory.newSAXParser();

			UserHandler userHandler = new UserHandler(fileName);
			saxParser.parse(inputFile, userHandler);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

}
