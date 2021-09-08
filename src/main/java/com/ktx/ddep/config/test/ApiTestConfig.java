package com.ktx.ddep.config.test;


import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ktx.ddep.config.ContextDataSource;
import com.ktx.ddep.config.ContextSqlMapper;

@Configuration
@Import({ContextDataSource.class, ContextSqlMapper.class, MvcTestConfig.class})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ktx.ddep.controller","com.ktx.ddep.service","com.ktx.ddep.dao", "com.ktx.ddep.argumentresolver"})
public class ApiTestConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpSession sessionCreator() {
		return new MockHttpSession();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		
		return new CommonsMultipartResolver();
	}
}
