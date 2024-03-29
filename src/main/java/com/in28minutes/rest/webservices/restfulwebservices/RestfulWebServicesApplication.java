package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
//	@Bean
//	public LocaleResolver localResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		localeResolver.setDefaultLocale(Locale.US);
//		return localeResolver;
//	}
	
	@Bean
	public AcceptHeaderLocaleContextResolver localResolver() {
		AcceptHeaderLocaleContextResolver localeResolver = new AcceptHeaderLocaleContextResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
//	@Bean
//	public ResourceBundleMessageSource bundleMessagesource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

}
