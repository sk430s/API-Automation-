package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import common.Hooks;

public class CommonUtils {
	static String requestPath = Hooks.config_prop.getProperty("RequestFilePath");
	
	static Logger logger = Logs.getLogger("CommonUtils");
	
	public static String readFile(String fileName) throws IOException {
		String filePath = requestPath + fileName;
		return new String(Files.readAllBytes(Paths.get(filePath)));

	}

	public static Map<String, Object> getKVPairFromString(String jsonKeyValueString) {
		Map<String, Object> keyValuesMap = new HashMap<String, Object>();
		String[] keyValues = jsonKeyValueString.split(",");
//		System.out.println(keyValues);
		for (String keyValuePair : keyValues) {
			String[] array = keyValuePair.split(":");
//			System.out.println(array);
			String key = array[0].trim();
			String value = (array[1] == null) ? null : array[1].trim();
			keyValuesMap.put(key, value);
		}
		return keyValuesMap;

	}

	public static String updateJson(String jsonString, Map<String, Object> keyValuesMap) {
		// Check if Request is an Array of Objects
		jsonString.trim();
		if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
			JSONArray reqArray = new JSONArray(jsonString);
			for (String key : keyValuesMap.keySet()) {
				String[] pathArray = key.split("[.]");
				int objectIndex = Integer
						.parseInt(pathArray[0].substring(pathArray[0].indexOf("[") + 1, pathArray[0].indexOf("]")));
				JSONObject jsonObj = reqArray.getJSONObject(objectIndex);
				if (pathArray.length == 2) {
					//jsonObj.put(pathArray[1].trim(), keyValuesMap.get(key));
					String lastKey = pathArray[1].trim();
					if (lastKey.endsWith("[]")) {
						String arrayValues = "["+keyValuesMap.get(key).toString().trim().replaceAll("[/]", ",")+"]";
						lastKey = lastKey.substring(0, lastKey.length()-2);
						logger.info("Array Values for " + key + ": " + arrayValues);
						
						jsonObj.put(lastKey, new JSONArray(arrayValues));
					} else {
						jsonObj.put(lastKey, keyValuesMap.get(key));
					}
				} else {
					JSONObject updates = jsonObj;
					for (int i = 1; i < pathArray.length - 1; i++) {
						// Identify if node is Array
						if (pathArray[i].contains("[") && pathArray[i].contains("]")) {
							String node = pathArray[i].trim().substring(0, pathArray[i].indexOf("["));
							int arrayIndex = Integer.parseInt(
									pathArray[i].substring(pathArray[i].indexOf("[") + 1, pathArray[i].indexOf("]")));
							updates = updates.getJSONArray(node).getJSONObject(arrayIndex);
						}
						// If node is object
						else {
							updates = updates.getJSONObject(pathArray[i].trim());
						}

					}
					String lastKey = pathArray[pathArray.length - 1].trim();
					if (lastKey.endsWith("[]")) {
						String arrayValues = "["+keyValuesMap.get(key).toString().trim().replaceAll("[/]", ",")+"]";
						lastKey = lastKey.substring(0, lastKey.length()-2);
						logger.info("Array Values for " + key + ": " + arrayValues);
						updates.put(lastKey, new JSONArray(arrayValues));
					} else {
						updates.put(lastKey, keyValuesMap.get(key));
					}
				}
			}
			return reqArray.toString();

			// If request is in JSON Object format
		} else {
			JSONObject jsonObj = new JSONObject(jsonString);
			for (String key : keyValuesMap.keySet()) {
				String[] pathArray = key.split("[.]");
				if (pathArray.length == 1) {
					//jsonObj.put(pathArray[0].trim(), keyValuesMap.get(key));
					String lastKey = pathArray[0].trim();
					if (lastKey.endsWith("[]")) {
						String arrayValues = "["+keyValuesMap.get(key).toString().trim().replaceAll("[/]", ",")+"]";
						lastKey = lastKey.substring(0, lastKey.length()-2);
						logger.info("Array Values for " + key + ": " + arrayValues);
						jsonObj.put(lastKey, new JSONArray(arrayValues));
					} else {
						jsonObj.put(lastKey, keyValuesMap.get(key));
					}
				} else {
					JSONObject updates = jsonObj;
					for (int i = 0; i < pathArray.length - 1; i++) {
						// Identify if node is Array
						if (pathArray[i].contains("[") && pathArray[i].contains("]")) {
							String node = pathArray[i].trim().substring(0, pathArray[i].indexOf("["));
							int arrayIndex = Integer.parseInt(
									pathArray[i].substring(pathArray[i].indexOf("[") + 1, pathArray[i].indexOf("]")));
							updates = updates.getJSONArray(node).getJSONObject(arrayIndex);
						}
						// If node is object
						else {
							updates = updates.getJSONObject(pathArray[i].trim());
						}

					}
					String lastKey = pathArray[pathArray.length - 1].trim();
					if (lastKey.endsWith("[]")) {
						String arrayValues = "["+keyValuesMap.get(key).toString().trim().replaceAll("[/]", ",")+"]";
						lastKey = lastKey.substring(0, lastKey.length()-2);
						logger.info("Array Values for " + key + ": " + arrayValues);
						updates.put(lastKey, new JSONArray(arrayValues));
					} else {
						updates.put(lastKey, keyValuesMap.get(key));
					}
				}
			}
			return jsonObj.toString();
		}
	}
	
	public static String updateListJson(String jsonString, String values) {
		JSONObject jsonObj = new JSONObject(jsonString);
		JSONObject filterTuples = (JSONObject) jsonObj.get("filterTuples");
		JSONArray filterDataArray = (JSONArray) filterTuples.get("filterData");
		JSONObject filterDataArrayObject = (JSONObject) filterDataArray.get(0);
		JSONArray filterAttributes = (JSONArray) filterDataArrayObject.get("filterAttributes");
		JSONObject attributesObject = null;
		int attributeSize=filterAttributes.length();
		String attributeName= null;
		String opeartorName = null;
		String valueArray[]= values.split(",");
		String valueArraykeyVal[] = new String[2];
		int valueArrayLen=valueArray.length;
		for (int k = 0; k < valueArrayLen; k++) {
			valueArraykeyVal = valueArray[k].split(":");
			for (int i = 0; i < attributeSize; i++) {
				attributesObject = (JSONObject) filterAttributes.get(i);
				attributeName = (attributesObject.get("attribute")).toString();
				opeartorName = (attributesObject.get("operator")).toString();
				if (attributeName.equals(valueArraykeyVal[0])) {
					JSONArray valuesArray = (JSONArray) attributesObject.get("values");
					for(int j=0;j<valuesArray.length();j++) {
						valuesArray.remove(j);
					}
					valuesArray.put(valueArraykeyVal[1]);
					attributesObject.put("operator", valueArraykeyVal[2]);
				
				}
			}
		}
	
	return jsonObj.toString();
	}
	
	public static String addAttributeObjectToJson(String jsonString, String attributeDetails) {
		JSONObject jsonObj = new JSONObject(jsonString);
		JSONObject newObject =new JSONObject(attributeDetails);
		JSONArray filterDataArray = (JSONArray) jsonObj.get("list");
		int arrSize=filterDataArray.length();
		filterDataArray.put(newObject);
		return jsonObj.toString();
	}


}
