package com.ktx.ddep.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// root context config
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ApplicationConfig.class};
	}

	// mvc config 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {MvcConfig.class};
	}

	// servlet mapping uri for dispatcher servlet
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	// encoding config for incoming requests 
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[]{encodingFilter};
		
	}

}
