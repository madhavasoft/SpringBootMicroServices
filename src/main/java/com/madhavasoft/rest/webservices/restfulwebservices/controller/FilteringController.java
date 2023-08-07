package com.madhavasoft.rest.webservices.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.madhavasoft.rest.webservices.restfulwebservices.user.SomeBean;

@RestController
public class FilteringController {
	//Static Filtering
	//http://localhost:8080/filtering
/*	@GetMapping("/filtering") //field2 - will be skipped in the response
	public SomeBean filtering() {
		
		return new SomeBean("value1","value2", "value3");
	}
	//http://localhost:8080/filtering-list
	@GetMapping("/filtering-list") //field2, field3
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value1","value2", "value3"),
											new SomeBean("value4","value5", "value6"),
											new SomeBean("value7","value8", "value9"));
		
	}*/
	//http://localhost:8080/filtering
	//Dynamic Filtering
	@GetMapping("/filtering") //field2 - will be skipped in the response
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("value1","value2", "value3");

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		
		return mappingJacksonValue;
	}
	//http://localhost:8080/filtering-list
	@GetMapping("/filtering-list") //filter1 will be skipped and field2, field3 will be displayed
	public MappingJacksonValue filteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2", "value3"),
											new SomeBean("value4","value5", "value6"),
											new SomeBean("value7","value8", "value9"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		
		return mappingJacksonValue;
	} 
}