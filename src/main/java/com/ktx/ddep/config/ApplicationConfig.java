package com.ktx.ddep.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


// bean register for controller
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.ktx.ddep.service"
		 //"com.ktx.ddep.dao",
})

public class ApplicationConfig implements TransactionManagementConfigurer {
	
	// ojdbc driver information
    private String driverClassName = "oracle.jdbc.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "DDEP";
    private String password = "1111";
    
    // connection pool config
	@Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
	
	
	@Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
	
	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}

}
