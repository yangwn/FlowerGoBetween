package com.ibm.ABCDEF.config;

import javax.sql.DataSource;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}

	/**
	 * DataSource
	 * 
	 * @return
	 */
	@Bean(destroyMethod = "close", initMethod = "init")
	public DataSource configDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(propertyResolver.getProperty("url"));
		dataSource.setUsername(propertyResolver.getProperty("username"));// 用户名
		dataSource.setPassword(propertyResolver.getProperty("password"));// 密码
		dataSource.setDriverClassName(propertyResolver.getProperty("driver"));
		dataSource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initsize")));
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}

	/**
	 * JdbcTemplate
	 * 
	 * @return
	 */
	@Bean
	public JdbcTemplate configJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.configDataSource());
		return jdbcTemplate;
	}

}
