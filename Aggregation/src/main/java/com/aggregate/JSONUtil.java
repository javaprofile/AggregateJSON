package com.aggregate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author vipul
 * This class uses object mapper to convert json string to json object.
 */
public class JSONUtil {

	/**
	 * This method uses ObjectMapper to transform json string to json object 
	 * and converts request to fruitGallery and in turn converts list of fruit.
	 * @param jsonString
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<Fruit> convertJSONStringToList(final String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		FruitGallery fruitGallery = objectMapper.readValue(jsonString,FruitGallery.class);
		
		List<List<String>> fruitGalleries = fruitGallery.getValues();
		List<Fruit> fruitList = new ArrayList<Fruit>();
		for( int i = 1; i< fruitGalleries.size(); i++) {
			List<String> innerList = fruitGalleries.get(i);
				Fruit fruit = new Fruit();
				fruit.setFruit(innerList.get(0));
				fruit.setDays(innerList.get(1));
				fruit.setCharacteristic1(innerList.get(2));
				fruit.setCharacteristic2(innerList.get(3));
				fruitList.add(fruit);
		}
		return fruitList;
	}
}
