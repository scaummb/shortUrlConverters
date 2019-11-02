package com.core.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author moubin.mo
 * @date: 2019/9/20 16:59
 */

@Component
public class SequenceProviderImpl implements SequenceProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(SequenceProviderImpl.class);

	@Autowired
	private RedisProvider redisProvider;

	private static final String BUCKET_NAME = "sequence";

	@Override
	public long getNextSequence(String sequenceDomain) {
		long sequence = 2L;
		RedisTemplate template = redisProvider.getRedisTemplate();
		try {
			Object val = template.opsForHash().get(BUCKET_NAME, sequenceDomain);
			if (val == null || val.toString().isEmpty() || !val.toString().matches("\\d+")) {
				LOGGER.warn("Invalid sequence-generator value: {} found for sequence domain: {}", val != null ? val.toString() : "null", sequenceDomain);
			}
			sequence = template.opsForHash().increment(BUCKET_NAME, sequenceDomain, 1L);
			if (sequence == 1L) {
				sequence = template.opsForHash().increment(BUCKET_NAME, sequenceDomain, 1L);
			}
		} catch (Exception var7) {
			LOGGER.error("Unexpected exception while getting sequence from Redis", var7);
			throw new RuntimeException("Unexpected exception while getting sequence from Redis", var7);
		}
		LOGGER.debug("Return next sequence {} for sequence domain {}", sequence - 1L, sequenceDomain);
		return sequence - 1L;
	}

	@Override
	public long getNextSequenceBlock(String var1, long var2) {
		return 0;
	}

	@Override
	public void resetSequence(String var1, long var2) {

	}

	@Override
	public long getCurrentSequence(String var1) {
		return 0;
	}
}
