package MainProject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Dumper.DumperFunctionClass;
import Parser.FunctionClass;
import Validator.ValidatorFunctionClass;

public class LoadProperties {

	public static Properties properties;

	public void loadProperties() throws IOException {

		properties = new Properties();

		try {

			FileReader fileReader = new FileReader(
					"C:\\Users\\m.abdallah\\Desktop\\Presentations\\EskaTaskProject\\config.properties");

			properties.load(fileReader);
		} catch (Exception e) {

			e.printStackTrace();
		}

		File pathSource = new File(LoadProperties.properties.getProperty("SourceFilePath"));
//		System.out.println(pathSource);
		File pathDestination = new File(LoadProperties.properties.getProperty("DestinationFilePath"));
//		System.out.println(pathDestination);
		File pathParser = new File(LoadProperties.properties.getProperty("ParserFilepath"));
//		System.out.println(pathParser);
		File pathVlidator = new File(LoadProperties.properties.getProperty("ValidatorFilePath"));
//		System.out.println(pathVlidator);
		String Delimiter = LoadProperties.properties.getProperty("Delimiter");
//		System.out.println(Delimiter);
		File pathDumper = new File(LoadProperties.properties.getProperty("DumperFilePath"));
//		System.out.println(pathDumper);

		@SuppressWarnings("unused")
		MainEskaTaskProject eskaTaskProject = new MainEskaTaskProject(pathSource, pathDestination, pathParser,
				pathVlidator, pathDumper, Delimiter);

		FunctionClass functionParserClass = new FunctionClass();
		functionParserClass.functionClassToParser(pathSource, pathDestination, pathParser);

		ValidatorFunctionClass functionValidatorClass = new ValidatorFunctionClass();
		functionValidatorClass.functionClassTOValidator(pathParser, pathVlidator, Delimiter);

		DumperFunctionClass functionDumperClass = new DumperFunctionClass();
		functionDumperClass.dumperFunctionClass(pathVlidator, pathDumper, Delimiter);

	}
}
