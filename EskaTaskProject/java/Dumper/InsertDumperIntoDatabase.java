package Dumper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertDumperIntoDatabase {

	public void sendQueryToTableDumperStatus(String nameReadFile, String tracingID, long numberOfCdr) {

		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/filedumper";

		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");

			String myquery = "insert into fdm_dumber_status (SOURCE_FILE_NAME,TRACING_ID,NUMBER_OF_CDRS,CREATE_DATE)"
					+ "values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(myquery);

			preparedStmt.setString(1, nameReadFile);
			preparedStmt.setString(2, tracingID);
			preparedStmt.setLong(3, numberOfCdr);
			preparedStmt.setString(4, time());

			preparedStmt.executeUpdate();
			if (!conn.getAutoCommit())
				conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String time() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String dateTime = dateTimeFormatter.format(localDateTime);
		return dateTime;
	}

}
