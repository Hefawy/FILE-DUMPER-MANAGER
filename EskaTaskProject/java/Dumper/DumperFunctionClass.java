package Dumper;

import java.io.File;
import java.io.IOException;

public class DumperFunctionClass {

	public void dumperFunctionClass(File pathValidator, File pathDumper, String Delimiter) throws IOException {

		LoadSetupTablesInDum.loadTablesFromDatabaseFromDum();

		MoverClassToDumper classToDumper = new MoverClassToDumper();
		classToDumper.moverClassToDumper(pathValidator, pathDumper);

		ReadOutFile file = new ReadOutFile();
		file.readFileOut(pathDumper);

		System.err.println("Dumper Done");
	}
}
