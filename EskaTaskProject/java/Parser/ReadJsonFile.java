package Parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import MainProject.LoadProperties;

@SuppressWarnings("unused")
public class ReadJsonFile {
	String Delimiter = LoadProperties.properties.getProperty("Delimiter");

	@SuppressWarnings("unchecked")
	public void readJsonFile(File pathDestination, String fileName) {

		// TODO JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try {
			FileReader reader = new FileReader(pathDestination);

			// Read JSON file
			Object obj = jsonParser.parse(reader);

			org.json.simple.JSONArray jsonList = (org.json.simple.JSONArray) obj;
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

			// TODO Iterate over data array
			Iterator<Object> unitsIterator = jsonList.iterator();
			while (unitsIterator.hasNext()) {
				org.json.simple.JSONObject emJsonObject = (org.json.simple.JSONObject) unitsIterator.next();
				for (Object key : emJsonObject.keySet()) {
					map.put(key.toString(), emJsonObject.get(key).toString());

				}
				getMap(map, fileName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getMap(LinkedHashMap<String, String> map, String fileName) {

		try {
			List<String> keys = new ArrayList<>();
			keys.add("ID");
			keys.add("Name");
			keys.add("LastName");
			keys.add("BDate");
			keys.add("NationalID");
			keys.add("Phone");
			keys.add("Gender");

			File parserPath = new File(LoadProperties.properties.getProperty("ParserFilepath"));

			String extention = ".prs";

			FileWriter fileWriter = null;
			BufferedWriter writer = null;

			String sourceName = FilenameUtils.removeExtension(fileName);

			String fileNameInParser = parserPath + "/" + sourceName + extention;

			if (sourceName != "") {
				fileWriter = new FileWriter(fileNameInParser, true);
				writer = new BufferedWriter(fileWriter);

				for (String key : keys) {
					if (map.containsKey(key))
						writer.write(map.get(key).toString().trim());
					else
						writer.write("");
					writer.write(Delimiter);
					writer.flush();

				}
				writer.write(fileName);
				writer.newLine();
				writer.flush();
				writer.close();
				map.clear();

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
