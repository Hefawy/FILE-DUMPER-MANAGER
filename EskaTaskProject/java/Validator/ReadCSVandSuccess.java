package Validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import MainProject.LoadProperties;

//TODO This class run by [ReadCSVFile.java] Class
public class ReadCSVandSuccess {

//	public static File files;
//	public ReadCSVandSuccess() {
//		// TODO Auto-generated constructor stub
//		this.files = files;
//	}

	public void getTypeId(String pathReadFile, String delimiter) {

		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/filedumper";

		try {

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");

			String query = "SELECT * FROM filedumper.fdm_template_file WHERE TYPE_ID = 1 and IS_CONDITIONAL_TAG = true";
			Statement st = conn.createStatement();

			// execute the query
			ResultSet resultSet = st.executeQuery(query);
			readCSV(pathReadFile, delimiter, resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void readCSV(String pathReadFile, String delimiter, ResultSet resultSet) {

		int count = 0;
		String tracingID = "";
		String path = pathReadFile;
		long numberOfCdr;
		boolean condition = true;

		try {

			Path path1 = Paths.get(pathReadFile);
			numberOfCdr = Files.lines(path1).count();
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
				String line;

				while ((line = bufferedReader.readLine()) != null) {
					String[] values = line.split(delimiter);

					int firstIndex = pathReadFile.indexOf('_');
					int lastIndex = pathReadFile.lastIndexOf('.');
					if (firstIndex > 0 && lastIndex > 0) {
						tracingID = pathReadFile.substring(firstIndex + 1, lastIndex);
					}
					if (values[7].endsWith("xml")) {
						if (values[4].equals("") || values[5].equals("")) {
							condition = false;
							rejected(values, delimiter, tracingID, numberOfCdr, path1.getFileName().toString());
						}

						else {
							condition = false;
							success(values, delimiter, tracingID, numberOfCdr, path1.getFileName().toString());
						}
					} else {
						if (values[0].equals("") || values[4].equals("")) {
							condition = false;
							rejected(values, delimiter, tracingID, numberOfCdr, path1.getFileName().toString());
						}

						else {
							condition = false;
							success(values, delimiter, tracingID, numberOfCdr, path1.getFileName().toString());
						}
					}

					count++;

				}
//			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void success(String[] values, String delimiter, String tracingID, long numberOfCdr, String prs) {
		String validatorPath = LoadProperties.properties.getProperty("ValidatorFilePath");
		String successPath = validatorPath + "\\" + "SUCCESS_" + tracingID + ".val";
		String sourceNameFileOut = "SUCCESS_" + tracingID + ".out";
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(successPath, true);
			bufferedWriter = new BufferedWriter(fileWriter);

			for (int a = 0; a < values.length; a++) {
				bufferedWriter.write(values[a].trim().toString() + " " + delimiter);
			}
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
			if (Files.lines(Paths.get(successPath)).count() == 1L) {
				InsertValidatorIntoDatabase database2 = new InsertValidatorIntoDatabase();
				database2.sendQueryToTableValidatorStatus(prs, sourceNameFileOut, Integer.parseInt(tracingID),
						numberOfCdr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void rejected(String[] values, String delimiter, String tracingID, long numberOfCdr, String prs) {
		String validatorPath = LoadProperties.properties.getProperty("ValidatorFilePath");
		String rejectedPath = validatorPath + "\\" + "REJECTED_" + tracingID + ".val";
		String sourceNameFileOut = "REJECTED_" + tracingID + ".out";

		BufferedWriter bufferedWriter2 = null;
		FileWriter fileWriter2 = null;

		try {
			fileWriter2 = new FileWriter(rejectedPath, true);
			bufferedWriter2 = new BufferedWriter(fileWriter2);

			for (int i = 0; i < values.length; i++) {
				bufferedWriter2.write(values[i].trim().toString() + " " + delimiter);
			}

			bufferedWriter2.newLine();
			bufferedWriter2.flush();
			bufferedWriter2.close();
			if (Files.lines(Paths.get(rejectedPath)).count() == 1L) {
				InsertValidatorIntoDatabase database2 = new InsertValidatorIntoDatabase();
				database2.sendQueryToTableValidatorStatus(prs, sourceNameFileOut, Integer.parseInt(tracingID),
						numberOfCdr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
