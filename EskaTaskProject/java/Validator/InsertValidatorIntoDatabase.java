package Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertValidatorIntoDatabase {

	public void sendQueryToTableValidatorStatus(String prs, String sourceNameFilecsv, int randomNumber,
			long numberOfCdr) {

		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/filedumper";

		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");

			String validatorQuery = "insert into fdm_validator_status (SOURCE_FILE_NAME,OUT_FILE_NAME,TRACING_ID,NUMBER_OF_CDRS,CREATE_DATE)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = conn.prepareStatement(validatorQuery);

			preparedStatement.setString(1, prs);
			preparedStatement.setString(2, sourceNameFilecsv);
			preparedStatement.setInt(3, randomNumber);
			preparedStatement.setLong(4, numberOfCdr);
			preparedStatement.setString(5, time());
			preparedStatement.execute();

			conn.close();
			preparedStatement.close();

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
