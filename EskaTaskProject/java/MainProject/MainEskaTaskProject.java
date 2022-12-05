package MainProject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;


@SuppressWarnings("unused")
public class MainEskaTaskProject {

	
	private static File pathSource;
	private static File pathDestination;
	private static File pathParser;

	private static File pathValidator;
	private static String Delimiter;

	private static File pathDumper;

	public MainEskaTaskProject(File pathSource, File pathDestination, File pathParser, File pathVlidator,
			File pathDumper, String Delimiter) {
		// TODO Auto-generated constructor stub

		MainEskaTaskProject.pathSource = pathSource;
		MainEskaTaskProject.pathDestination = pathDestination;
		MainEskaTaskProject.pathParser = pathParser;
		MainEskaTaskProject.pathValidator = pathVlidator;
		MainEskaTaskProject.pathDumper = pathDumper;
		MainEskaTaskProject.Delimiter = Delimiter;
	}

	public static void main(String[] args) throws IOException, ParseException {

		LoadProperties loadPropertiesFile = new LoadProperties();
		loadPropertiesFile.loadProperties();
	}

}
