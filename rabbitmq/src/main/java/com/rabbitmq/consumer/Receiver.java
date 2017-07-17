package com.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by NCP-605 on 2017/7/14.
 */
@Service
@RabbitListener(queues = "hello_queue")
public class Receiver {

	private Logger logger = LoggerFactory.getLogger(Receiver.class);

	@RabbitHandler
	public void receiveHandler(String msg) {
		logger.warn("receive message : " + msg);
	}
}
