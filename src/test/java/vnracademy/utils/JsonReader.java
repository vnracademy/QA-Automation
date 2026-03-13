package vnracademy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class JsonReader {
	
	// Identify test data file and read it
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jasonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/"+filePath), StandardCharsets.UTF_8);
		
//		new HashMap<String, String>();
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jasonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}

}
