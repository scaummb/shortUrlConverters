package com.core.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author moubin.mo
 * @date: 2019/8/31 17:22
 */

@Component
public class SystemConfig {

	@Value("${redis.uri}")
	private String redisUrl;

	public String getRedisUrl() {
		return redisUrl;
	}

	public void setRedisUrl(String redisUrl) {
		this.redisUrl = redisUrl;
	}
}
