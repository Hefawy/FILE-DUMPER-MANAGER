package Dumper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadSetupTablesInDum {

	public static void loadTablesFromDatabaseFromDum() {
		loadTypesToDum();
		loadTemplateToDum();
	}

	public static ArrayList<FDMTypesModelDum> loadTypesToDum() {

		Connection con = null;

		Statement statement5 = null;

		ResultSet resultSet5 = null;

		ArrayList<FDMTypesModelDum> list5 = new ArrayList<FDMTypesModelDum>();

		String query5 = "select * from fdm_types";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filedumper", "root", "root");

			statement5 = con.createStatement();

			resultSet5 = statement5.executeQuery(query5);

			while (resultSet5.next()) {

				FDMTypesModelDum fdm5 = new FDMTypesModelDum();

				int ID = resultSet5.getInt("ID");

				String TYPE = resultSet5.getString("TYPE");

				fdm5.setID(ID);
				fdm5.setTYPE(TYPE);

				list5.add(fdm5);

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return list5;

	}

	public static ArrayList<FDMTemplateFileModelDum> loadTemplateToDum() {

		Connection con = null;
		Statement statement6 = null;

		ResultSet resultSet6 = null;
		ArrayList<FDMTemplateFileModelDum> list6 = new ArrayList<FDMTemplateFileModelDum>();
		String query6 = "select * from fdm_template_file";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filedumper", "root", "root");

			statement6 = con.createStatement();
			resultSet6 = statement6.executeQuery(query6);

			while (resultSet6.next()) {

				FDMTemplateFileModelDum fdm6 = new FDMTemplateFileModelDum();

				int ID = resultSet6.getInt("ID");

				int TYPE_ID = resultSet6.getInt("TYPE_ID");

				String TAG_NAME = resultSet6.getString("TAG_NAME");

				int ORDER = resultSet6.getInt("ORDER");

				boolean IS_CONDITIONAL_TAG = resultSet6.getBoolean("IS_CONDITIONAL_TAG");

				fdm6.setID(ID);
				fdm6.setTYPE_ID(TYPE_ID);
				fdm6.setTAG_NAME(TAG_NAME);
				fdm6.setORDER(ORDER);
				fdm6.setIS_CONDITIONAL_TAG(IS_CONDITIONAL_TAG);

				list6.add(fdm6);

			}
		} catch (Exception e) {

			e.getStackTrace();
		}

		return list6;

	}
}
