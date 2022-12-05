package Parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadSetupTables {

	public static void loadTablesFromDatabase() {
		loadTypes();
		loadTemplate();
	}

	public static ArrayList<FDMTypesModel> loadTypes() {

		Connection con = null;

		Statement statement = null;

		ResultSet resultSet = null;

		ArrayList<FDMTypesModel> list = new ArrayList<FDMTypesModel>();

		String query = "select * from fdm_types";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filedumper", "root", "root");

			statement = con.createStatement();

			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				FDMTypesModel fdm = new FDMTypesModel();

				int ID = resultSet.getInt("ID");

				String TYPE = resultSet.getString("TYPE");

				fdm.setID(ID);
				fdm.setTYPE(TYPE);

				list.add(fdm);

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;

	}

	public static ArrayList<FDMTemplateFileModel> loadTemplate() {

		Connection con = null;
		Statement statement2 = null;

		ResultSet resultSet2 = null;
		ArrayList<FDMTemplateFileModel> list2 = new ArrayList<FDMTemplateFileModel>();
		String query2 = "select * from fdm_template_file";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filedumper", "root", "root");

			statement2 = con.createStatement();
			resultSet2 = statement2.executeQuery(query2);

			while (resultSet2.next()) {

				FDMTemplateFileModel fdm2 = new FDMTemplateFileModel();

				int ID = resultSet2.getInt("ID");

				int TYPE_ID = resultSet2.getInt("TYPE_ID");

				String TAG_NAME = resultSet2.getString("TAG_NAME");

				int ORDER = resultSet2.getInt("ORDER");

				boolean IS_CONDITIONAL_TAG = resultSet2.getBoolean("IS_CONDITIONAL_TAG");

				fdm2.setID(ID);
				fdm2.setTYPE_ID(TYPE_ID);
				fdm2.setTAG_NAME(TAG_NAME);
				fdm2.setORDER(ORDER);
				fdm2.setIS_CONDITIONAL_TAG(IS_CONDITIONAL_TAG);

				list2.add(fdm2);

			}
		} catch (Exception e) {

			e.getStackTrace();
		}

		return list2;

	}

}
