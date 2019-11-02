package com.core.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author moubin.mo
 * @date: 2019/11/1 12:24
 */

@Component
public class KafkaSender {

	private final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private final static String KAFKA_TOPIC = "ShortUrlConverterTopic";

	/*
	 * 发送消息方法
	 */
	public void sendTestMessage(KafkaMessage message) {
		message.setId(System.currentTimeMillis());
		message.setMsg(UUID.randomUUID().toString());
		message.setSendTime(new Date());
		LOGGER.info("send kafka message = {}", message.toString());
		kafkaTemplate.send(KAFKA_TOPIC, String.valueOf(message.getId()%10000000), message.toString());
	}
}
