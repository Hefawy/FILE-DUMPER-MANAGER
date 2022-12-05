package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import org.apache.commons.io.FilenameUtils;
import MainProject.LoadProperties;

public class ChangeExtensionToCSV {

	public void changeTODone(File pathDestination) {
		try {

			File file = null;
			String extentionDone = ".DONE";

			if (pathDestination != null && pathDestination.isDirectory()) {
				File[] listDone = pathDestination.listFiles();

				for (int i = 0; i < listDone.length; i++) {

					file = listDone[i];
					String name = file.getName();
					int index = name.lastIndexOf('.');
					if (index > 0) {
						String ext = name.substring(index);
						if (ext.equalsIgnoreCase(extentionDone)) {

						} else {
							file.renameTo(new File(file.getPath() + extentionDone));
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeExtinsionToCSV(File pathSource, File pathDestination, File pathParser) throws IOException {

		try {

			@SuppressWarnings("unused")
			int count = UserHandler.count++;
			String Delimiter = LoadProperties.properties.getProperty("Delimiter");

			File files = null;
			long numberOfCdr;
			String extention = ".csv";

			if (pathParser != null && pathParser.isDirectory()) {
				File[] filesPRS = pathParser.listFiles();

				if (filesPRS != null) {
					for (int i = 0; i < filesPRS.length; i++) {

						Random random = new Random();
						int randomNumber = random.nextInt(999999);

						files = filesPRS[i];

						String FileName = files.getName();

						String sourcename = FilenameUtils.removeExtension(FileName);
						String prsFile = pathParser + "/" + FileName;

						String parser = pathParser + "/" + sourcename + "_" + randomNumber + extention;
						String sourceNameFilecsv = sourcename + "-" + randomNumber + extention;

						// File with old name (prs)
						File file = new File(prsFile);

						// File with new name (CDR)
						File file2 = new File(parser);

						// TODO boolean Rename file form '.prs' to '.csv'
						boolean success = file.renameTo(file2);

						if (!success) {
//										System.out.println("rename success");
						}

						// must be in class use path parser && destination.

						// TODO count number of CDRS
						Path path = Paths.get(parser);
						numberOfCdr = Files.lines(path).count();
						BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));

						String line = bufferedReader.readLine();
						int index = line.lastIndexOf(Delimiter);
						String prs = FileName;
						if (index > 0) {
							String extinsion = line.substring(index + 1);
							prs = extinsion;
						}

						int FDMType = 0;
						if (prs.endsWith("json")) {
							FDMType = 1;
						}
						bufferedReader.close();

						// TODO variable to database...
						InsertParsingIntoDatabase database = new InsertParsingIntoDatabase();
						database.sendQueryToTableParserStatus(prs, sourceNameFilecsv, randomNumber, numberOfCdr,
								FDMType);

					}
				}

			}

//				}
//			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
