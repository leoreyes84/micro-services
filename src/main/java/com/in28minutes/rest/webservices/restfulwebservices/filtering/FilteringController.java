package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path = "/filtering")
	public SomeBean retreiveSomeBean() {
		return new SomeBean("field1", "field2", "field3");
	}

	@GetMapping(path = "/filtering-list")
	public List<SomeBean> retreiveListOfSomeBeans() {
		return Arrays.asList(new SomeBean("field1", "field2", "field3"), new SomeBean("field12", "field22", "field32"));
	}

	@GetMapping(path = "/dynamic-filtering-list")
	public MappingJacksonValue retreiveDynamicListOfSomeBeans() {
		SomeBean someBean = new SomeBean("field1", "field2", "field3");

		Set<String> propertyArray = new HashSet<>(Arrays.asList("field1", "field2"));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray); 
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		mapping.setFilters(filters);
		
		return mapping;
	}

}
