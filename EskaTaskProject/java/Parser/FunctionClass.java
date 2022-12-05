package Parser;

import java.io.File;
import java.io.IOException;

public class FunctionClass {

	public void functionClassToParser(File pathSource, File pathDestination, File pathParser) throws IOException {

		// TODO loadTablesFromDatabase method (uses in parserFunction method)
		LoadSetupTables.loadTablesFromDatabase();

		// TODO moverClassMethod method (uses in parserFunction method)
		MoverClass moverClass = new MoverClass();
		moverClass.fromSourceToDestination(pathSource, pathDestination);
		moverClass.chooseFileExtensionToParsing(pathDestination, pathParser.getName());

		ChangeExtensionToCSV toCSV = new ChangeExtensionToCSV();

		toCSV.changeExtinsionToCSV(pathSource, pathDestination, pathParser);
		toCSV.changeTODone(pathDestination);

		System.err.println("Parsing Done");

	}

}
