package Validator;

import java.io.File;
import java.io.IOException;

public class ValidatorFunctionClass {

	public void functionClassTOValidator(File pathParser, File pathValidator, String delimiter) {

		// TODO loadTablesFromDatabase method (uses in validatorFunction method)
		LoadSetupTablesInVal.loadTablesFromDatabaseFromVal();

		// TODO moverClassMethod method (uses in validatorFunction method)
		MoverClassToValidator moverClassToValidator = new MoverClassToValidator();
		moverClassToValidator.fromParserToValidator(pathParser, pathValidator);

		ReadCSVFile readCSV = new ReadCSVFile();
		readCSV.readFileCSV(pathValidator, delimiter);

		DeleteFileCSV fileCSV = new DeleteFileCSV();
		try {
			fileCSV.deleteCSVExtintion(pathValidator);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ChangeExtinsionToOut out = new ChangeExtinsionToOut();
		out.changeExtinsionToOut(pathValidator);

		System.err.println("Validator Done");
	}

}
