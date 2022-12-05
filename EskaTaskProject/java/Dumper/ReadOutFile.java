package Dumper;

import java.io.File;

public class ReadOutFile {

	public void readFileOut(File pathDumper) {

		File readFiles = null;
		if (pathDumper != null && pathDumper.isDirectory()) {
			File[] files = pathDumper.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files != null) {
					readFiles = files[i];

					String pathReadFile = readFiles.getParent() + "\\" + readFiles.getName();
					String nameReadFile = readFiles.getName();

					int index = nameReadFile.lastIndexOf(".");

					if (index > 0) {
						String extinsion = nameReadFile.substring(index + 1);

						if (extinsion.equalsIgnoreCase("out")) {

							DumpClass class1 = new DumpClass();
							class1.readOut(pathReadFile, extinsion, nameReadFile);
						}

					}
				}
			}
		}
	}
}
