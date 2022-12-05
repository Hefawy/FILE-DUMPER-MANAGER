package Validator;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class ChangeExtinsionToOut {

	public void changeExtinsionToOut(File pathVlidator) {

		File filesVal = null;
		String extention = ".out";

		if (pathVlidator != null && pathVlidator.isDirectory()) {
			File[] files = pathVlidator.listFiles();

			if (files != null) {
				for (int i = 0; i < files.length; i++) {

					filesVal = files[i];

					String fileName = filesVal.getName();
					String sourceName = FilenameUtils.removeExtension(fileName);

					String valFile = pathVlidator + "/" + fileName;
					String newName = pathVlidator + "/" + sourceName + extention;
					@SuppressWarnings("unused")
					String OutFile = sourceName + extention;

					File file = new File(valFile);

					File file2 = new File(newName);

					boolean success = file.renameTo(file2);

					if (!success) {
//						System.out.println("rename success from val to out");
					}
				}
			}

		}
	}
}
