package Parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InsertParsingIntoDatabase {


	
	public void sendQueryToTableParserStatus( String fileName ,String sourceNameFilecsv , int randomNumber, long numberOfCdr , int FDMType) {
		
		
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/filedumper";
		
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");
			
			String myquery = "insert into fdm_parser_status (TYPE_ID,SOURCE_FILE_NAME,OUT_FILE_NAME,TRACING_ID,NUMBER_OF_CDRS,CREATE_DATE)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(myquery);
			
			ArrayList<FDMTypesModel> types = LoadSetupTables.loadTypes();
					
			preparedStmt.setInt(1, types.get(FDMType).getID());
			preparedStmt.setString(2,fileName );
			preparedStmt.setString(3, sourceNameFilecsv);
			preparedStmt.setInt(4, randomNumber);
			preparedStmt.setLong(5, numberOfCdr);
			preparedStmt.setString(6, time());
			
			preparedStmt.executeUpdate();
			if(!conn.getAutoCommit())
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
