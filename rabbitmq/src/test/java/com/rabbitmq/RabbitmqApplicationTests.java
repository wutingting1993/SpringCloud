package com.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabbitmq.configuration.RabbitConfiguration;
import com.rabbitmq.produce.HelloQueueSend;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

	@Autowired
	private HelloQueueSend helloQueueSend;

	@Test
	public void contextLoads() {
		for (int i = 0; i < 100000; i++) {
			helloQueueSend.sendMessage("hello" + i);
		}
	}

}
