package Validator;

import java.io.File;
import java.io.IOException;

public class DeleteFileCSV {

	public void deleteCSVExtintion(File pathVlidator) throws IOException {
		File files = null;

		if (pathVlidator != null && pathVlidator.isDirectory()) {

			File[] FilesList = pathVlidator.listFiles();

			for (int i = 0; i < FilesList.length; i++) {
				files = FilesList[i];
				String filePath = files.getParent() + "/" + files.getName();
				String fileName = files.getName();
				File fileDelete = new File(filePath);

				int index = fileName.lastIndexOf(".");
				if (index > 0) {

					String extDelete = fileName.substring(index + 1);

					if (extDelete.equalsIgnoreCase("csv")) {
						if (fileDelete.delete()) {
							
						}

					}
				}
			}
		}

	}
}
