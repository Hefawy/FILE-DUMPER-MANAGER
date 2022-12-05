package Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadSetupTablesInVal {

	public static void loadTablesFromDatabaseFromVal() {
		loadTypesToVal();
		loadTemplateToVal();
	}

	public static ArrayList<FDMTypesModelVal> loadTypesToVal() {

		Connection con = null;

		Statement statement3 = null;

		ResultSet resultSet3 = null;

		ArrayList<FDMTypesModelVal> list3 = new ArrayList<FDMTypesModelVal>();

		String query3 = "select * from fdm_types";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filedumper", "root", "root");

			statement3 = con.createStatement();

			resultSet3 = statement3.executeQuery(query3);

			while (resultSet3.next()) {

				FDMTypesModelVal fdm3 = new FDMTypesModelVal();

				int ID = resultSet3.getInt("ID");

				String TYPE = resultSet3.getString("TYPE");

				fdm3.setID(ID);
				fdm3.setTYPE(TYPE);

				list3.add(fdm3);

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return list3;

	}

	public static ArrayList<FDMTemplateFileModelVal> loadTemplateToVal() {

		Connection con = null;
		Statement statement4 = null;

		ResultSet resultSet4 = null;
		ArrayList<FDMTemplateFileModelVal> list4 = new ArrayList<FDMTemplateFileModelVal>();
		String query4 = "select * from fdm_template_file";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filedumper", "root", "root");

			statement4 = con.createStatement();
			resultSet4 = statement4.executeQuery(query4);

			while (resultSet4.next()) {

				FDMTemplateFileModelVal fdm2 = new FDMTemplateFileModelVal();

				int ID = resultSet4.getInt("ID");

				int TYPE_ID = resultSet4.getInt("TYPE_ID");

				String TAG_NAME = resultSet4.getString("TAG_NAME");

				int ORDER = resultSet4.getInt("ORDER");

				boolean IS_CONDITIONAL_TAG = resultSet4.getBoolean("IS_CONDITIONAL_TAG");

				fdm2.setID(ID);
				fdm2.setTYPE_ID(TYPE_ID);
				fdm2.setTAG_NAME(TAG_NAME);
				fdm2.setORDER(ORDER);
				fdm2.setIS_CONDITIONAL_TAG(IS_CONDITIONAL_TAG);

				list4.add(fdm2);

			}
		} catch (Exception e) {

			e.getStackTrace();
		}

		return list4;

	}
}
