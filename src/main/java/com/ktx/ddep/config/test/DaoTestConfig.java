package com.ktx.ddep.config.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ktx.ddep.config.ContextDataSource;
import com.ktx.ddep.config.ContextSqlMapper;

@Configuration
@Import({ContextDataSource.class, ContextSqlMapper.class})
@ComponentScan(basePackages = {"com.ktx.ddep.dao", "com.ktx.ddep.service"})
@EnableTransactionManagement
public class DaoTestConfig {

}
