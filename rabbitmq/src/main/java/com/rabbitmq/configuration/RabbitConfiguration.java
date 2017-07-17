package com.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by NCP-605 on 2017/7/14.
 */
@Configuration
public class RabbitConfiguration {
	@Bean
	public Queue helloQue() {
		return new Queue("hello_queue");
	}
}
