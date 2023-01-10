package com.tutorials;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**

 */
public class CollectionUse {

	public static void main(String[] args) {
	    
		List<String> sl = new LinkedList();
		Deque<String> dl = new LinkedList();
		
		sl.add("MEG.TO");
		sl.add("CM.TO");
		sl.add("NA.TO");
		sl.add("BNS.TO");
		sl.add("FM.TO");
		sl.add("RY.TO");
		sl.add("C");
		sl.add("BNS.TO");
		sl.add("BMO.TO");
		
		sl.stream()
		.filter(t -> t.contains("B"))
		.forEach(System.out::println);
		
		System.out.println(sl.get(sl.size()-1));
		
		sl.stream()
		.sorted()
		.filter(t -> t.contains("O"))
		.map(e -> e.toLowerCase())
		.map(u -> u.substring(0,u.indexOf(".")))
		.forEach(t -> {
			dl.add(t);
		});
		
		
		System.out.println("-------------------");
		dl.stream().forEach(System.out::println);
		
		System.out.println("--------Duplicates-----------");
		
		Set<String> duppies = new HashSet();
		duppies = sl.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(m -> m.getValue() > 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		
		duppies.forEach(System.out::println);
	}

}
