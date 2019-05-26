package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * springboot启动类
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * main 方法，用户启动springboot
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	/**
	 * 数据源
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource dataSource(){
		return new DruidDataSource();
	}
}
