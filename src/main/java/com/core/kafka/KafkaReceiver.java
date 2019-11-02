package com.core.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author moubin.mo
 * @date: 2019/11/1 12:33
 */
@Component
public class KafkaReceiver {
	private final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

	private final static String KAFKA_TOPIC = "ShortUrlConverterTopic";

	@KafkaListener(topics = {KAFKA_TOPIC})
	public void listen(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {
			Object message = kafkaMessage.get();
			LOGGER.info("[KafkaReceiver] receive message record = {}, mezssage = {}", record, message);
		}

	}
}
