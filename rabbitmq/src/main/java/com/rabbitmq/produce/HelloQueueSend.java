package com.rabbitmq.produce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NCP-605 on 2017/7/14.
 */
@Service
public class HelloQueueSend {
	private Logger logger = LoggerFactory.getLogger(HelloQueueSend.class);
	@Autowired
	private AmqpTemplate amqpTemplate;

	public boolean sendMessage(String msg) {
		logger.warn("send message : " + msg);
		amqpTemplate.convertAndSend("hello_queue", msg);
		return true;
	}
}
