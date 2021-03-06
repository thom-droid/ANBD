package com.ktx.ddep.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// bean register for controller
@Configuration
@Import({ContextDataSource.class, ContextSqlMapper.class, SecurityConfig.class, MvcConfig.class})
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.ktx.ddep.service", "com.ktx.ddep.dao", "com.ktx.ddep.security"})
public class ApplicationConfig  {
	
}
