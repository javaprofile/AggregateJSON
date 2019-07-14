/**
 * 
 */
package com.aggregate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;




import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author vipul
 * This class reads json data from a URL and performs required aggregation on the JSON data and prints it. 
 */
public class Aggregation {

	/**
	 * This class is invoked by client, combines reading json from url and aggregating data.
	 */
	public void performAggregationOnJSON() {
		String json = null;
		try {
			Reader reader = readJSONFromUrl();
			json = readAll( reader );
			aggregateJSON( json );
		} catch (Exception e) {
			System.out.println("Problem executing the application "+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * This class reads JSON string from url and creates a character reader object
	 * from input stream.
	 * @return Reader
	 */
	private Reader readJSONFromUrl()  {
		InputStream inputStream;
		try {
			inputStream = new URL( AggregateConstants.URL).
															openStream();
		} catch (IOException e) {
			throw new AggregationException("Problem with opening stream with URL"+AggregateConstants.URL);
		}
		Reader reader = new BufferedReader(new InputStreamReader(inputStream,Charset.forName("UTF-8")));
		return reader;
	}
	/**
	 * 
	 * @param jsonString
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void aggregateJSON(final String jsonString )  {
		List<Fruit> fruitList;
		try {
			fruitList = JSONUtil.convertJSONStringToList(jsonString);
		} catch (IOException e) {
			throw new AggregationException("Problem while converting json to list of fruit objects");
		}
		// Count total number of fruits.
		System.out.println("Total number of fruit: "+ 
						FruitUtil.GetNumberOfFruits( fruitList ) );
		System.out.println();
		//Types of fruit
		System.out.println("Types of fruit: "+ 
						FruitUtil.countTypesOfFruit( fruitList ) );
		System.out.println();
		//Each type of fruit in descending order.
		System.out.println("The number of each type of fruit in descending order");
		Map<String, List<Fruit>> fruitMap = FruitUtil.groupByFruitName( fruitList ); //group by fruit name. key as fruit name and list of fruit objects.
		List<String> sortedFruit =  FruitUtil.orderMapByListSize(fruitMap);
		sortedFruit.stream().forEach((eachFruit)->{
			System.out.println(eachFruit+": "+(fruitMap.get(eachFruit).size()));
		});
		System.out.println();
		//characteristics of fruits
		System.out.println("The Characteristics(size, color, shape etc) of each fruit by type");
		Map<String,List<String>> groupedMap = FruitUtil.groupByCharateristics( fruitMap ); //group by fruit name. key as fruit name and list of fruit objects.
		Set<String> keySet = groupedMap.keySet();
		keySet.stream().forEach((eachFruit)->{
			System.out.println((fruitMap.get(eachFruit).size())+" "+eachFruit+": "+String.join(",", groupedMap.get(eachFruit)));
		});
		System.out.println();
		//Number of days old.
		System.out.println("Have any fruit been in the basket for over 3 days");
		List<Fruit> filteredFruitList = FruitUtil.filterFruitsByNumberofDays(
															fruitList, 
															AggregateConstants.NUMBER_OF_DAYS);
		Map<String,List<Fruit>> filteredMap = FruitUtil.groupByFruitName(filteredFruitList);
		Set<String> filteredSet = filteredMap.keySet();
		filteredSet.stream().forEach((eachFruit)->{
			System.out.println((filteredMap.get(eachFruit).size())+" "+eachFruit+" is/are "+AggregateConstants.NUMBER_OF_DAYS+"days old.");
		});
	}
	
	/**
	 * This method builds converts json reqeust from Reader object to String.
	 * @param reader
	 * @return String
	 */
	private static String readAll( final Reader reader ) {
		StringBuilder builder = new StringBuilder();
		int cp;
		try {
			while( (cp = reader.read()) != -1 ) {
				builder.append( (char)cp );
			}
		} catch (IOException e) {
			throw new AggregationException("Problem while building json request from reader.");
		}
		return builder.toString();
	}
	
}
