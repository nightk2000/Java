package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

// Configuration
@SpringBootConfiguration
public class TomcatConfig {

	@Value("${server.port}")
	private Integer port;
	
	@Bean
	public ConfigurableServletWebServerFactory configurableServletWebServerFactory(){
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		//factory.setContextPath("/"); // 设置项目访问路径
		factory.setPort(port);
		return factory;
	}


}
