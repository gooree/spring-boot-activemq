package com.wxjfkg;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class BootstrapApplication {

	private static Logger logger = LoggerFactory.getLogger(BootstrapApplication.class);
	
	@Value("${queue.name}")
	private String queueName;

	@Bean
	public Queue queue() {
		return new ActiveMQQueue(queueName);
	}

	public static void main(String[] args) throws Exception {
		logger.debug("start spring-boot-activemq application.");
		SpringApplication.run(BootstrapApplication.class, args);
	}

}
