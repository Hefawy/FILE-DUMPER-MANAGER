package Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MoverClass {

	public void fromSourceToDestination(File pathSource, File pathDestination) {

		try {

			if (pathSource.isDirectory()) {
				File[] file = pathSource.listFiles();
				for (int i = 0; i < file.length; i++) {
					String sourceFiles = file[i].toString();
					String DestinationFiles = pathDestination.toString() + "//" + file[i].getName();

					Files.move(Paths.get(sourceFiles), Paths.get(DestinationFiles));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void chooseFileExtensionToParsing(File pathDestination, String fileName) throws IOException {

		File file = null;
		if (pathDestination != null && pathDestination.isDirectory()) {
			File[] content = pathDestination.listFiles();

			for (int i = 0; i < content.length; i++) {
				if (content != null) {
					file = content[i];
					fileName = file.getName();

					int index = fileName.lastIndexOf(".");
					if (index > 0) {
						String extinsion = fileName.substring(index + 1);
						if (extinsion.endsWith("xml")) {
							// TODO xml Class parsing
							ReadXMLFile xmlFile = new ReadXMLFile();
							xmlFile.readXMLFile(file, fileName);
						} else if (extinsion.endsWith("json")) {
							// TODO json Class parsing
							ReadJsonFile jsonFile = new ReadJsonFile();
							jsonFile.readJsonFile(file, fileName);

						}
					}
				}
			}
		}
	}
}
