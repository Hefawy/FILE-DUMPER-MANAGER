package Validator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MoverClassToValidator {

	public void fromParserToValidator(File pathParser, File pathVlidator) {

		try {

			if (pathParser.isDirectory()) {
				File[] file = pathParser.listFiles();
				for (int i = 0; i < file.length; i++) {
					String parserFile = file[i].toString();
					String validatorFile = pathVlidator.toString() + "//" + file[i].getName();

					Files.move(Paths.get(parserFile), Paths.get(validatorFile));
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
