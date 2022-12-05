package Dumper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import MainProject.LoadProperties;

public class DumpClass {

	String Delimiter = LoadProperties.properties.getProperty("Delimiter");

	public void getTypeId(String pathReadFile, String delimiter, String nameReadFile) {

		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/filedumper";

		try {

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");

			String query = "SELECT * FROM filedumper.fdm_template_file WHERE TYPE_ID = 1 and IS_CONDITIONAL_TAG = true";
			Statement st = conn.createStatement();

			// execute the query
			@SuppressWarnings("unused")
			ResultSet resultSet = st.executeQuery(query);

			readOut(pathReadFile, delimiter, nameReadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void readOut(String pathReadFile, String delimiter, String nameReadFile) {
		int count = 0;
		String tracingID = "";
		String path = pathReadFile;
		long numberOfCdr;
		boolean condition = true;
		try {
			Path path2 = Paths.get(pathReadFile);
			numberOfCdr = Files.lines(path2).count();

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/filedumper";

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");
			String SuccessQuery = "insert into fdm_xml_dumper (CDR_ID,NAME,LAST_NAME,B_DATE,NATIONAL_ID,PHONE,GENDER,ADDRESS,FILE_NAME,CREATE_DATE) values (?, ?, ?, ?, ?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(SuccessQuery);
			
			try (BufferedReader reader = new BufferedReader(new FileReader(pathReadFile))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(Delimiter);

					int firstIndex = pathReadFile.indexOf('_');
					int lastIndex = pathReadFile.lastIndexOf('.');
					if (firstIndex > 0 && lastIndex > 0) {
						tracingID = pathReadFile.substring(firstIndex + 1, lastIndex);
					}

					if (fields[fields.length - 1].toLowerCase().contains("xml")) {
						conn.setAutoCommit(false);

						addBatch(fields, preparedStatement);
					} else {
						conn.setAutoCommit(true);
						
						commit(fields, conn);
					}
				}
			}
			if (!conn.getAutoCommit()) {
				
				preparedStatement.executeBatch();
				conn.commit();
			}
			conn.close();

			InsertDumperIntoDatabase database = new InsertDumperIntoDatabase();
			database.sendQueryToTableDumperStatus(nameReadFile, tracingID, numberOfCdr);
			count++;
		} catch (IOException | SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getStackTrace();
		}

	}

	public void addBatch(String[] values, PreparedStatement preparedStatement) throws SQLException {

		preparedStatement.setString(1, values[0]);
		preparedStatement.setString(2, values[1]);
		preparedStatement.setString(3, values[2]);
		preparedStatement.setString(4, values[3]);
		preparedStatement.setString(5, values[4]);

		preparedStatement.setString(6, values[5]);
		preparedStatement.setString(7, values[6]);
		preparedStatement.setString(8, values[7]);
		preparedStatement.setString(9, values[8]);

		preparedStatement.setString(10, time());
		preparedStatement.addBatch();

	}

	public void commit(String[] values, Connection conn) throws SQLException {

		String RjectedQuery = "insert into fdm_json_dumber (CDR_ID,NAME,LAST_NAME,B_DATE,NATIONAL_ID,PHONE,GENDER,FILE_NAME,CREATE_DATE) values (?, ?, ?, ?, ?,?,?,?,?)";

		PreparedStatement preparedStatement = conn.prepareStatement(RjectedQuery);

		preparedStatement.setString(1, values[0]);
		preparedStatement.setString(2, values[1]);
		preparedStatement.setString(3, values[2]);
		preparedStatement.setString(4, values[3]);
		preparedStatement.setString(5, values[4]);

		preparedStatement.setString(6, values[5]);
		preparedStatement.setString(7, values[6]);
		preparedStatement.setString(8, values[7]);

		preparedStatement.setString(9, time());
		preparedStatement.execute();

	}

	public String time() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String dateTime = dateTimeFormatter.format(localDateTime);
		return dateTime;
	}
}
