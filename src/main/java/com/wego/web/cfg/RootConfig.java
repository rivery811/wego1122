package com.wego.web.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@MapperScan(basePackages = {"com.wego.web"})
@ComponentScan(basePackages = {"com.wego.web"})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
@Import({
	ServletConfig.class, MybatisConfig.class, 
})
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
	
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://172.168.0.121/wegodb");
		dataSource.setUsername("wego");
		dataSource.setPassword("wego");
		return dataSource;
	}
	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource()); //470?��?���?
	}
	
	

}
