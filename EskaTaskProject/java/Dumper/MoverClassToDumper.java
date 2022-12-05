package Dumper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MoverClassToDumper {

	public void moverClassToDumper(File pathValidator, File pathDumper) {
		try {

			if (pathValidator.isDirectory()) {
				File[] files = pathValidator.listFiles();

				for (int i = 0; i < files.length; i++) {
					String fileInValidator = files[i].toString();
					String fileToDumper = pathDumper.toString() + "/" + files[i].getName();
					if (files[i].getName().endsWith("out"))
						Files.move(Paths.get(fileInValidator), Paths.get(fileToDumper));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
