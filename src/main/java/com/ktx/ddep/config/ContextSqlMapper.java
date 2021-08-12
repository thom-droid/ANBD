package com.ktx.ddep.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@MapperScan(basePackages = {"com.ktx.ddep.dao"})
public class ContextSqlMapper {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);

        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));

        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mappers/*.xml"));

        return factoryBean;

    }

    @Bean

    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);

    }



}
