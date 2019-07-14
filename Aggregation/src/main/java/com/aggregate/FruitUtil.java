/**
 * 
 */
package com.aggregate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author vipul
 * This util class does aggregate operations on the fruit list.
 * Currently the methods in the class does not handle null check. Make sure to send
 * list of fruits and not empty data.
 */
public class FruitUtil {

	/**
	 * This method counts number of fruits in the list.
	 * @param fruitList list that is source of fruit objects.
	 * @return long number of fruits in the list.
	 */
	public static long GetNumberOfFruits( final List<Fruit> fruitList ) {
		return fruitList.stream().count();
	}
	
	/**
	 * This method returns a map of fruit type and list of fruits.
	 * @param fruitList
	 * @return
	 */
	public static Map<String,List<Fruit>> groupByFruitName( final List<Fruit> fruitList ) {
		Map<String,List<Fruit>> fruitMap = 
				fruitList.stream().collect( Collectors.groupingBy(Fruit::getFruit));
		return fruitMap;
	}
	
	/**
	 * This method groups fruits by characteristics1 & 2
	 * @param fruitList
	 * @return
	 */
	public static Map<String,List<String>> 
						groupByCharateristics( final Map<String,List<Fruit>> fruitMap ) {
		Set<String> keySet = fruitMap.keySet();
		Map<String, List<String>> characteristicMap = new HashMap<String, List<String>>();
		keySet.stream().forEach((key)->{
			List<Fruit> specificFruitList = fruitMap.get( key );
			List<String> eachCharacteristcList = new ArrayList<String>();
			for( Fruit fruit : specificFruitList ) {
				if( !eachCharacteristcList.contains( fruit.getCharacteristic1())) {
					eachCharacteristcList.add( fruit.getCharacteristic1() );
				}
				if( !eachCharacteristcList.contains( fruit.getCharacteristic2())) {
					eachCharacteristcList.add( fruit.getCharacteristic2() );
				}
			}
			characteristicMap.put(key, eachCharacteristcList );
		});
		return characteristicMap;
	}
	
	/**
	 * This method counts types of fruit by using grouping by name and 
	 * returns count.
	 * @param fruitList
	 * @return
	 */
	public static long countTypesOfFruit( final List<Fruit> fruitList ) {
		Map<String, List<Fruit>> fruitMap = groupByFruitName( fruitList );
		return fruitMap.values().stream().mapToInt(List::size).count();
	}
	
	/**
	 * This method takes a map which is grouped by name and returns list of fruits sorted by size.
	 * @param fruitMap
	 * @return
	 */
	public static List<String> orderMapByListSize( final Map<String, List<Fruit>> fruitMap ) {
		List<String> keys = fruitMap.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
		return keys;
	}
	/**
	 * This method filters fruit list by number of days.
	 * @param fruitList
	 * @param numberOfDays
	 * @return
	 */
	public static List<Fruit> filterFruitsByNumberofDays( 
			List<Fruit> fruitList, 
			int numberOfDays ) {
		return fruitList.stream().
				filter(eachFruit->Integer.parseInt(eachFruit.getDays())>=3).
				collect(Collectors.toList());
	}
}
