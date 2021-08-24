package com.ktx.ddep.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({ContextDataSource.class, ContextSqlMapper.class})
@ComponentScan(basePackages = {"com.ktx.ddep.dao", "com.ktx.ddep.service"})
@EnableTransactionManagement
public class DaoTestConfig {

}
