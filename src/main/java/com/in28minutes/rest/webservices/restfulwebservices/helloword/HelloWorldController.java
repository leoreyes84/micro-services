package com.in28minutes.rest.webservices.restfulwebservices.helloword;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello world";
	}

	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}

	@GetMapping(path = "hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world, %s", name));
	}

	@GetMapping(path = "hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
//	@GetMapping(path = "hello-world-internationalized")
//	public String helloWorldInternationalized() {
//		return "Good morning";
//	}

}
