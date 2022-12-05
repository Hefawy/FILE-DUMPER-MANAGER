package Validator;

import java.io.File;

public class ReadCSVFile {

	public void readFileCSV(File pathVlidator, String delimiter) {

		File readFiles = null;
		if (pathVlidator != null && pathVlidator.isDirectory()) {
			File[] files = pathVlidator.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files != null) {
					readFiles = files[i];

					String pathReadFile = readFiles.getParent() + "\\" + readFiles.getName();
					String nameReadFile = readFiles.getName();

					int index = nameReadFile.lastIndexOf(".");

					if (index > 0) {
						String extinsion = nameReadFile.substring(index + 1);

						if (extinsion.equalsIgnoreCase("csv")) {
							ReadCSVandSuccess csvandSuccess = new ReadCSVandSuccess();
							// TODO send parameter
							csvandSuccess.getTypeId(pathReadFile, delimiter);

						}

					}
				}
			}
		}
	}
}
